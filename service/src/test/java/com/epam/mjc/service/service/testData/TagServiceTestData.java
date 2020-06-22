package com.epam.mjc.service.service.testData;

import com.epam.mjc.dao.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagServiceTestData {
    public static final long ID = 1;
    public static final Tag TAG = new Tag(Long.valueOf(1), "fun");
    public static final List<Tag> TAG_LIST = new ArrayList<>();
}
