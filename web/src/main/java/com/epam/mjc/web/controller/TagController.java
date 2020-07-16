package com.epam.mjc.web.controller;

import com.epam.mjc.dao.dto.TagDto;
import com.epam.mjc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Qualifier("tagServiceImpl")
    @Autowired
    private TagService service;

    @GetMapping("/{id}")
    public TagDto getTagsById(@PathVariable("id") long id) {

        return  service.getTagById(id);
    }

    @GetMapping()
    public List<TagDto> getAllTags() {

        return service.getAllTags();
    }

    @PostMapping()
    public TagDto createTag(@RequestBody TagDto tagDto) {

        return service.createTag(tagDto);
    }

    @DeleteMapping("/{id}")
    public String deleteTagById(@PathVariable("id") Long id)  {

        return service.deleteTagById(id);
    }
}
