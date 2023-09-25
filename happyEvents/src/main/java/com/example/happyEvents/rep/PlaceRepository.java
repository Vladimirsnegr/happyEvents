package com.example.happyEvents.rep;

import com.example.happyEvents.entity.Place;
import com.example.happyEvents.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> getPlacesByName(String name);

    List<Place> getPlacesByTagsIn(List<Long> tagIds);
}
