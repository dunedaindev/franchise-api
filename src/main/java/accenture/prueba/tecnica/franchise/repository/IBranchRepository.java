package accenture.prueba.tecnica.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import accenture.prueba.tecnica.franchise.entity.Branch;

public interface IBranchRepository extends JpaRepository<Branch, Long> {

    boolean existsByFranchise_IdAndNameIgnoreCase(Long idFranchise, String name);
    
}
