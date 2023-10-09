package zhanuzak.dto;

import lombok.Getter;
import lombok.Setter;
import zhanuzak.enums.Role;
@Setter
@Getter
public class StudentResponse{
    private Long id;
    private String name;
    private int age;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;

    public StudentResponse(Long id, String name, int age,
                           String email, String password, Role role,String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber=phoneNumber;
    }

    public StudentResponse(long id, String name, int age, String email, String roleName, String phoneNumber) {
    }
}
