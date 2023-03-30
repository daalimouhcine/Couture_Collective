package com.example.backend.controllers;

import com.example.backend.responses.TailorResponse;
import com.example.backend.services.tailor.TailorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tailor")
public class TailorController {

    @Autowired
    private TailorService tailorService;

    @GetMapping("/all")
    public List<TailorResponse> getAllTailors() {
        return null;
    }
}
