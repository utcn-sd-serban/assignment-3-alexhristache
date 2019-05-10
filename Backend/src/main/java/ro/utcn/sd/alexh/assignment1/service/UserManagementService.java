package ro.utcn.sd.alexh.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.alexh.assignment1.dto.UserDTO;
import ro.utcn.sd.alexh.assignment1.entity.User;
import ro.utcn.sd.alexh.assignment1.exception.LoginFailedException;
import ro.utcn.sd.alexh.assignment1.exception.UserAlreadyExistsException;
import ro.utcn.sd.alexh.assignment1.exception.UserNotFoundException;
import ro.utcn.sd.alexh.assignment1.exception.UserNotLoggedException;
import ro.utcn.sd.alexh.assignment1.persistence.api.RepositoryFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final RepositoryFactory repositoryFactory;
    private User loggedUser;

    @Transactional
    public void login(String username, String password) {
        Optional<User> maybeUser = repositoryFactory.createUserRepository().findByUsername(username);
        if (maybeUser.isPresent() && maybeUser.get().getPassword().equals(password)) {
            loggedUser = maybeUser.get();
        } else {
            throw new LoginFailedException();
        }
    }

    @Transactional
    public UserDTO getLoggedUser() {
        if (loggedUser != null) {
            return UserDTO.ofEntity(loggedUser);
        } else {
            throw new UserNotLoggedException();
        }
    }

    @Transactional
    public void logout() {
        loggedUser = null;
    }

    @Transactional
    public List<UserDTO> listUsers() {
        return repositoryFactory.createUserRepository().findAll().stream()
                .map(UserDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setType(userDTO.getType());
        user.setScore(userDTO.getScore());
        user.setBanned(userDTO.isBanned());

        Optional<User> maybeUser = repositoryFactory.createUserRepository().findByUsername(user.getUsername());
        if (maybeUser.isPresent()) {
            throw new UserAlreadyExistsException();
        } else {
            return UserDTO.ofEntity(repositoryFactory.createUserRepository().save(user));
        }
    }

    @Transactional
    public UserDTO findUserById(Integer userId) {
        Optional<User> maybeUser = repositoryFactory.createUserRepository().findById(userId);
        return UserDTO.ofEntity(maybeUser.orElseThrow(UserNotFoundException::new));
    }
}
