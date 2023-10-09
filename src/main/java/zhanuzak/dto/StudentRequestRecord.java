package zhanuzak.dto;

import lombok.Builder;
import zhanuzak.enums.Role;
import zhanuzak.validation.EmailValidation;
import zhanuzak.validation.PhoneNumberValidation;

@Builder
public record StudentRequestRecord(String name,
                                   int age,
                                   @EmailValidation
                                   String email,
                                   String password,
                                   Role role,
                                   @PhoneNumberValidation
                                   String phoneNumber) {
    public StudentRequestRecord(String name, int age, String email, String password, Role role,String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber=phoneNumber;
    }
}
