package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.ModelService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateModelRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateModelRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllModelsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetModelResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateModelResponse;
import com.kodlama.io.rentacar.business.rules.ModelBusinessRules;
import com.kodlama.io.rentacar.entities.Model;
import com.kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelMapper mapper;
    private final ModelBusinessRules rules;

    @Override
    public CreateModelResponse add(CreateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        model.setId(0);
        repository.save(model);
        CreateModelResponse response = mapper.map(model, CreateModelResponse.class);
        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest request) {
        Model model = mapper.map(request, Model.class);
        model.setId(id);
        rules.checkIfModelExists(id);
        repository.save(model);
        UpdateModelResponse updateModelResponse = mapper.map(model, UpdateModelResponse.class);
        return updateModelResponse;
    }

    @Override
    public GetModelResponse getById(int id) {
        rules.checkIfModelExists(id);
        Model model = repository.findById(id).orElseThrow();
        GetModelResponse response = mapper.map(model, GetModelResponse.class);
        return response;
    }

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        List<GetAllModelsResponse> responses = models
                .stream()
                .map(model -> mapper.map(model, GetAllModelsResponse.class))
                .toList();
        return responses;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);

    }


}
