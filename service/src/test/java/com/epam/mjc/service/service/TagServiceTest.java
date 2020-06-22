package com.epam.mjc.service.service;

import com.epam.mjc.dao.dao.TagDao;
import com.epam.mjc.dao.entity.Tag;
import com.epam.mjc.dao.exception.DaoNotFoundException;
import com.epam.mjc.service.validator.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.epam.mjc.service.service.testData.TagServiceTestData.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {
    @Mock
    private TagDao dao;
    @Mock
    private Validator validator = mock(Validator.class);
    @InjectMocks
    private TagServiceImpl service;

    @Before
    public void setUp() {
        service = new TagServiceImpl(dao, validator);
        System.out.println(validator.getClass());
    }

    @Test
    public void getTagByIdTest() throws DaoNotFoundException {
        when(dao.getById(ID)).thenReturn(TAG);
        Tag actualTag = service.getTagById(ID);
        Assert.assertEquals(TAG, actualTag);
        verify(dao, times(1)).getById(ID);
    }

    @Test
    public void getAllTagsTest() {
        when(dao.getAll()).thenReturn(TAG_LIST);
        List<Tag> actualTagList = service.getAllTags();
        Assert.assertNotEquals(TAG_LIST, actualTagList);
        verify(dao, times(1)).getAll();
    }

    @Test
    public void deleteTagByIdTest() throws DaoNotFoundException {
        when(dao.deleteById(ID)).thenReturn(true);
        Assert.assertTrue(service.deleteTagById(ID));
        verify(dao, times(1)).deleteById(ID);
    }
}
