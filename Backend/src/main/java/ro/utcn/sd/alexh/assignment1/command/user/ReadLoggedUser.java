package ro.utcn.sd.alexh.assignment1.command.user;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.dto.UserDTO;
import ro.utcn.sd.alexh.assignment1.service.UserUserDetailsService;

@RequiredArgsConstructor
public class ReadLoggedUser implements Command {

    private final UserUserDetailsService userUserDetailsService;

    @Override
    public DTO execute() {
        return UserDTO.ofEntity(userUserDetailsService.loadCurrentUser());
    }
}
