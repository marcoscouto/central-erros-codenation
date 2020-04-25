package com.github.marcoscouto.dto;

import com.github.marcoscouto.domain.User;
import com.github.marcoscouto.domain.enums.ProfileEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private String name;
    private String cpf;
    private LocalDate dateBirth;
    private String email;
    private Integer profile;

    public UserDTO(User user){
        this.name = user.getName();
        this.cpf = user.getCpf();
        this.dateBirth = user.getDateBirth();
        this.email = user.getEmail();
        this.profile = user.getProfile().getCode();
    }

    public String getProfile() {
        return ProfileEnum.toEnum(profile).getDescription();
    }

    public void setProfile(ProfileEnum profile) {
        this.profile = profile.getCode();
    }
}
