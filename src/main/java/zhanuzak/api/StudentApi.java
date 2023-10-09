package zhanuzak.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zhanuzak.dto.PaginationResponse;
import zhanuzak.dto.SimpleResponse;
import zhanuzak.dto.StudentRequestRecord;
import zhanuzak.dto.StudentResponse;
import zhanuzak.repository.dao.StudentJdbcTemplate;
import zhanuzak.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Tag(name = "StudentApi")
public class StudentApi {

    private final StudentService studentService;
    private final StudentJdbcTemplate studentJdbcTemplate;


    @PermitAll
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }
    @PermitAll
    @GetMapping("/template")
    public List<StudentResponse>getAll(){
        return studentJdbcTemplate.getAllStudents();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public SimpleResponse saveStudent(@RequestBody @Valid StudentRequestRecord studentRequest) {
        return studentService.saveStudent(studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping("/{studentId}")
    public StudentResponse getById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{studentId}")
    public SimpleResponse updateStudent(@PathVariable Long studentId,
                                        @RequestBody StudentRequestRecord studentRequest) {
        return studentService.updateStudent(studentId, studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @DeleteMapping("/{studentId}")
    public SimpleResponse deleteStudent(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/pagination")
    public PaginationResponse pagination(@RequestParam int currentPage,
                                         @RequestParam int pageSize) {
        return studentService.getAllByPagination(currentPage, pageSize);
    }
}
