package zhanuzak.repository;

import zhanuzak.dto.StudentResponse;
import zhanuzak.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select new zhanuzak.dto.StudentResponse(s.id,s.name,s.age," +
            "s.email,s.password,s.role,s.phoneNumber)from Student s")
    List<StudentResponse>getAllStudents();

    @Query("select new zhanuzak.dto.StudentResponse(s.id,s.name,s.age," +
            "s.email,s.password,s.role,s.phoneNumber)from Student s")
    Page<StudentResponse> getAllStudents(Pageable pageable);


    @Query("select new zhanuzak.dto.StudentResponse(s.id,s.name,s.age," +
            "s.email,s.password,s.role,s.phoneNumber)from Student s where s.id=:id")
    Optional<StudentResponse>getStudentById(Long id);




}
