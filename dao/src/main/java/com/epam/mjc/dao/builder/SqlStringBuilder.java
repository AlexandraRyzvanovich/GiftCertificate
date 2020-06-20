package com.epam.mjc.dao.builder;

import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortType;
import com.epam.mjc.dao.entity.SorterParams;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SqlStringBuilder {
    private static final String QUERY_PART_WHERE = " WHERE ";
    private static final String QUERY_PART_AND = " AND ";

    public static String buildQuery(SearchParams searchParams) {
        List<String> tags = searchParams.getTags();
        String text = searchParams.getText();
        SorterParams sorterParams = searchParams.getSorterParams();
        String tagQueryPattern = "";
        if(text != null) {
            tagQueryPattern = tagQueryPattern.concat(QUERY_PART_WHERE + textBuilder(text));
        }

        if(!CollectionUtils.isEmpty(tags)) {
            if(text!= null) {
                tagQueryPattern = tagQueryPattern.concat(QUERY_PART_AND + SqlStringBuilder.tagsBuilder(tags));
            } else {
                tagQueryPattern = tagQueryPattern.concat(QUERY_PART_WHERE + SqlStringBuilder.tagsBuilder(tags));
            }
        }

        if(sorterParams != null) {
            tagQueryPattern = tagQueryPattern.concat(sorterParamsBuilder(sorterParams));
        }
        return tagQueryPattern;
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

    private static String sorterParamsBuilder(SorterParams sorterParams) {
        String queryForSorting = "";
        String fieldToSort = sorterParams.getFieldName();
        SortType sortType = sorterParams.getSortType();
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
        if (sortType == SortType.DESC) {
            queryForSorting = queryForSorting.concat("DESC");
        } else {
            queryForSorting = queryForSorting.concat("ASC");
        }
        return queryForSorting;
    }
}
