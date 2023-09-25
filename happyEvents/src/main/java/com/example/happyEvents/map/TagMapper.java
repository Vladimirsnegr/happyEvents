package com.example.happyEvents.map;

import com.example.happyEvents.dto.TagDto;
import com.example.happyEvents.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper {
    public TagDto tagToDto (Tag tag) {
        TagDto dto = new TagDto();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        return dto;
    }

    public Tag dtoToTag (TagDto dto) {
        Tag tag = new Tag();
        tag.setId(dto.getId());
        tag.setName(dto.getName());
        return tag;
    }

    public List<TagDto> tagListToDtoList (List<Tag> tagList) {
        return tagList.stream().map(this::tagToDto).collect(Collectors.toList());
    }
}
