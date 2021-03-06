package com.epam.mjc.dao.builder;

import com.epam.mjc.dao.entity.SearchParams;
import com.epam.mjc.dao.entity.SortParams;
import com.epam.mjc.dao.entity.SortType;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SqlStringBuilder {
    private static final String QUERY_PART_AND = " AND ";
    private static final String QUERY_GROUP_BY = " GROUP BY c.id ";
    private static final String QUERY_HAVING = " HAVING COUNT(c.id) >= ";
    private static final String QUERY_NAME_LIKE = "(c.name LIKE '%";
    private static final String QUERY_DESCRIPTION_LIKE = "%' OR c.description LIKE '%";
    private static final String QUERY_ORDER_BY_CREATION_DATE = " ORDER BY c.creation_date ";
    private static final String QUERY_ORDER_BY_NAME = " ORDER BY c.name ";
    private static final String QUERY_DESC = "DESC";
    private static final String QUERY_ASC = "ASC";
    private static final String QUERY_LIMIT = " LIMIT ";
    private static final String QUERY_OFFSET = " OFFSET ";


    public static String buildQuery(SearchParams searchParams, Integer size, Integer pageNumber) {
        List<String> tags = searchParams.getTags();
        String text = searchParams.getText();
        SortParams sortParams = searchParams.getSortParams();
        String tagQueryPattern = "";
        if (text != null) {
            tagQueryPattern = tagQueryPattern.concat(QUERY_PART_AND + textBuilder(text));
        }
        if (!CollectionUtils.isEmpty(tags)) {
            tagQueryPattern = tagQueryPattern.concat(QUERY_PART_AND + tagsBuilder(tags));
        }

        if (searchParams != null) {
            tagQueryPattern = tagQueryPattern.concat(QUERY_GROUP_BY);
        }

        if (!CollectionUtils.isEmpty(tags)) {
            int tagsSize = tags.size();
            tagQueryPattern = tagQueryPattern.concat(QUERY_HAVING + tagsSize);
        }

        if (sortParams != null) {
            if (size == null && pageNumber == null) {
                sortParams.setSortType(null);
            }
            tagQueryPattern = tagQueryPattern.concat(sorterParamsBuilder(sortParams));
        }
        if (size != null && pageNumber != null) {
            tagQueryPattern = tagQueryPattern.concat(paginationBuilder(size, pageNumber));
        }
        return tagQueryPattern;
    }

    public static String paginationBuilder(Integer size, Integer pageNumber) {
        Integer itemStartFrom = size * (pageNumber - 1);
        return QUERY_LIMIT + size + QUERY_OFFSET + itemStartFrom;
    }

    private static String tagsBuilder(List<String> tags) {
        String queryForTags = "t.name IN(";
        String collect = tags.stream().map(s -> "'" + s + "'").collect(Collectors.joining(","));
        queryForTags = queryForTags.concat(collect + ")");

        return queryForTags;
    }

    private static String textBuilder(String text) {
        return QUERY_NAME_LIKE + text + QUERY_DESCRIPTION_LIKE + text + "%')";
    }

    private static String sorterParamsBuilder(SortParams sortParams) {
        String queryForSorting = "";
        String fieldToSort = sortParams.getFieldName();
        SortType sortType = sortParams.getSortType();
        if (fieldToSort != null) {
            switch (fieldToSort) {
                case "date":
                    queryForSorting = queryForSorting.concat(QUERY_ORDER_BY_CREATION_DATE);
                    break;
                case "name":
                    queryForSorting = queryForSorting.concat(QUERY_ORDER_BY_NAME);
                    break;
                default:
                    return queryForSorting;
            }
        }
        if (sortType != null) {
            if (sortType == SortType.DESC) {
                queryForSorting = queryForSorting.concat(QUERY_DESC);
            } else {
                queryForSorting = queryForSorting.concat(QUERY_ASC);
            }
        }
        return queryForSorting;
    }
}
