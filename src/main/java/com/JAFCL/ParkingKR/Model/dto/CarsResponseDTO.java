package com.JAFCL.ParkingKR.Model.dto;

import lombok.Data;

@Data
public class CarsResponseDTO {
    private Long id;
    private String carPlate;
    private int hours;
}
