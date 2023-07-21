package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.AccountDTO;
import com.example.ominitrixw.dto.BrandDTO;
import com.example.ominitrixw.entities.Brand;
import com.example.ominitrixw.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/brand")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BrandController {
    @Autowired
    private BrandServiceImpl brandService;
    @PostMapping(value = "/add")
    public BrandDTO addBrand(@RequestBody Brand newBrand){
        BrandDTO brandDTO;
        try {
            brandDTO = brandService.addBrand(newBrand);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return brandDTO;
    }
    @GetMapping("/{id}")
    public BrandDTO findBrandByID(@PathVariable String id){
        BrandDTO brandDTO = new BrandDTO();
        try {
            brandDTO = brandService.findBrandByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return brandDTO;
    }
    @GetMapping(value = {""})
    public List<BrandDTO> findAllBrand(){
        List<BrandDTO> brandDTOList = new ArrayList<>();
        try {
            brandDTOList = brandService.findAllBrand();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return brandDTOList;
    }
    @PutMapping(value = "/update")
    public boolean updateBrand(@RequestBody Brand newBrand){
        try {
            brandService.updateBrand(newBrand);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @DeleteMapping("/{id}")
    public boolean deleteBrand(@PathVariable String id){
        try {
            brandService.deleteBrand(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
