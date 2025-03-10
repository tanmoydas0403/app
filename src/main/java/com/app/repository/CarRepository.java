package com.app.repository;

import com.app.entity.cars.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(
            "SELECT c FROM Car c " +
                    "JOIN c.brand b " +
                    "JOIN c.transmission t " +
                    "JOIN c.model m " +
                    "JOIN c.year y " +
                    "WHERE b.name = :details or t.type = :details or m.name = :details or y.year = :details"
    )
    Page<Car> searchCar(
            @Param("details") String details,
            Pageable pageable
    );
}