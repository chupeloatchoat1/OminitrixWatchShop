package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.BrandDTO;
import com.example.ominitrixw.entities.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    public BrandDTO addBrand(Brand newBrand);
    public BrandDTO findBrandByID(String id);
    public List<BrandDTO> findAllBrand();
    public boolean updateBrand(Brand brand);
    public boolean deleteBrand(String id);
}
