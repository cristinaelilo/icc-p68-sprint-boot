package ec.edu.ups.icc.fundamentos01.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class PartialUpdateUserDto {

    @Size(min = 3, max = 150)
    private String name;

    @Email
    private String email;

    public PartialUpdateUserDto() {}

    public PartialUpdateUserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}