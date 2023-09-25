package com.example.happyEvents.service;

import com.example.happyEvents.entity.Place;
import com.example.happyEvents.entity.Tag;

import java.util.List;

public interface PlaceService {
    Place getPlace(Long id);
    Place savePlace(Place place);
    void deletePlace(Long id);
    List<Place> getPlaces();

    List<Place> getPlacesByTags(List<Tag> tags);
}
