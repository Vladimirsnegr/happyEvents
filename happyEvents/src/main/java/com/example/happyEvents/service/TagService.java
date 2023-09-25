package com.example.happyEvents.service;

import com.example.happyEvents.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags();

    List<Tag> getTagsByIds(List<Long> ids);
}
