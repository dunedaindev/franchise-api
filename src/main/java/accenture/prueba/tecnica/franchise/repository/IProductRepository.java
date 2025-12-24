package accenture.prueba.tecnica.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import accenture.prueba.tecnica.franchise.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

    boolean existsByBranch_IdAndNameIgnoreCase(Long idBranch, String name);

    boolean existsByIdAndAvailableTrue(Long productId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Product p SET p.available = false WHERE p.id = :productId")
    int logicalDelete(@Param("productId") Long productId);
    
}
