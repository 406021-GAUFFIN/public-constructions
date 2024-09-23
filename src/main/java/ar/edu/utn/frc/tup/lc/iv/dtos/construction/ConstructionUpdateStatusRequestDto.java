package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ConstructionUpdateStatusRequestDto {

    @JsonProperty("construction-id")
    private Long constructionId;
    private ConstructionStatus status;
}
