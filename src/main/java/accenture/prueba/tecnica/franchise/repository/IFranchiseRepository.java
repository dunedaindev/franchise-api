package accenture.prueba.tecnica.franchise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import accenture.prueba.tecnica.franchise.entity.Franchise;

@Repository
public interface IFranchiseRepository  extends JpaRepository<Franchise, Long>{

    boolean existsByNameIgnoreCase(String name);

    Optional<Franchise> findWithBranchesAndProductsById(Long idFranchise);

}
