package tn.esprit.pibakcend.payload.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtLogin {

    private String username;
    private String nom;
    private String prenom;

    private String password;

}