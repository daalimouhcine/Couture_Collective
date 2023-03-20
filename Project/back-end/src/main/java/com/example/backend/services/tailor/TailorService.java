package com.example.backend.services.tailor;

import com.example.backend.dto.TailorDto;

import java.util.List;

public interface TailorService {
    TailorDto login(TailorDto tailorDto);
    List<TailorDto> getAllTailors();
    TailorDto addTailor(TailorDto tailorDto);
    TailorDto findTailorByEmail(String email);
    TailorDto findTailorById(Long id);
    TailorDto updateTailor(TailorDto tailorDto);
    Boolean deleteTailor(Long id);
}
