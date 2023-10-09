package zhanuzak.service;

import zhanuzak.dto.PaginationResponse;
import zhanuzak.dto.SimpleResponse;
import zhanuzak.dto.StudentRequestRecord;
import zhanuzak.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    SimpleResponse saveStudent(StudentRequestRecord studentRequest);

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);
     SimpleResponse updateStudent(Long id, StudentRequestRecord studentRequest);



    SimpleResponse deleteStudent(Long id);


    PaginationResponse getAllByPagination(int currentPage, int pageSize);
}
