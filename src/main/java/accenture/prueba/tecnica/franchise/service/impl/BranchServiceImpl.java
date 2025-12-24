package accenture.prueba.tecnica.franchise.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import accenture.prueba.tecnica.franchise.dto.request.CreateBranchRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.BranchResponseDTO;
import accenture.prueba.tecnica.franchise.entity.Branch;
import accenture.prueba.tecnica.franchise.entity.Franchise;
import accenture.prueba.tecnica.franchise.exception.BadRequestException;
import accenture.prueba.tecnica.franchise.exception.NotFoundException;
import accenture.prueba.tecnica.franchise.repository.IBranchRepository;
import accenture.prueba.tecnica.franchise.repository.IFranchiseRepository;
import accenture.prueba.tecnica.franchise.service.IBranchService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BranchServiceImpl implements IBranchService {

    private final IFranchiseRepository franchiseRepository;
    private final IBranchRepository branchRepository;

    @Transactional
    @Override
    public BranchResponseDTO addBranchInFranchise(Long idFranchise, CreateBranchRequestDTO branchRequest) {

        Franchise franchise = franchiseRepository.findById(idFranchise)
                .orElseThrow(() -> new NotFoundException("Franquicia no encontrada: " + idFranchise));

        if (branchRepository.existsByFranchise_IdAndNameIgnoreCase(idFranchise, branchRequest.getName())) {
            throw new BadRequestException("Ya existe una sucursal con ese nombre en la franquicia");
        }

        Branch branch = Branch.builder()
                .name(branchRequest.getName().trim())
                .franchise(franchise)
                .build();

        Branch saved = branchRepository.save(branch);

        return BranchResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .franchiseId(idFranchise)
                .build();
    }

    @Transactional
    @Override
    public BranchResponseDTO updateBranchName(Long branchId, UpdateNameRequestDTO request) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada: " + branchId));

        String newName = request.getName().trim();

        if (!branch.getName().equalsIgnoreCase(newName)
                && branchRepository.existsByFranchise_IdAndNameIgnoreCase(branch.getFranchise().getId(), newName)) {
            throw new BadRequestException("Ya existe una sucursal con ese nombre en la franquicia");
        }

        branch.setName(newName);

        return BranchResponseDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .franchiseId(branch.getFranchise().getId())
                .build();
    }

}
