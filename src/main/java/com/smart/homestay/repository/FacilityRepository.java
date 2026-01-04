package com.smart.homestay.repository;

import com.smart.homestay.model.Facility;
import com.smart.homestay.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
