package it.trainingsi2001.rentalcars.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UtenteAuthJWTResponseDTO {

    private String token;

    @Builder.Default
    private String type = "Bearer";

    private String username;
    private String role;

}
