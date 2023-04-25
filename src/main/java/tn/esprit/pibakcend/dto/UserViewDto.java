package tn.esprit.pibakcend.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDto {
    private Long id;
    private String nom;
    private String prenom;

    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private Date birthDate ;
}
