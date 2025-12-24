package accenture.prueba.tecnica.franchise.service;

import java.util.List;

import accenture.prueba.tecnica.franchise.dto.request.CreateFranchiseRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.FranchiseResponseDTO;
import accenture.prueba.tecnica.franchise.dto.response.MaxStockProductByBranchResponse;

public interface IFranchiseService {

    FranchiseResponseDTO createFranchise(CreateFranchiseRequestDTO requestDTO);

    FranchiseResponseDTO updateFranchiseName(Long idFranchise, UpdateNameRequestDTO requestDTO);

    List<MaxStockProductByBranchResponse> getMaxStockProductsByBranch(Long idFranchise);
    
}
