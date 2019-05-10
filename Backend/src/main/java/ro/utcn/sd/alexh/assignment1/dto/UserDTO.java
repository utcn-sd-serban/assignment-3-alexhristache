package ro.utcn.sd.alexh.assignment1.dto;

import lombok.Data;
import ro.utcn.sd.alexh.assignment1.entity.User;

@Data
public class UserDTO {
    private Integer userId;
    private String email;
    private String username;
    private String password;
    private String type;
    private int score;
    private boolean isBanned;

    public static UserDTO ofEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setType(user.getType());
        userDTO.setScore(user.getScore());
        userDTO.setBanned(user.isBanned());

        return userDTO;
    }
}
