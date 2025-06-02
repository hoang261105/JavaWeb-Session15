package com.example.service.resume;

import com.example.dto.ResumeDTO;
import com.example.model.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> findAllResumes();

    boolean saveResume(ResumeDTO resume);

    boolean checkExistEmail(String email);

    boolean checkExistPhone(String phone);

    ResumeDTO findById(int id);

    boolean updateResume(ResumeDTO resume);

    boolean deleteResume(int id);
}
