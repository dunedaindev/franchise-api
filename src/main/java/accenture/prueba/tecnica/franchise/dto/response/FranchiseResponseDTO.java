package accenture.prueba.tecnica.franchise.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FranchiseResponseDTO {
    private final Long id;
    private final String name;
}
