package accenture.prueba.tecnica.franchise.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateFranchiseRequestDTO {
    
    @NotBlank(message = "El nombre de la franquicia es obligatorio")
    private String name;
}
