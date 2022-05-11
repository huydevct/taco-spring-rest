package tacos.security;

import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotNull
    @Size(min = 5, message = "Username must be at least 5 characters long")
    private String username;

    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long")
    private String password;

    @NotNull
    @Size(min = 3, message = "Password must be at least 3 characters long")
    @Transient
    private String confirmPassword;

    @NotBlank(message = "Fullname is required")
    private String fullname;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Province is required")
    private String province;
    @NotBlank(message = "Zip is required")
    private String zip;
    @NotBlank(message = "PhoneNumber is required")
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password),
                fullname, street, city, province, zip, phoneNumber);
    }
}


