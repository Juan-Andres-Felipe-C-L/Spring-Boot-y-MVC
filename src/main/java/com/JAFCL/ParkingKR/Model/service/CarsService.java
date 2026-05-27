package com.JAFCL.ParkingKR.Model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.JAFCL.ParkingKR.Model.dto.CarsResponseDTO;
import com.JAFCL.ParkingKR.Model.dto.GlobalResponse;
import com.JAFCL.ParkingKR.Model.dto.MessageResponseDTO;
import com.JAFCL.ParkingKR.Model.dto.RegisterRequestDTO;
import com.JAFCL.ParkingKR.Model.entity.Cars;
import com.JAFCL.ParkingKR.Model.repository.CarsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarsService {
    
     private final CarsRepository carsRepository;

    public MessageResponseDTO createCar(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Cars> carFound = carsRepository.findByCarPlate(request.getCarPlate());

        if(carFound.isPresent()) {
            response.setMessage("Ya existe un carro con la paca escrita.");
            return response;
        }

        response.setMessage("Registro de carro exitoso.");
        Cars car = new Cars();
        car.setCarPlate(request.getCarPlate());
        car.setHours(request.getHours());
        carsRepository.save(car);

        return response;
    }

    public List<CarsResponseDTO> getCars() {
        List<CarsResponseDTO> listCars = new ArrayList<>();
        List<Cars> carsFound = carsRepository.findAll();

        for (Cars car : carsFound) {
            CarsResponseDTO carNew = new CarsResponseDTO();
            carNew.setId(car.getId());
            carNew.setCarPlate(car.getCarPlate());
            carNew.setHours(car.getHours());
            
            listCars.add(carNew);
        }

        return listCars;
    }

    public GlobalResponse<CarsResponseDTO> getCarById(Long id) {
        GlobalResponse<CarsResponseDTO> response = new GlobalResponse<>();
        Optional<Cars> carFound = carsRepository.findById(id);

        if (carFound.isEmpty()) {
            response.setMessage("Automóvil no encontrado.");
            return response;
        }

        Cars car = carFound.get();

        CarsResponseDTO carFinal = new CarsResponseDTO();
        carFinal.setId(car.getId());
        carFinal.setCarPlate(car.getCarPlate());
        carFinal.setHours(car.getHours());
        
        response.setMessage("Carro encontrado.");
        response.setData(carFinal);

        return response;
    }

    public MessageResponseDTO deleteCar(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Cars> carFound = carsRepository.findById(id);

        if (carFound.isEmpty()) {
            response.setMessage("Carro no encontrado.");
            return response;
        }

        carsRepository.deleteById(id);
        response.setMessage("Carro eliminado exitosamente.");
        return response;
    }

    public GlobalResponse<CarsResponseDTO> upDateCar(Long id, RegisterRequestDTO request) {
        GlobalResponse<CarsResponseDTO> response = new GlobalResponse<>();

        Optional<Cars> carFound = carsRepository.findById(id);

        if(carFound.isEmpty()){
            response.setMessage("Carro no encontrado.");
            return response;
        }

        Cars car = carFound.get();
        car.setCarPlate(request.getCarPlate());
        car.setHours(request.getHours());
    
        carsRepository.save(car);

        CarsResponseDTO carsResponseDTO = new CarsResponseDTO();
        carsResponseDTO.setId(car.getId());
        carsResponseDTO.setCarPlate(car.getCarPlate());
        carsResponseDTO.setHours(car.getHours());

        response.setMessage("Carro actualizado correctamente.");
        response.setData(carsResponseDTO);
        return response;
    }
}