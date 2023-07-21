package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.WatchDTO;
import com.example.ominitrixw.entities.Watch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchService {
    public WatchDTO addWatch(WatchDTO watchDTO);
    public List<WatchDTO> findAllWatch();
    public WatchDTO getWatchByID(String id);
    public WatchDTO updateWatch(WatchDTO watchDTO);
    //public boolean deleteWatch(String id);
    public List<WatchDTO> findWatchByTypeID(String id);
    public List<WatchDTO> getWatchByName(String name);

    void updateQuantity(String id,int buyQuantity);
    public boolean updateSTT(String id, boolean newSTT);
    public List<Double> getRevenueWatchByMonth(int month);


}
