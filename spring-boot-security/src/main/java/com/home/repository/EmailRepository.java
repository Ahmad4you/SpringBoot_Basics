package com.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.model.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {
}