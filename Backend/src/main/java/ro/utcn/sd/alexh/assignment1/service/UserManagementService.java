package ro.utcn.sd.alexh.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.alexh.assignment1.dto.UserDTO;
import ro.utcn.sd.alexh.assignment1.entity.User;
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

    @Transactional
    public List<UserDTO> listUsers() {
        return repositoryFactory.createUserRepository().findAll().stream()
                .map(UserDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO addUser(UserDTO userDTO) {
        User user = UserDTO.ofDTO(userDTO);

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


    @Transactional
    public void deleteUser(Integer userId) {
        User user = UserDTO.ofDTO(findUserById(userId));
        repositoryFactory.createUserRepository().remove(user);
    }

    @Transactional
    public void deleteAll() {
        listUsers().forEach(userDTO -> deleteUser(userDTO.getUserId()));
    }

    @Transactional
    public UserDTO findUserByUsername(String username) {
        return UserDTO.ofEntity(repositoryFactory.createUserRepository().findByUsername(username).orElseThrow(UserNotFoundException::new));
    }
}
