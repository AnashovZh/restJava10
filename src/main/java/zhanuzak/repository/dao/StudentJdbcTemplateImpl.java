package zhanuzak.repository.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;
import zhanuzak.dto.StudentResponse;
import zhanuzak.enums.Role;

import java.sql.ResultSet;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentJdbcTemplateImpl implements StudentJdbcTemplate {
    private final JdbcTemplate jdbcTemplate;

    private StudentResponse rowMapper(ResultSet rs) {
        return null;
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        String sql = """
                select id as id, name as name,age as age ,email as email ,
                password as password,role as role ,phone_number as phoneNumber from students
                """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String roleName = rs.getString("role");
            Role role = Role.valueOf(roleName);
            return new StudentResponse(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("password"),
                    role,
                    rs.getString("phoneNumber"));
        });
    }

    @Override
    public StudentResponse getById(Long id) {
        return null;
    }
}
