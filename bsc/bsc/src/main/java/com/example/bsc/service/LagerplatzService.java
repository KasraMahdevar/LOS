package com.example.bsc.service;

import com.example.bsc.repo.LagerplatzRepo;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class LagerplatzService {

    private LagerplatzRepo lagerplatzRepo;
}
