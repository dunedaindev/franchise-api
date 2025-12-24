package accenture.prueba.tecnica.franchise.service;

import accenture.prueba.tecnica.franchise.dto.request.CreateBranchRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.BranchResponseDTO;

public interface IBranchService {

    BranchResponseDTO addBranchInFranchise(Long idFranchise, CreateBranchRequestDTO branchRequest);
    BranchResponseDTO updateBranchName(Long branchId, UpdateNameRequestDTO request);
}
