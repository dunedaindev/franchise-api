package accenture.prueba.tecnica.franchise.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import accenture.prueba.tecnica.franchise.dto.request.CreateProductRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateNameRequestDTO;
import accenture.prueba.tecnica.franchise.dto.request.UpdateStockRequestDTO;
import accenture.prueba.tecnica.franchise.dto.response.ProductResponseDTO;
import accenture.prueba.tecnica.franchise.service.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping("/create/{idBranch}")
    public ResponseEntity<ProductResponseDTO> addProduct(@PathVariable Long idBranch, @RequestBody @Valid CreateProductRequestDTO request) {
        ProductResponseDTO response = productService.addProduct(idBranch, request);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/name/{idProduct}")
    public ResponseEntity<ProductResponseDTO> updateName(@PathVariable Long idProduct, @RequestBody @Valid UpdateNameRequestDTO request) {
        ProductResponseDTO response = productService.updateProductName(idProduct, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/stock/{idProduct}")
    public ResponseEntity<ProductResponseDTO> updateStock(@PathVariable Long idProduct, @RequestBody @Valid UpdateStockRequestDTO request) {
        ProductResponseDTO response = productService.updateProductStock(idProduct, request);
        return ResponseEntity.ok(response);
    }
}
