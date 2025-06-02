package com.example.repository.resume;

import com.example.config.ConnectionDB;
import com.example.dto.ResumeDTO;
import com.example.model.Resume;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumeRepositoryImp implements ResumeRepository {
    @Override
    public List<Resume> findAllResumes() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Resume> resumes = new ArrayList<>();

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_resumes()}");

            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Resume resume = new Resume();
                resume.setId(rs.getInt("id"));
                resume.setFullName(rs.getString("full_name"));
                resume.setPhone(rs.getString("phone_number"));
                resume.setEmail(rs.getString("email"));
                resume.setEducation(rs.getString("education"));
                resume.setExperience(rs.getString("experience"));
                resume.setSkills(rs.getString("skills"));
                resume.setImage(rs.getString("image"));
                resumes.add(resume);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resumes;
    }

    @Override
    public boolean saveResume(ResumeDTO resume) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call add_resume(?,?,?,?,?,?,?)");

            callSt.setString(1, resume.getFullName());
            callSt.setString(2, resume.getEmail());
            callSt.setString(3, resume.getPhone());
            callSt.setString(4, resume.getEducation());
            callSt.setString(5, resume.getExperience());
            callSt.setString(6, resume.getSkills());
            callSt.setString(7, resume.getImage());

            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_exist_email(?)}");
            
            callSt.setString(1, email);
            
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_exist_email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_exist_phone(?)}");

            callSt.setString(1, phone);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_exist_phone");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResumeDTO findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResumeDTO resume = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call find_resume_by_id(?)");

            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                resume = new ResumeDTO();
                resume.setId(rs.getInt("id"));
                resume.setFullName(rs.getString("full_name"));
                resume.setPhone(rs.getString("phone_number"));
                resume.setEmail(rs.getString("email"));
                resume.setEducation(rs.getString("education"));
                resume.setExperience(rs.getString("experience"));
                resume.setSkills(rs.getString("skills"));
                resume.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resume;
    }

    @Override
    public boolean updateResume(ResumeDTO resume) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call update_resume(?,?,?,?,?,?,?,?)");

            callSt.setInt(1, resume.getId());
            callSt.setString(2, resume.getFullName());
            callSt.setString(3, resume.getEmail());
            callSt.setString(4, resume.getPhone());
            callSt.setString(5, resume.getEducation());
            callSt.setString(6, resume.getExperience());
            callSt.setString(7, resume.getSkills());
            callSt.setString(8, resume.getImage());

            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteResume(int id) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call delete_resume(?)");

            callSt.setInt(1, id);

            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
