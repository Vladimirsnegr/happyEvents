package com.example.happyEvents.service.impl;

import com.example.happyEvents.entity.Tag;
import com.example.happyEvents.rep.TagRepository;
import com.example.happyEvents.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getTagsByIds(List<Long> ids) {
        return tagRepository.findAllById(ids);
    }

    @Override
    public List<Tag> getTagsByName(String name) {
        return tagRepository.getTagsByNameContaining(name);
    }
}
