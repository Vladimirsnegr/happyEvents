package com.example.happyEvents.controller;

import com.example.happyEvents.dto.PlaceDto;
import com.example.happyEvents.dto.TagDto;
import com.example.happyEvents.entity.Tag;
import com.example.happyEvents.map.PlaceMapper;
import com.example.happyEvents.map.TagMapper;
import com.example.happyEvents.service.impl.PlaceServiceImpl;
import com.example.happyEvents.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/place")
public class PlaceController {

    @Autowired
    PlaceServiceImpl placeService;

    @Autowired
    TagServiceImpl tagService;

    @Autowired
    PlaceMapper placeMapper;

    @Autowired
    TagMapper tagMapper;

    @GetMapping
    public List<PlaceDto> getPlaces() {
        return placeMapper.placeListToDtoList(placeService.getPlaces());
    }

    @PostMapping
    public List<PlaceDto> getPlacesByTags(@RequestBody List<TagDto> tagDtos) {
        List<Long> tagIds = tagDtos.stream()
                .map(TagDto::getId)
                .collect(Collectors.toList());

        List<Tag> tags = tagService.getTagsByIds(tagIds);

        return placeMapper.placeListToDtoList(placeService.getPlacesByTags(tags));
    }
    @GetMapping("/{id}")
    public PlaceDto getPlace(@PathVariable Long id) {
        return placeMapper.placeToDto(placeService.getPlace(id));
    }

}
