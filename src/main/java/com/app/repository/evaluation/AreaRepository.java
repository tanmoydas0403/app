package com.app.repository.evaluation;

import com.app.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByPinCode(long pinCode);
}