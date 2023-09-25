package com.example.happyEvents.controller;

import com.example.happyEvents.dto.TagDto;
import com.example.happyEvents.map.TagMapper;
import com.example.happyEvents.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    TagServiceImpl tagService;

    @Autowired
    TagMapper tagMapper;

    @GetMapping
    public List<TagDto> getTags() {
        return tagMapper.tagListToDtoList(tagService.getTags());
    }

    @GetMapping("/{name}")
    public List<TagDto> getTagsByName(@PathVariable String name) {
        return tagMapper.tagListToDtoList(tagService.getTagsByName(name));
    }
}
