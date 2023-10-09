package zhanuzak.service;

import zhanuzak.dto.AuthenticationResponse;
import zhanuzak.dto.SignInRequest;
import zhanuzak.dto.SignUpRequest;

public interface AuthenticationService {
    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
}
