package com.epam.mjc.service.impl;

import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.service.TagService;
import com.epam.mjc.service.exception.EntityAlreadyExistsException;
import com.epam.mjc.service.exception.IncorrectParamsException;
import com.epam.mjc.service.exception.NotFoundException;
import com.epam.mjc.service.validator.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagDao tagDao;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag getTagById(Long id) {
        Tag tag = tagDao.getById(id);
        if(tag == null) {
            throw new NotFoundException("No tag found with id" + id);
        }

        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    @Override
    public Tag createTag(Tag tag ) {
        Tag foundTag = tagDao.getByName(tag.getName());
        if(foundTag != null) {
            throw  new EntityAlreadyExistsException("Tag with name " + tag.getName() + " already exists");
        }
            Validator.validateTag(tag);
            Long tagId = tagDao.create(tag);
            if(tagId == null) {
                throw new IncorrectParamsException("Impossible to create tag with given params");
            }
            return tagDao.getById(tagId);

    }

    @Override
    public String deleteTagById(Long id) throws NotFoundException {
        boolean result = tagDao.deleteById(id);
        if(!result) {
            throw new NotFoundException("No tag found with id" + id + "Impossible to delete");
        }
        return "Tag has been successfully deleted";

    }
}
