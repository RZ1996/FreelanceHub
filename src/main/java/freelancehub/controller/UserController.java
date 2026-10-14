package freelancehub.controller;
import freelancehub.dto.LoginDTO;
import freelancehub.dto.RegisterRequestDTO;
import freelancehub.dto.UserDTO;
import freelancehub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        String token = userService.loginUser(loginDTO);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO){
        UserDTO user = userService.registerUser(registerRequestDTO);
        return ResponseEntity.ok(user);

    }
}
