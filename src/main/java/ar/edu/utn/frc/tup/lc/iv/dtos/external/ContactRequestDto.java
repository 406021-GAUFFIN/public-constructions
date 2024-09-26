package ar.edu.utn.frc.tup.lc.iv.dtos.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ContactRequestDto {

    @JsonProperty("contact_value")
    private String contactValue;

    @JsonProperty(value = "contact_type",access = JsonProperty.Access.READ_ONLY)
    private String contactType;

}
