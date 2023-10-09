package zhanuzak.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhanuzak.dto.AuthenticationResponse;
import zhanuzak.dto.SignInRequest;
import zhanuzak.dto.SignUpRequest;
import zhanuzak.entity.User;
import zhanuzak.exceptions.AlreadyExistException;
import zhanuzak.exceptions.BadCreadentialException;
import zhanuzak.exceptions.NotFoundException;
import zhanuzak.repository.UserRepository;
import zhanuzak.security.jwt.JwtService;
import zhanuzak.service.AuthenticationService;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new AlreadyExistException(
                    "User with email:" + signUpRequest.email() + " already exists!"
            );
        }
        User user = User.builder()
                .name(signUpRequest.name())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(signUpRequest.role())
                .build();
        userRepository.save(user);
        //toDo:generateToken
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUsersByEmail(signInRequest.email()).orElseThrow(
                () -> new NotFoundException("User with email:" + signInRequest.email() + " doesn't exist!"));

        if (signInRequest.email().isBlank()){
            throw new BadCreadentialException("Email is blank");
        }if (!passwordEncoder.matches(signInRequest.password(), user.getPassword())){
            throw  new BadCreadentialException("Wrong password !!!");
        }
        String s = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(s)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
