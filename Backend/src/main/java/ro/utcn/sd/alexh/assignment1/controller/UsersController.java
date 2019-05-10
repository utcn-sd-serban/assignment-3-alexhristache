package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.dto.UserDTO;
import ro.utcn.sd.alexh.assignment1.service.UserManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserManagementService userManagementService;

    @GetMapping("/users")
    public List<UserDTO> readAll() {
        return userManagementService.listUsers();
    }

    @GetMapping("/users/{id}")
    public UserDTO read(@PathVariable int id) {
        return userManagementService.findUserById(id);
    }

    @PostMapping("/users")
    public UserDTO create(@RequestBody UserDTO dto) {
        return userManagementService.addUser(dto);
    }
}
