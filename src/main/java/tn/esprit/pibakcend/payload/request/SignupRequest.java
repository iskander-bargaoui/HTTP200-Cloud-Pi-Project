package tn.esprit.pibakcend.payload.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Classe qui contient les paramétres du SignUp  : Création d'un nouveau compte Utilisateur

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  private String nom;

  @NotBlank
  private String prenom;

  @NotBlank
  private String address;

  @NotBlank
  @Size(min = 8, max = 12)
  private String phoneNumber;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  private Date birthDate;

  //    private String verificationCode;
}
