package ro.utcn.sd.alexh.assignment1.service;

import org.junit.Assert;
import org.junit.Test;
import ro.utcn.sd.alexh.assignment1.entity.User;
import ro.utcn.sd.alexh.assignment1.exception.LoginFailedException;
import ro.utcn.sd.alexh.assignment1.persistence.api.RepositoryFactory;
import ro.utcn.sd.alexh.assignment1.persistence.api.UserRepository;
import ro.utcn.sd.alexh.assignment1.persistence.memory.InMemoryRepositoryFactory;

public class UserManagementServiceTests {

    private static final RepositoryFactory repositoryFactory = new InMemoryRepositoryFactory();
    private static final UserRepository userRepository = repositoryFactory.createUserRepository();
    private static final UserManagementService userManagementService = new UserManagementService(repositoryFactory);

    static {
        userRepository.save(new User(1, "email1@email.com", "username1", "password1", "regular", 0, false));
        userRepository.save(new User(2, "email2@email.com", "username2", "password2", "regular", 0, false));
        userRepository.save(new User(3, "email3@email.com", "username3", "password3", "regular", 0, false));
    }

//    @Test
//    public void findUserTest() {
//        User userFromRepo = userManagementService.findUserById(1);
//        User user = new User(1, "email1@email.com", "username1", "password1", "regular", 0, false);
//        Assert.assertEquals(user, userFromRepo);
//    }
//
//    @Test
//    public void loginTest() {
//        userManagementService.login("username1", "password1");
//        Assert.assertEquals(userManagementService.getLoggedUser(), userManagementService.findUserById(1));
//    }
//
//    @Test(expected = LoginFailedException.class)
//    public void loginFailedTest() {
//        userManagementService.login("username2", "SubscribeToPewdiepie");
//    }
//
//    @Test
//    public void getUsersTest() {
//        Assert.assertEquals(userManagementService.listUsers().size(), 3);
//    }
}
