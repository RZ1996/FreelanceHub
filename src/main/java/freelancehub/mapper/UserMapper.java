package freelancehub.mapper;

import freelancehub.dto.UserDTO;
import freelancehub.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
