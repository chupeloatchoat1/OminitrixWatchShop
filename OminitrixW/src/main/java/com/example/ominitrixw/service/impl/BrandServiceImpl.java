package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.BrandDTO;
import com.example.ominitrixw.entities.Brand;
import com.example.ominitrixw.repository.BrandRepository;
import com.example.ominitrixw.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;
    private BrandDTO convertEntityToDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO = modelMapper.map(brand, BrandDTO.class);
        return brandDTO;
    }


    @Override
    public BrandDTO addBrand(Brand newBrand) {
//        Brand brand = brandRepository.findById(newBrand.getBrandID()).orElse(null);
//        if (brand == null) {
//            brandRepository.save(newBrand);
//            return convertEntityToDTO(newBrand);
//        }
//        return null;
        brandRepository.save(newBrand);
        return convertEntityToDTO(newBrand);
    }

    @Override
    public BrandDTO findBrandByID(String id) {
        Brand brand= brandRepository.findById(id).orElse(null);
        if (brand == null)
            return null;
        else {
            return convertEntityToDTO(brand);
        }
    }

    @Override
    public List<BrandDTO> findAllBrand() {
        return brandRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updateBrand(Brand newBrand) {
        Brand brand = brandRepository.findById(newBrand.getBrandID()).orElse(null);
        if (brand != null) {
            brandRepository.save(newBrand);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBrand(String id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
