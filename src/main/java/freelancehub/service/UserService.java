package freelancehub.service;
import freelancehub.dto.RegisterRequestDTO;
import freelancehub.exceptions.EmailAlreadyExistsException;
import freelancehub.exceptions.InvalidCredentialsException;
import freelancehub.dto.LoginDTO;
import freelancehub.dto.UserDTO;
import freelancehub.entity.User;
import freelancehub.mapper.UserMapper;
import freelancehub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterRequestDTO registerRequestDTO){
        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        User user = userMapper.userDTOToUser(registerRequestDTO);
        userRepository.save(user);
    }

    public UserDTO loginUser(LoginDTO login){
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        return userMapper.userToUserDTO(user);
    }





}
