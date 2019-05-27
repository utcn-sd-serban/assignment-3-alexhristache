package ro.utcn.sd.alexh.assignment1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserListDTO implements DTO {
    private List<UserDTO> list;
}
