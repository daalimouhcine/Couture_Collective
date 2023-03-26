package com.example.backend.services.tailor;

import com.example.backend.dto.TailorDto;
import com.example.backend.entities.TailorEntity;
import com.example.backend.repositories.TailorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TailorServiceImp implements TailorService {
    @Autowired
    TailorRepository tailorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public TailorDto login(TailorDto tailorDto) {
        TailorEntity tailorEntity = tailorRepository.findByEmail(tailorDto.getEmail());
        if (tailorEntity != null) {
            if (passwordEncoder.matches(tailorDto.getPassword(), tailorEntity.getPassword())) {
                TailorDto loggedInTailorDto = new TailorDto();
                BeanUtils.copyProperties(tailorEntity, loggedInTailorDto);
                return loggedInTailorDto;
            }
        }
        System.out.println("not correct");
        return null;
    }

    @Override
    public List<TailorDto> getAllTailors() {
        List<TailorEntity> tailorEntities = tailorRepository.getAllTailors();
        List<TailorDto> tailorDtos = new ArrayList<TailorDto>();
        for(TailorEntity tailorEntity : tailorEntities) {
            TailorDto tailorDto = new TailorDto();
            BeanUtils.copyProperties(tailorEntity, tailorDto);
            tailorDtos.add(tailorDto);
        }
        return tailorDtos;
    }

    @Override
    public TailorDto addTailor(TailorDto tailorDto) {
        tailorDto.setPassword(passwordEncoder.encode(tailorDto.getPassword()));
        TailorEntity tailorEntity = new TailorEntity();
        BeanUtils.copyProperties(tailorDto, tailorEntity);
        TailorEntity createdTailor = tailorRepository.save(tailorEntity);
        TailorDto createdTailorDto = new TailorDto();
        BeanUtils.copyProperties(createdTailor, createdTailorDto);
        return createdTailorDto;
    }

    @Override
    public TailorDto findTailorByEmail(String email) {
        TailorEntity tailorEntity = tailorRepository.findByEmail(email);
        if(tailorEntity == null) {
            return null;
        }
        TailorDto tailorDto = new TailorDto();
        BeanUtils.copyProperties(tailorEntity, tailorDto);
        return tailorDto;
    }

    @Override
    public TailorDto findTailorById(Long id) {
        TailorEntity tailorEntity = tailorRepository.findById(id).get();
        TailorDto tailorDto = new TailorDto();
        BeanUtils.copyProperties(tailorEntity, tailorDto);
        return tailorDto;
    }

    @Override
    public TailorDto updateTailor(TailorDto tailorDto) {
        TailorEntity tailorEntity = tailorRepository.findById(tailorDto.getId()).get();
        tailorEntity.setName(tailorDto.getName());
        tailorEntity.setEmail(tailorDto.getEmail());
        tailorEntity.setPassword(passwordEncoder.encode(tailorDto.getPassword()));
        TailorEntity updatedTailor = tailorRepository.save(tailorEntity);
        TailorDto updatedTailorDto = new TailorDto();
        BeanUtils.copyProperties(updatedTailor, updatedTailorDto);
        return updatedTailorDto;
    }

    @Override
    public Boolean deleteTailor(Long id) {
        TailorEntity tailorEntity = tailorRepository.findById(id).get();
        if(tailorEntity != null) {
            tailorRepository.delete(tailorEntity);
            return true;
        }
        return false;
    }
}
