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

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Sort.class);
    }

    @Override
    public SortParams resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String params = webRequest.getParameter("sortBy");
        String fieldName;
        if (params != null) {
            String[] fields = params.split(":");
            fieldName = fields[0];
            String sortingType = fields[1];
            SortType sortTypeValue = null;
                if (sortingType.equalsIgnoreCase(ASC_SORTING_TYPE) || sortingType.equalsIgnoreCase(DESC_SORTING_TYPE))
                    sortTypeValue = SortType.valueOf(fields[1].toUpperCase());
                return new SortParams(fieldName, sortTypeValue);
        }
        return null;
    }
}
