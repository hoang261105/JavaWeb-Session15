package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResumeDTO {
    private int id;
    
    @NotBlank(message = "Tên không được để trống!")
    private String fullName;

    @NotBlank(message = "Email không được để trống!")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống!")
    private String phone;
    
    @NotBlank(message = "Học vấn không được để trống!")
    private String education;
    
    @NotBlank(message = "Kinh nghiệm không được để trống!")
    private String experience;

    private transient MultipartFile file;

    private String image;

    @NotBlank(message = "Kỹ năng không được để trống!")
    private String skills;
}