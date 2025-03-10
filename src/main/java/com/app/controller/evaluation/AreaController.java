package com.app.controller.evaluation;

import com.app.entity.evaluation.Area;
import com.app.service.evaluation.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping
    public ResponseEntity<Area> addArea(@RequestBody Area area) {
        Area savedArea = areaService.addArea(area);
        return ResponseEntity.ok(savedArea);
    }
}