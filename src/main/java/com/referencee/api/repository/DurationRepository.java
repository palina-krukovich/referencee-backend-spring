package com.referencee.api.repository;

import com.referencee.api.model.Duration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DurationRepository extends PagingAndSortingRepository<Duration, Integer> {
}
