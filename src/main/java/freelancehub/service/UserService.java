package freelancehub.service;
import freelancehub.dto.RegisterRequestDTO;
import freelancehub.exceptions.EmailAlreadyExistsException;
import freelancehub.exceptions.InvalidCredentialsException;
import freelancehub.dto.LoginDTO;
import freelancehub.dto.UserDTO;
import freelancehub.entity.User;
import freelancehub.mapper.UserMapper;
import freelancehub.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserDTO registerUser(RegisterRequestDTO registerRequestDTO){
        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        User user = new User();
        user.setName(registerRequestDTO.getName());
        user.setSurName(registerRequestDTO.getSurName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
       return userMapper.userToUserDTO(user);

    }

    public String loginUser(LoginDTO login) {
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return jwtService.generateToken(user.getId());
    }




}
