package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resume {
    private int id;

    private String fullName;
    private String email;

    private String phone;
    private String education;
    private String experience;
    private transient MultipartFile file;
    private String image;
    private String skills;
}
