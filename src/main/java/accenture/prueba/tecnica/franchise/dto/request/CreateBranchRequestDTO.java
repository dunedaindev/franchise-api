package accenture.prueba.tecnica.franchise.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBranchRequestDTO {
    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String name;
}
