package ar.edu.utn.frc.tup.lc.iv.dtos.external;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * DTO for handling contact information requests.
 */
@Setter
@Getter
@NoArgsConstructor
public class ContactRequestDto {

    /**
     * Value of the contact, such as phone number or email.
     */
    @JsonProperty("contact_value")
    private String contactValue;

    /**
     * Type of contact, which is read-only and set internally.
     */
    @JsonProperty(value = "contact_type", access = JsonProperty.Access.READ_ONLY)
    private String contactType;

}

