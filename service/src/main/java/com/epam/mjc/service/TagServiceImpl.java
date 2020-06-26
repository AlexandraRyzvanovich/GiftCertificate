package com.epam.mjc.service;

import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.entity.Tag;
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

    public Tag getTagById(long id) {
        Tag tag = tagDao.getById(id);
        if(tag == null) {
            throw new NotFoundException("No tag found with id" + id);
        }

        return tag;
    }

    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    public Tag createTag(Tag tag ) {
            Validator.validateTag(tag);
            Long tagId = tagDao.create(tag);
            if(tagId == null) {
                throw new IncorrectParamsException("Impossible to create tag with given params");
            }
            return tagDao.getById(tagId);

    }

    public boolean deleteTagById(long id) throws NotFoundException {
        boolean result = tagDao.deleteById(id);
        if(!result) {
            throw new NotFoundException("No tag found with id" + id + "Impossible to delete");
        }
        return true;

    }
}
