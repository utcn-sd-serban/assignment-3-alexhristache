package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.command.Invoker;
import ro.utcn.sd.alexh.assignment1.command.user.ReadAllUsersCommand;
import ro.utcn.sd.alexh.assignment1.command.user.ReadLoggedUser;
import ro.utcn.sd.alexh.assignment1.dto.UserDTO;
import ro.utcn.sd.alexh.assignment1.dto.UserListDTO;
import ro.utcn.sd.alexh.assignment1.service.UserManagementService;
import ro.utcn.sd.alexh.assignment1.service.UserUserDetailsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserManagementService userManagementService;
    private final UserUserDetailsService userUserDetailsService;
    private final Invoker invoker;

    @GetMapping("/users")
    public List<UserDTO> readAll() {
        return ((UserListDTO) invoker.invoke(new ReadAllUsersCommand(userManagementService))).getList();
    }

    @GetMapping("/users/{id}")
    public UserDTO read(@PathVariable int id) {
        return userManagementService.findUserById(id);
    }

    @PostMapping("/users")
    public UserDTO create(@RequestBody UserDTO dto) {
        return userManagementService.addUser(dto);
    }

    @GetMapping("/current-user")
    public UserDTO getLoggedUser() {
        return (UserDTO) invoker.invoke(new ReadLoggedUser(userUserDetailsService));
    }
}
