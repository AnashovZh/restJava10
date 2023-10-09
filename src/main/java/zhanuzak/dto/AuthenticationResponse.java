package zhanuzak.dto;

import lombok.Builder;
import zhanuzak.enums.Role;

@Builder
public record AuthenticationResponse(String token, String email, Role role) {


}
