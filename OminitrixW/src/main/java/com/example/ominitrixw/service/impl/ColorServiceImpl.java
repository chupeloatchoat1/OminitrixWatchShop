package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.ColorDTO;
import com.example.ominitrixw.entities.Color;
import com.example.ominitrixw.repository.ColorRepository;
import com.example.ominitrixw.service.ColorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ModelMapper modelMapper;

    private ColorDTO convertEntityToDTO(Color color) {
        ColorDTO colorDTO = new ColorDTO();
        colorDTO = modelMapper.map(color, ColorDTO.class);
        return colorDTO;
    }


    @Override
    public ColorDTO addColor(Color newColor) {
        Color color = colorRepository.findById(newColor.getColorID()).orElse(null);
        if (color == null) {
            colorRepository.save(newColor);
            return convertEntityToDTO(newColor);
        }
        return null;
    }

    @Override
    public ColorDTO findColorByID(String id) {
        Color color= colorRepository.findById(id).orElse(null);
        if (color == null)
            return null;
        else {
            return convertEntityToDTO(color);
        }
    }
    @Override
    public List<ColorDTO> findAllColor() {
        return colorRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updateColor(Color newColor) {
        Color color = colorRepository.findById(newColor.getColorID()).orElse(null);
        if (color != null) {
            colorRepository.save(newColor);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteColor(String id) {
        if (colorRepository.existsById(id)) {
            colorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
