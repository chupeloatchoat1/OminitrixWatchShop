package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.WatchTypeDTO;
import com.example.ominitrixw.entities.WatchType;
import com.example.ominitrixw.repository.WatchTypeRepository;
import com.example.ominitrixw.service.WatchtTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class WatchTypeServiceImpl implements WatchtTypeService {
    @Autowired
    private WatchTypeRepository watchTypeRepository;
    @Autowired
    private ModelMapper modelMapper;

    private WatchTypeDTO convertEntityToDTO(WatchType watchType) {
        WatchTypeDTO watchTypeDTO = new WatchTypeDTO();
        watchTypeDTO = modelMapper.map(watchType, WatchTypeDTO.class);
        return watchTypeDTO;
    }


    @Override
    public WatchTypeDTO addWatchType(WatchType newType) {
        WatchType watchType = watchTypeRepository.findById(newType.getTypeID()).orElse(null);
        if (watchType == null) {
            watchTypeRepository.save(newType);
            return convertEntityToDTO(newType);
        }
        return null;
    }

    @Override
    public WatchTypeDTO findWatchTypeByID(String id) {
        WatchType watchType= watchTypeRepository.findById(id).orElse(null);
        if (watchType == null)
            return null;
        else {
            return convertEntityToDTO(watchType);
        }
    }

    @Override
    public List<WatchTypeDTO> findAllWatchType() {
        return watchTypeRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean updateWatchType(WatchType newWatchType) {
        WatchType watchType = watchTypeRepository.findById(newWatchType.getTypeID()).orElse(null);
        if (watchType != null) {
            watchTypeRepository.save(newWatchType);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteWatchType(String id) {
        if (watchTypeRepository.existsById(id)) {
            watchTypeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
