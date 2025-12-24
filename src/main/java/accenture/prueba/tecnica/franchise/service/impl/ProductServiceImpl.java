package accenture.prueba.tecnica.franchise.service.impl;

import java.util.Objects;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import accenture.prueba.tecnica.franchise.dto.request.CreateProductRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateStockRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.ProductResponseDTO;
import accenture.prueba.tecnica.franchise.entity.Branch;
import accenture.prueba.tecnica.franchise.entity.Product;
import accenture.prueba.tecnica.franchise.exception.BadRequestException;
import accenture.prueba.tecnica.franchise.exception.NotFoundException;
import accenture.prueba.tecnica.franchise.repository.IBranchRepository;
import accenture.prueba.tecnica.franchise.repository.IProductRepository;
import accenture.prueba.tecnica.franchise.service.IProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IBranchRepository branchRepository;
    private final IProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDTO addProduct(Long idBranch, CreateProductRequestDTO request) {
        Branch branch = branchRepository.findById(idBranch)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada: " + idBranch));

        if (productRepository.existsByBranch_IdAndNameIgnoreCase(idBranch, request.getName())) {
            throw new BadRequestException("Ya existe un producto con ese nombre en la sucursal");
        }

        Product product = Product.builder()
                .name(request.getName().trim())
                .available(true)
                .stock(request.getStock())
                .branch(branch)
                .build();

        Product saved = productRepository.save(product);

        return ProductResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .stock(saved.getStock())
                .branchId(idBranch)
                .build();
    }

    @Override
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    public void deleteProduct(Long productId) {
        if (!productRepository.existsByIdAndAvailableTrue(productId)) {
            throw new NotFoundException("Producto no encontrado: " + productId);
        }

        productRepository.logicalDelete(productId);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProductName(Long idProduct, UpdateNameRequestDTO request) {
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + idProduct));

        String newName = request.getName().trim();

        if (!product.getName().equalsIgnoreCase(newName)
                && productRepository.existsByBranch_IdAndNameIgnoreCase(product.getBranch().getId(), newName)) {
            throw new BadRequestException("Ya existe un producto con ese nombre en la sucursal");
        }

        product.setName(newName);

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .stock(product.getStock())
                .branchId(product.getBranch().getId())
                .build();
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProductStock(Long idProduct, UpdateStockRequestDTO request) {
        
        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + idProduct));
        if (request.getStock() < 0 || request.getStock() == null || Objects.equals(request.getStock(), product.getStock())) {
            throw new BadRequestException("El stock no puede ser negativo");
        }

        product.setStock(request.getStock());
        Product productUpdated = productRepository.save(product);

        return ProductResponseDTO.builder()
                .id(productUpdated.getId())
                .name(productUpdated.getName())
                .stock(productUpdated.getStock())
                .branchId(productUpdated.getBranch().getId())
                .build();
    }
    
}
