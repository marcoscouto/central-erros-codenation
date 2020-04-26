package com.github.marcoscouto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.marcoscouto.domain.User;
import com.github.marcoscouto.domain.enums.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    @CPF(message = "CPF precisa ser válido")
    @Size(max = 11, min = 11, message = "CPF deve ter 11 caracteres")
    private String cpf;

    @Past(message = "Data de nascimento não pode ser data futura")
    private LocalDate dateBirth;

    @Email(message = "Email precisa ser válido")
    private String email;
    private Integer profile;
    private String password;

    public UserDTO(User user){
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.dateBirth = user.getDateBirth();
        this.email = user.getEmail();
        this.profile = user.getProfile().getCode();
        this.password = user.getPassword();
    }

    public String getProfile() {
        return ProfileEnum.toEnum(profile).getDescription();
    }

    public void setProfile(ProfileEnum profile) {
        this.profile = profile.getCode();
    }

    @JsonIgnore
    public Integer getProfileCode() {
        return profile;
    }


    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
