package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.create.CreateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateMaintenanceStatusForCarRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateMaintenanceStatusForCarResponse;

import java.util.List;

public interface MaintenanceService {
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);
    GetMaintenanceResponse getById(int id);
    List<GetAllMaintenancesResponse> getAll();
    UpdateMaintenanceStatusForCarResponse returnFromMaintenance(int id,UpdateMaintenanceStatusForCarRequest request);
    void delete(int id);

}
