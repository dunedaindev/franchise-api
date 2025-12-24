package accenture.prueba.tecnica.franchise.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDTO {
    private final Long id;
    private final String name;
    private final Integer stock;
    private final Long branchId;
}
