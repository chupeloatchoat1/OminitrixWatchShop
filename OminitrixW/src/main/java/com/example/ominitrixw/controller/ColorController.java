package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.BrandDTO;
import com.example.ominitrixw.dto.ColorDTO;
import com.example.ominitrixw.entities.Brand;
import com.example.ominitrixw.entities.Color;
import com.example.ominitrixw.service.impl.ColorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/color")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColorController {
    @Autowired
    private ColorServiceImpl colorService;
    @PostMapping(value = "/add")
    public ColorDTO addColor(@RequestBody Color newColor){
        ColorDTO colorDTO;
        try {
            colorDTO = colorService.addColor(newColor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return colorDTO;
    }
    @GetMapping("/{id}")
    public ColorDTO findColorByID(@PathVariable String id){
        ColorDTO colorDTO = new ColorDTO();
        try {
            colorDTO = colorService.findColorByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return colorDTO;
    }
    @GetMapping(value = {""})
    public List<ColorDTO> findAllColor(){
        List<ColorDTO> colorDTOList = new ArrayList<>();
        try {
            colorDTOList = colorService.findAllColor();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return colorDTOList;
    }
    @PutMapping(value = "/update")
    public boolean updateColor(@RequestBody Color newColor){
        try {
            colorService.updateColor(newColor);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @DeleteMapping("/{id}")
    public boolean deleteColor(@PathVariable String id){
        try {
            colorService.deleteColor(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
