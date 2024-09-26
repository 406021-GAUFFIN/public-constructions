package ar.edu.utn.frc.tup.lc.iv.dtos.external;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ContactResponseDto {

    private Integer id;

    @JsonProperty("contact_value")
    private String contactValue;

    @JsonProperty("contact_type")
    private String contactType;
}
