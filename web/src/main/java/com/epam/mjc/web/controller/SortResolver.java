package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.SortType;
import com.epam.mjc.dao.entity.SortParams;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SortResolver implements HandlerMethodArgumentResolver {
    private static final String ASC_SORTING_TYPE = SortType.ASC.name();
    private static final String DESC_SORTING_TYPE = SortType.DESC.name();
    private static final String SORT_BY_PARAMETER = "sortBy";
    private static final String SPLITERATOR = ":";
    private static final String DATE_SORTING_TYPE = "date";
    private static final String NAME_SORTING_TYPE = "name";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Sort.class);
    }

    @Override
    public SortParams resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String params = webRequest.getParameter(SORT_BY_PARAMETER);
        String fieldName;
        if (params == null) {
            String[] fields = params.split(SPLITERATOR);
            fieldName = fields[0];
            String sortingType = fields[1];
            if(!sortingType.equalsIgnoreCase(DATE_SORTING_TYPE) || !sortingType.equalsIgnoreCase(NAME_SORTING_TYPE)) {
                return null;
            }
            SortType sortTypeValue = null;
                if (sortingType.equalsIgnoreCase(ASC_SORTING_TYPE) || sortingType.equalsIgnoreCase(DESC_SORTING_TYPE))
                    sortTypeValue = SortType.valueOf(fields[1].toUpperCase());
                return new SortParams(fieldName, sortTypeValue);
        }
        return null;
    }
}
