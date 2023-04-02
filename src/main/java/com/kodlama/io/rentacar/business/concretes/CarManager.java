package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.CarService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateCarRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateCarRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllCarsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateCarResponse;
import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.kodlama.io.rentacar.entities.enums.State.*;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final ModelMapper mapper;


    @Override
    public CreateCarResponse add(CreateCarRequest request) {
        Car car = mapper.map(request,Car.class);
        car.setId(0);
        repository.save(car);
        CreateCarResponse response = mapper.map(car,CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest request) {
        Car car = mapper.map(request,Car.class);
        car.setId(id);
        repository.save(car);
        UpdateCarResponse updateCarResponse = mapper.map(car,UpdateCarResponse.class);
        return updateCarResponse;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = repository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car,GetCarResponse.class);
        return response;
    }

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();
        List<GetAllCarsResponse> responses = cars.stream()
                .map(car -> mapper.map(car,GetAllCarsResponse.class)).toList();
        return responses;
    }
    @Override
    public List<GetAllCarsResponse> getAllByState(Boolean filter) {
        List<Car> cars = repository.findAll();
        List<GetAllCarsResponse> responses;
        if (filter == false)
        {
            responses = cars.stream().map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        }
        else {
            responses = cars.stream().filter(car -> car.getState().toString() == AVAILABLE.name())
                    .map(car -> mapper.map(car, GetAllCarsResponse.class)).toList();
        }
        return responses;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);

    }

    public void checkIfCarExists(int id) {
        if (!repository.existsById(id)) throw new RuntimeException("Marka bulunamadı");
    }
}
