package ar.edu.utn.frc.tup.lc.iv.dtos.documentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for documentation type creation requests.
 */
@NoArgsConstructor
@Setter
@Getter
public class DocumentationTypeRequestDto {
    /**
     * Name of the documentation type.
     */
    private String name;

    /**
     * Description of the documentation type.
     */
    private String description;
}
