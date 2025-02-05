package com.walking.repository;

import com.walking.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkRepository extends JpaRepository<Walk,Long> {

}
