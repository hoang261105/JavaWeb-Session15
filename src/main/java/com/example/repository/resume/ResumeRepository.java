package com.example.repository.resume;

import com.example.dto.ResumeDTO;
import com.example.model.Resume;

import java.util.List;

public interface ResumeRepository {
    List<Resume> findAllResumes();

    boolean saveResume(ResumeDTO resume);

    boolean checkExistEmail(String email);

    boolean checkExistPhone(String phone);

    ResumeDTO findById(int id);

    boolean updateResume(ResumeDTO resume);

    boolean deleteResume(int id);
}
