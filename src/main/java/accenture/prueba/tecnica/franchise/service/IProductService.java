package accenture.prueba.tecnica.franchise.service;

import accenture.prueba.tecnica.franchise.dto.request.CreateProductRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateStockRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.ProductResponseDTO;

public interface IProductService {

    ProductResponseDTO addProduct(Long idBranch, CreateProductRequestDTO request);

    void deleteProduct(Long productId);

    ProductResponseDTO updateProductName(Long idProduct, UpdateNameRequestDTO request);

    ProductResponseDTO updateProductStock(Long idProduct, UpdateStockRequestDTO request);
    
}
