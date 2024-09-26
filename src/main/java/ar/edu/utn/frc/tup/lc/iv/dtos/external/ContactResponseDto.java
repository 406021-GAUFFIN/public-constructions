package ar.edu.utn.frc.tup.lc.iv.dtos.external;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for handling responses related to contact information.
 */

@NoArgsConstructor
@Setter
@Getter
public class ContactResponseDto {


    /**
     * Unique identifier for the contact.
     */
    private Integer id;

    /**
     * Value of the contact, such as phone number or email.
     */
    @JsonProperty("contact_value")
    private String contactValue;

    /**
     * Type of contact, e.g., phone, email, etc.
     */
    @JsonProperty("contact_type")
    private String contactType;
}
