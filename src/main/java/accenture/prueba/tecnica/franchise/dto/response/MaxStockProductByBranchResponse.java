package accenture.prueba.tecnica.franchise.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MaxStockProductByBranchResponse {

    private final Long branchId;
    private final String branchName;

    private final Long productId;
    private final String productName;
    private final Integer stock;
}