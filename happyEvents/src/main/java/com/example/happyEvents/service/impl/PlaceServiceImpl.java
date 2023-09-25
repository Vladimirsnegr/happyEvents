package com.example.happyEvents.service.impl;

import com.example.happyEvents.entity.Place;
import com.example.happyEvents.entity.Tag;
import com.example.happyEvents.rep.PlaceRepository;
import com.example.happyEvents.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Place getPlace(Long id) {
        return placeRepository.findById(id).orElseThrow(() -> new RuntimeException("Объект не найден"));
    }

    @Override
    public Place savePlace(Place place) {
        validate(place);
        return placeRepository.saveAndFlush(place);
    }

    private void validate(Place place) {
        if (place.getName().isEmpty()||place.getDescription().isEmpty()) {
            throw new RuntimeException("Не все данные заполнены");
        }
    }

    @Override
    public void deletePlace(Long id) {
        if (!placeRepository.existsById(id)) {
            throw new RuntimeException("Объект не найден");
        }
        placeRepository.deleteById(id);
    }

    @Override
    public List<Place> getPlaces() {
        return placeRepository.findAll();
    }

    @Override
    public List<Place> getPlacesByTags(List<Tag> tags) {
        // Строим JPQL-запрос
        String jpql = "SELECT DISTINCT p FROM Place p " +
                "JOIN p.tags t " +
                "WHERE t IN :tags";

        TypedQuery<Place> query = entityManager.createQuery(jpql, Place.class);
        query.setParameter("tags", tags);

        return query.getResultList();
    }
}
