package zhanuzak.repository.dao;

import zhanuzak.dto.StudentResponse;

import java.util.List;

public interface StudentJdbcTemplate {
    List<StudentResponse> getAllStudents();
    StudentResponse getById(Long id);
}
