package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    CreateModelResponse add(CreateModelRequest request);

    UpdateModelResponse update(int id, UpdateModelRequest request);

    GetModelResponse getById(int id);

    List<GetAllModelsResponse> getAll();

    void delete(int id);
}
