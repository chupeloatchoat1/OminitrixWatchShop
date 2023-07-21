package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.WatchTypeDTO;
import com.example.ominitrixw.entities.WatchType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchtTypeService {
    public WatchTypeDTO addWatchType(WatchType newType);
    public WatchTypeDTO findWatchTypeByID(String id);
    public List<WatchTypeDTO> findAllWatchType();
    public boolean updateWatchType(WatchType watchType);
    public boolean deleteWatchType(String id);
}
