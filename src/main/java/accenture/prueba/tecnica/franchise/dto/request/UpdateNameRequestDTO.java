package accenture.prueba.tecnica.franchise.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNameRequestDTO {
        
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
}
