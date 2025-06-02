package com.example.controller;

import com.example.dto.ResumeDTO;
import com.example.model.Resume;
import com.example.service.CloudinaryService;
import com.example.service.resume.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("resumes")
    public String resumes(Model model) {
        List<Resume> resumes = resumeService.findAllResumes();
        model.addAttribute("resumes", resumes);
        return "resumes";
    }

    @GetMapping("add-resume")
    public String addResume(Model model) {
        ResumeDTO resumeDTO = new ResumeDTO();
        model.addAttribute("resumeDTO", resumeDTO);
        return "add_resume";
    }

    @PostMapping("create-resume")
    public String createResume(@Valid @ModelAttribute("resumeDTO") ResumeDTO resumeDTO, BindingResult result) throws IOException {
        if (resumeDTO.getFile() == null || resumeDTO.getFile().isEmpty()) {
            result.rejectValue("file", null, "Vui lòng chọn hình ảnh");
        }

        if (result.hasErrors()) {
            return "add_resume";
        }

        String URL = cloudinaryService.uploadFile(resumeDTO.getFile());
        resumeDTO.setImage(URL);
        resumeService.saveResume(resumeDTO);

        return "redirect:/resumes";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        ResumeDTO resumeDTO = resumeService.findById(id);
        model.addAttribute("resume", resumeDTO);
        return "edit_resume";
    }

    @PostMapping("update-resume")
    public String updateResume(@Valid @ModelAttribute("resume") ResumeDTO resumeDTO, BindingResult result) throws IOException{
        if (result.hasErrors()) {
            return "edit_resume";
        }

        if (resumeDTO.getFile() != null && !resumeDTO.getFile().isEmpty()) {
            String URL = cloudinaryService.uploadFile(resumeDTO.getFile());
            resumeDTO.setImage(URL);
        }else{
            ResumeDTO resume = resumeService.findById(resumeDTO.getId());
            resumeDTO.setImage(resume.getImage());
        }

        resumeService.updateResume(resumeDTO);
        return "redirect:/resumes";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        resumeService.deleteResume(id);
        return "redirect:/resumes";
    }
}
