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
    public ResponseEntity getCertificateById(@PathVariable("id") long id) {
        Optional<Tag> tag = service.getTagById(id);

        return tag.map(value -> new ResponseEntity(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity("No Tag found for ID " + id, HttpStatus.NOT_FOUND));
    }

    @GetMapping()
    public ResponseEntity getAllCertificates() {
        List<Tag> tag = service.getAllTags();

        if (tag == null) {
            return new ResponseEntity("Not found any tags", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(tag, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity createCertificate(@RequestBody Tag tag) {
        Long createdTagId = null;
        try {
            createdTagId = service.createTag(tag);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return createdTagId!= null ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCertificateById(@PathVariable("id") long id) throws ControllerException {
        try {
            boolean result = service.deleteTagById(id);
            if (!result) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (ServiceException e) {
           throw new ControllerException("Failed deleting tag by id");
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
