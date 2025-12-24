package accenture.prueba.tecnica.franchise.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import accenture.prueba.tecnica.franchise.dto.request.CreateFranchiseRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.FranchiseResponseDTO;
import accenture.prueba.tecnica.franchise.dto.response.MaxStockProductByBranchResponse;
import accenture.prueba.tecnica.franchise.entity.Franchise;
import accenture.prueba.tecnica.franchise.entity.Product;
import accenture.prueba.tecnica.franchise.exception.BadRequestException;
import accenture.prueba.tecnica.franchise.exception.NotFoundException;
import accenture.prueba.tecnica.franchise.repository.IFranchiseRepository;
import accenture.prueba.tecnica.franchise.service.IFranchiseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements IFranchiseService {

    private final IFranchiseRepository franchiseRepository;

    @Transactional
    @Override
    public FranchiseResponseDTO createFranchise(CreateFranchiseRequestDTO requestDTO) {

        if (franchiseRepository.existsByNameIgnoreCase(requestDTO.getName())) {
            throw new BadRequestException("Ya existe una franquicia con ese nombre");
        }

        Franchise franchise = Franchise.builder()
                .name(requestDTO.getName().trim())
                .build();

        Franchise saved = franchiseRepository.save(franchise);

        return FranchiseResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();
    }

    @Override
    @Transactional
    public FranchiseResponseDTO updateFranchiseName(Long idFranchise, UpdateNameRequestDTO requestDTO) {

        Franchise franchise = franchiseRepository.findById(idFranchise)
                .orElseThrow(() -> new BadRequestException("Franquicia no encontrada: " + idFranchise));

        franchise.setName(requestDTO.getName().trim());
        franchiseRepository.save(franchise);

        return FranchiseResponseDTO.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaxStockProductByBranchResponse> getMaxStockProductsByBranch(Long idFranchise) {
        Franchise franchise = franchiseRepository.findWithBranchesAndProductsById(idFranchise)
                .orElseThrow(() -> new NotFoundException("Franquicia no encontrada: " + idFranchise));

        return franchise.getBranches().stream()
                .map(branch -> branch.getProducts().stream()
                        .filter(p -> p.getAvailable())
                        .max(Comparator.comparing(Product::getStock))
                        .map(product -> MaxStockProductByBranchResponse.builder()
                        .branchId(branch.getId())
                        .branchName(branch.getName())
                        .productId(product.getId())
                        .productName(product.getName())
                        .stock(product.getStock())
                        .build())
                        .orElse(null)
                )
                .filter(x -> x != null)
                .toList();
    }

}
