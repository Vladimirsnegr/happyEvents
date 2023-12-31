package com.example.happyEvents.rep;

import com.example.happyEvents.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> getTagsByNameContaining(String name);
}
