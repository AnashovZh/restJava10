package zhanuzak.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zhanuzak.dto.PaginationResponse;
import zhanuzak.dto.SimpleResponse;
import zhanuzak.dto.StudentRequestRecord;
import zhanuzak.dto.StudentResponse;
import zhanuzak.entity.Student;
import zhanuzak.exceptions.NotFoundException;
import zhanuzak.repository.StudentRepository;
import zhanuzak.repository.dao.StudentJdbcTemplate;
import zhanuzak.service.StudentService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j//simple logging for java
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentJdbcTemplate studentJdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SimpleResponse saveStudent(StudentRequestRecord studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.name());
        student.setAge(studentRequest.age());
        student.setEmail(studentRequest.email());
        student.setPassword(passwordEncoder.encode(studentRequest.password()));
        student.setRole(studentRequest.role());
        student.setPhoneNumber(studentRequest.phoneNumber());
        studentRepository.save(student);
        log.info("Student with id: %s successfully saved" + student.getId());
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved", student.getId()))
                .build();
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return studentRepository.getStudentById(id).orElseThrow(
                () -> {
                    String message = "Student with id: " + id + " is not found!";
                    log.error(message);
                    return new NotFoundException(
                            message);
                });
    }

    @Override
    public SimpleResponse updateStudent(Long id, StudentRequestRecord studentRequest) {
        Student student1 = studentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Student with id: " + id + " is not found!"));
        student1.setName(studentRequest.name());
        student1.setAge(studentRequest.age());
        student1.setEmail(studentRequest.email());
        student1.setPassword(studentRequest.password());
        student1.setRole(studentRequest.role());
        student1.setPhoneNumber(studentRequest.phoneNumber());
        studentRepository.save(student1);
        log.info("Student successfully updated ");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Student with id: %s successfully saved", student1.getId()))
                .build();

    }


    @Override
    public SimpleResponse deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            log.error("Student with id: \" + id + \" is not found!");
            throw new NotFoundException(
                    "Student with id: " + id + " is not found!");
        }
        studentRepository.deleteById(id);
        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Student with id: " + id + " is deleted!")
                .build();

    }

    @Override
    public PaginationResponse getAllByPagination(int currentPage, int pageSize) {
        Pageable pageable= PageRequest.of(currentPage,pageSize);
        Page<StudentResponse> allStudents = studentRepository.getAllStudents(pageable);
        return PaginationResponse.builder()
                .students(allStudents.getContent())
                .currentPage(allStudents.getNumber()+1)
                .pageSize(allStudents.getTotalPages())
                .build();
    }
}
