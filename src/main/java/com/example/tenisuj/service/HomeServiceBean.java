package com.example.tenisuj.service;

import org.springframework.stereotype.Service;

@Service
public class HomeServiceBean implements HomeService {
    @Override
    public String getHome() {
        return "Nebud chuj, tenisuj!";
    }
}
