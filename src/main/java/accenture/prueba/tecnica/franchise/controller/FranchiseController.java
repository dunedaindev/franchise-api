package accenture.prueba.tecnica.franchise.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import accenture.prueba.tecnica.franchise.dto.request.CreateFranchiseRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.FranchiseResponseDTO;
import accenture.prueba.tecnica.franchise.dto.response.MaxStockProductByBranchResponse;
import accenture.prueba.tecnica.franchise.service.IFranchiseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/franchise")
@AllArgsConstructor
public class FranchiseController {

    private final IFranchiseService franchiseService;

    @PostMapping("/create")
    public ResponseEntity<FranchiseResponseDTO> createFranchise(@RequestBody CreateFranchiseRequestDTO requestDTO) {

        FranchiseResponseDTO response = franchiseService.createFranchise(requestDTO);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/update/{idFranchise}")
    public ResponseEntity<FranchiseResponseDTO> updateFranchiseName(@PathVariable Long idFranchise, @RequestBody @Valid UpdateNameRequestDTO requestDTO) {
        
        FranchiseResponseDTO response = franchiseService.updateFranchiseName(idFranchise, requestDTO);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idFranchise}/products/max-stock")
    public ResponseEntity<List<MaxStockProductByBranchResponse>> getMaxStockProductsByBranch(@PathVariable Long idFranchise) {
        
        List<MaxStockProductByBranchResponse> response = franchiseService.getMaxStockProductsByBranch(idFranchise);
        
        return ResponseEntity.ok(response);
    }
    
}
