import userModel from "../model/UserModel"

class LoginPresenter {

    onCreate() {
        window.location.assign("#/login");
    }

    onChange(property, value) {
        userModel.changeStateProperty(property, value);
    }

    onLogin() {
        userModel.login(userModel.state.inputUsername, userModel.state.inputPassword);
        if (userModel.state.loggedUser !== "" && userModel.state.loggedUser !== undefined) {
            window.location.assign("#/");
        }
    }

    onInit() {
        userModel.loadUsers();
    }

}

const loginPresenter = new LoginPresenter();

export default loginPresenter;