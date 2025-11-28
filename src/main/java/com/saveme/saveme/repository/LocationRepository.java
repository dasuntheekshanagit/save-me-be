package com.saveme.saveme.repository;

import com.saveme.saveme.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
