package accenture.prueba.tecnica.franchise.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import accenture.prueba.tecnica.franchise.dto.request.CreateBranchRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.BranchResponseDTO;
import accenture.prueba.tecnica.franchise.service.IBranchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/branch")
@AllArgsConstructor
public class BranchController {

    private final IBranchService branchService;

    @PostMapping("/{idFranchise}/add-branch-to-franchise")
    public ResponseEntity<BranchResponseDTO> addBranchInFranchise(@PathVariable Long idFranchise, @RequestBody @Valid CreateBranchRequestDTO branchRequest) {
        BranchResponseDTO response = branchService.addBranchInFranchise(idFranchise, branchRequest);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{idBranch}/update-name")
    public ResponseEntity<BranchResponseDTO> updateBranchName(@PathVariable Long idBranch, @RequestBody @Valid UpdateNameRequestDTO request) {
        BranchResponseDTO response = branchService.updateBranchName(idBranch, request);
        return ResponseEntity.ok(response);
    }

}
