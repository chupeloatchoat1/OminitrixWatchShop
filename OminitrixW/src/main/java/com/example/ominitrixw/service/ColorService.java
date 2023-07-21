package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.ColorDTO;
import com.example.ominitrixw.entities.Color;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorService {
    public ColorDTO addColor(Color newColor);
    public ColorDTO findColorByID(String id);
    public List<ColorDTO> findAllColor();
    public boolean updateColor(Color color);
    public boolean deleteColor(String id);
}
