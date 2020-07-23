package com.epam.mjc.web.linkbuilder;

import com.epam.mjc.dao.dto.TagDto;
import com.epam.mjc.web.controller.TagController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagLinkBuilder implements LinkBuilder<TagDto> {
    @Override
    public CollectionModel<TagDto> addLinksToList(List<TagDto> tagDtoList) {
        for (TagDto tag : tagDtoList) {
            addLinksToDto(tag);
        }
        return CollectionModel.of(tagDtoList,
                linkTo(methodOn(TagController.class).getAllTags(null, null)).withRel("getAll"),
                linkTo(methodOn(TagController.class).getMostPopularAndExpensiveTag()).withRel("getPopularTag"));
    }

    @Override
    public TagDto addLinksToDto(TagDto tagDto) {
        tagDto.add(linkTo(methodOn(TagController.class).getTagById(tagDto.getId())).withRel("getTagById"));
        if (RoleIdentifier.isAdmin()) {
            tagDto.add(linkTo(methodOn(TagController.class).createTag(tagDto)).withRel("createTag"));
            tagDto.add(linkTo(methodOn(TagController.class).createTag(tagDto)).withRel("createTag"));
            tagDto.add(linkTo(methodOn(TagController.class).deleteTagById(tagDto.getId())).withRel("deleteByTagId"));
        }
        return tagDto;

    }
}
