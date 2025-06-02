package com.example.service.resume;

import com.example.dto.ResumeDTO;
import com.example.model.Resume;
import com.example.repository.resume.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImp implements ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public List<Resume> findAllResumes() {
        return resumeRepository.findAllResumes();
    }

    @Override
    public boolean saveResume(ResumeDTO resume) {
        return resumeRepository.saveResume(resume);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return resumeRepository.checkExistEmail(email);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return resumeRepository.checkExistPhone(phone);
    }

    @Override
    public ResumeDTO findById(int id) {
        return resumeRepository.findById(id);
    }

    @Override
    public boolean updateResume(ResumeDTO resume) {
        return resumeRepository.updateResume(resume);
    }

    @Override
    public boolean deleteResume(int id) {
        return resumeRepository.deleteResume(id);
    }
}
