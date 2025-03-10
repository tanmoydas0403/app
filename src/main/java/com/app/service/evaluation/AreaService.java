package com.app.service.evaluation;

import com.app.entity.evaluation.Area;
import com.app.repository.evaluation.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public Area addArea(Area area) {
        return areaRepository.save(area);
    }
}