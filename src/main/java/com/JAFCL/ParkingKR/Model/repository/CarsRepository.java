package com.JAFCL.ParkingKR.Model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JAFCL.ParkingKR.Model.entity.Cars;
@Repository
public interface CarsRepository extends JpaRepository<Cars, Long>{
    Optional<Cars> findByCarPlate(String carPlate);
}
