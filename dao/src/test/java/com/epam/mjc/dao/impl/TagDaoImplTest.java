package com.epam.mjc.dao.impl;

import com.epam.mjc.dao.TagDao;
import com.epam.mjc.dao.TestConfig;
import com.epam.mjc.dao.entity.TagEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@ActiveProfiles("test")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/migration/insert-data.sql")
public class TagDaoImplTest {

    private static final String EXPECTED_BY_NAME_TAG = "FUN";
    private static final Long TAG_ID = 1L;

    @Autowired
    private TagDao tagDao;

    @Test
    public void getTagsCount() {
        Assert.assertEquals(5, tagDao.countTags());
    }

    @Test
    public void getTagsByName() {
        Optional<TagEntity> tag = tagDao.getByName(EXPECTED_BY_NAME_TAG);
        Assert.assertTrue(tag.isPresent());
        Assert.assertEquals(EXPECTED_BY_NAME_TAG, tag.get().getName());
    }

    @Test
    public void getAllTags() {
        List<TagEntity> allTags = tagDao.getAll(10000, 1);
        Assert.assertEquals(5, allTags.size());
    }


}
