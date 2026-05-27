package com.JAFCL.ParkingKR.Model.dto;

import lombok.Data;

@Data
public class GlobalResponse<T> {
    private String message;
    private T data;
}
