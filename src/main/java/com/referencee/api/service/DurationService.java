package com.referencee.api.service;

import com.referencee.api.model.Duration;
import com.referencee.api.repository.DurationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DurationService {
    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private DurationRepository repository;

    public List<Duration> findAll() {
        return (List<Duration>) repository.findAll();
    }
}
