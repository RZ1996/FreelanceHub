package freelancehub.controller;
import freelancehub.dto.LoginDTO;
import freelancehub.dto.RegisterDTO;
import freelancehub.dto.UserDTO;
import freelancehub.repository.UserRepository;
import freelancehub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO){
        userService.loginUser(loginDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");

    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO){
        userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");

    }
}
