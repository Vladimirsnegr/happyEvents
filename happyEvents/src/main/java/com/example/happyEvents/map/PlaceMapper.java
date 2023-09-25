package com.example.happyEvents.map;

import com.example.happyEvents.dto.PlaceDto;
import com.example.happyEvents.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlaceMapper {
    @Autowired
    TagMapper tagMapper;
    public PlaceDto placeToDto (Place place) {
        PlaceDto dto = new PlaceDto();
        dto.setId(place.getId());
        dto.setName(place.getName());
        dto.setDescription(place.getDescription());
        dto.setAddress(place.getAddress());
        dto.setPhoneNumber(place.getPhoneNumber());
        dto.setRating(place.getRating());
        dto.setPhotoLink(place.getPhotoLink());

        dto.setTags(place.getTags().stream().map(a -> tagMapper.tagToDto(a)).collect(Collectors.toList()));
        return dto;
    }

    public Place dtoToPlace (PlaceDto dto) {
        Place place = new Place();
        place.setId(dto.getId());
        place.setName(dto.getName());
        place.setDescription(dto.getDescription());
        place.setAddress(dto.getAddress());
        place.setPhoneNumber(dto.getPhoneNumber());
        place.setRating(dto.getRating());
        //place.setTags(dto.getTags());
        return place;
    }

    public List<PlaceDto> placeListToDtoList (List<Place> placeList) {
        return placeList.stream().map(this::placeToDto).collect(Collectors.toList());
    }
}
