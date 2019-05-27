package ro.utcn.sd.alexh.assignment1.command.user;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.dto.UserListDTO;
import ro.utcn.sd.alexh.assignment1.service.UserManagementService;

@RequiredArgsConstructor
public class ReadAllUsersCommand implements Command {

    private final UserManagementService userManagementService;

    @Override
    public DTO execute() {
        return new UserListDTO(userManagementService.listUsers());
    }
}
