package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService service;

    @GetMapping("/{id}")
    public Tag getTagsById(@PathVariable("id") long id) {

        return  service.getTagById(id);
    }

    @GetMapping()
    public List<Tag> getAllTags() {

        return service.getAllTags();
    }

    @PostMapping()
    public Tag createTag(@RequestBody Tag tag) {

        return service.createTag(tag);
    }

    @DeleteMapping("/{id}")
    public String deleteTagById(@PathVariable("id") String id)  {

        return service.deleteTagById(id);
    }
}
