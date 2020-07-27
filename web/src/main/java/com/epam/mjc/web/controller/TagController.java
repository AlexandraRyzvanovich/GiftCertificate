package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.dao.dto.TagDto;
import com.epam.mjc.service.TagService;
import com.epam.mjc.web.linkbuilder.TagLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Qualifier("tagServiceImpl")
    @Autowired
    private TagService service;
    @Autowired
    private TagLinkBuilder tagLinkBuilder;

    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable("id") long id) {

        return  tagLinkBuilder.addLinksToDto(service.getTagById(id));
    }

    @GetMapping()
    public PageDto<TagDto> getAllTags(@RequestParam(name = "size", defaultValue = "5") Integer size,
                                      @RequestParam(name = "page", defaultValue = "1") Integer pageNumber) {
        List<TagDto> tags = service.getAllTags(size, pageNumber);
        int tagsItemsSize = service.countTags();

        return new PageDto<>(tagLinkBuilder.addLinksToList(tags), tagsItemsSize);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public TagDto createTag(@RequestBody TagDto tagDto) {

        return service.createTag(tagDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteTagById(@PathVariable("id") Long id)  {

        return service.deleteTagById(id);
    }

    @GetMapping("/popular")
    public TagDto getMostPopularAndExpensiveTag() {
        return service.getMostPopularAndExpensiveTag();
    }
}
