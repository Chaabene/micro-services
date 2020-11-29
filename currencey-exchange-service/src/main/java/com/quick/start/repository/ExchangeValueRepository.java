package com.quick.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quick.start.beans.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromIgnoreCaseAndToIgnoreCase(String from, String to);
}
