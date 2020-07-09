//package com.epam.mjc.service;
//
//import com.epam.mjc.dao.TagDao;
//import com.epam.mjc.dao.entity.Tag;
//import com.epam.mjc.service.exception.NotFoundException;
//import com.epam.mjc.service.impl.TagServiceImpl;
//import com.epam.mjc.service.validator.Validator;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TagServiceTest {
//    @Mock
//    private TagDao dao;
//    @Mock
//    private Validator validator = mock(Validator.class);
//    @InjectMocks
//    private TagServiceImpl service;
//
//    public static final long ID = 1;
//    public static final long INVALID_ID = 1111111;
//    public static final Tag TAG = new Tag(1L, "fun");
//    public static final List<Tag> TAG_LIST = new ArrayList<>();
//
//    @Before
//    public void setUp() {
//        service = new TagServiceImpl(dao);
//        System.out.println(validator.getClass());
//    }
//
//    @Test
//    public void getTagByValidIdShouldReturnTagObjectTest()  {
//        when(dao.getById(ID)).thenReturn(TAG);
//        Tag actualTag = service.getTagById(ID);
//        Assert.assertEquals(TAG, actualTag);
//        verify(dao, times(1)).getById(ID);
//    }
//    @Test(expected = NotFoundException.class)
//    public void getTagByInValidIdShouldReturnNotFoundExceptionTest()  {
//        when(dao.getById(INVALID_ID)).thenReturn(null);
//        service.getTagById(INVALID_ID);
//        verify(dao, times(1)).getById(ID);
//    }
//
//    @Test
//    public void getAllTagsTest() {
//        when(dao.getAll()).thenReturn(TAG_LIST);
//        List<Tag> actualTagList = service.getAllTags();
//        Assert.assertEquals(TAG_LIST, actualTagList);
//        verify(dao, times(1)).getAll();
//    }
//}
