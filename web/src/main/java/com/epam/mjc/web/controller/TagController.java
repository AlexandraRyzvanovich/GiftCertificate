package com.epam.mjc.web.controller;

import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.exception.ServiceException;
import com.epam.mjc.service.service.TagService;
import com.epam.mjc.web.exception.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Boolean deleteTagById(@PathVariable("id") long id)  {

        return service.deleteTagById(id);
    }
}
