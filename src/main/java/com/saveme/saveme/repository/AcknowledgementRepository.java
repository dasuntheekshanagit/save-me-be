package com.saveme.saveme.repository;

import com.saveme.saveme.model.Acknowledgement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcknowledgementRepository extends JpaRepository<Acknowledgement, Long> {
}
