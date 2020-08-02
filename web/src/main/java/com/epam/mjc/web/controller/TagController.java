package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.PageDto;
import com.epam.mjc.dao.dto.TagDto;
import com.epam.mjc.service.TagService;
import com.epam.mjc.web.linkbuilder.TagLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private TagLinkBuilder tagLinkBuilder;

    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable("id") long id) {

        return tagLinkBuilder.addLinksToDto(tagService.getTagById(id));
    }

    @GetMapping()
    public PageDto<TagDto> getAllTags(@RequestParam(name = "size", defaultValue = "5") Integer size,
                                      @RequestParam(name = "page", defaultValue = "1") Integer pageNumber) {
        List<TagDto> tags = tagService.getAllTags(size, pageNumber);
        int tagsItemsSize = tagService.countTags();

        return new PageDto<>(tagLinkBuilder.addLinksToList(tags), tagsItemsSize);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public TagDto createTag(@RequestBody TagDto tagDto) {

        return tagService.createTag(tagDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable("id") Long id)  {
        tagService.deleteTagById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TagDto updateTag(@PathVariable("id") Long id,
                            @RequestBody TagDto tagDto) {
        return tagService.updateTag(id, tagDto);
    }

    @GetMapping("/popular")
    public TagDto getMostPopularAndExpensiveTag() {
        return tagService.getMostPopularAndExpensiveTag();
    }
}
