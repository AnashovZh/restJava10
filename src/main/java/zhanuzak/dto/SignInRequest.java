package zhanuzak.dto;

import lombok.Builder;

@Builder
public record SignInRequest(
        String email,
        String password) {

}
