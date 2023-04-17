package project.management.usersmanagement.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PasswordRequest {
    private String CurrentPassword;

    private String NewPassword;

}
