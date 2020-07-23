package com.epam.mjc.dao.builder;

import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortParams;
import com.epam.mjc.dao.entity.SortType;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SqlStringBuilder {
    private static final String QUERY_PART_WHERE = " WHERE ";
    private static final String QUERY_PART_AND = " AND ";
    private static final String QUERY_GROUP_BY = " GROUP BY c.id ";
    private static final String QUERY_HAVING = " HAVING COUNT(c.id) >= ";

    public static String buildQuery(SearchParams searchParams, Integer size, Integer pageNumber) {
        List<String> tags = searchParams.getTags();
        String text = searchParams.getText();
        SortParams sortParams = searchParams.getSortParams();
        String tagQueryPattern = "";

        if(text != null) {
            tagQueryPattern = tagQueryPattern.concat(QUERY_PART_WHERE + textBuilder(text));
        }

        if(!CollectionUtils.isEmpty(tags)) {
            if(text!= null) {
                tagQueryPattern = tagQueryPattern.concat(QUERY_PART_AND + tagsBuilder(tags));
            } else {
                tagQueryPattern = tagQueryPattern.concat(QUERY_PART_WHERE + tagsBuilder(tags));
            }
        }
        if(size!= null && pageNumber!= null) {
            tagQueryPattern = tagQueryPattern.concat(QUERY_GROUP_BY);
        }

        if(!CollectionUtils.isEmpty(tags)) {
            int tagsSize = tags.size();
            tagQueryPattern = tagQueryPattern.concat(QUERY_HAVING + tagsSize);
        }

        if(sortParams != null) {
            tagQueryPattern = tagQueryPattern.concat(sorterParamsBuilder(sortParams));
        }
        if(size!= null && pageNumber!= null) {
            tagQueryPattern = tagQueryPattern.concat(" ORDER BY c.id " + paginationBuilder(size, pageNumber));
        }
        return tagQueryPattern;
    }
    public static String paginationBuilder(Integer size, Integer pageNumber) {
        Integer itemStartFrom  = size * (pageNumber - 1);
        return " Limit " +  size +   " OFFSET " + itemStartFrom;
    }

    private static String tagsBuilder(List<String> tags) {
        String queryForTags = "t.name IN(";
        String collect = tags.stream().map(s -> "'" + s + "'").collect(Collectors.joining(","));
        queryForTags = queryForTags.concat(collect + ")");

        return queryForTags;
    }

    private static String textBuilder(String text) {
        return "(c.name LIKE '%" + text + "%' OR c.description LIKE '%" + text + "%')";
    }

    private static String sorterParamsBuilder(SortParams sortParams) {
        String queryForSorting = "";
        String fieldToSort = sortParams.getFieldName();
        SortType sortType = sortParams.getSortType();
        if(fieldToSort != null) {
            switch (fieldToSort) {
                case "date" :
                    queryForSorting = queryForSorting.concat("ORDER BY c.creation_date ");
                    break;
                case "name" :
                    queryForSorting = queryForSorting.concat("ORDER BY c.name ");
                    break;
                default: return queryForSorting;
            }
        }
        if (sortType != null) {
            if (sortType == SortType.DESC) {
                queryForSorting = queryForSorting.concat("DESC");
            } else {
                queryForSorting = queryForSorting.concat("ASC");
            }
        }
        return queryForSorting;
    }
}
