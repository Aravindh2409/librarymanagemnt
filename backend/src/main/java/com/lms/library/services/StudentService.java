package com.lms.library.services;

import com.lms.library.models.Student;
import com.lms.library.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
    
    public Student updateStudent(Long studentId, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setSname(studentDetails.getSname());
            student.setSbranch(studentDetails.getSbranch());
            student.setEmail(studentDetails.getEmail());
            student.setPhone(studentDetails.getPhone());
            return studentRepository.save(student);
        }
        return null;
    }
    
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
    
    @Transactional(readOnly = true)
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
    
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Student> getStudentsByBranch(String branch) {
        return studentRepository.findBySbranch(branch);
    }
    
    @Transactional(readOnly = true)
    public List<Student> searchStudents(String keyword) {
        return studentRepository.searchStudents(keyword);
    }
    
    @Transactional(readOnly = true)
    public Long getTotalStudents() {
        return studentRepository.getTotalStudentsCount();
    }
}
