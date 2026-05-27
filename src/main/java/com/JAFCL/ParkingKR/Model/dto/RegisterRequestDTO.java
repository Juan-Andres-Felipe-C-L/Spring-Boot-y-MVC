package com.JAFCL.ParkingKR.Model.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    
    private String carPlate;

    private int hours;
}
