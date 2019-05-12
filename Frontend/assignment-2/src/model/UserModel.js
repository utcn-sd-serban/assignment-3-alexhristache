import { EventEmitter } from "events";
import RestClient from "../rest/RestClient";

export var client = new RestClient("-", "-");

class UserModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            users: [],
            inputUsername: "",
            inputPassword: "",
            loggedUser: "",
        };
    }

    loadUsers() {
        client.loadAllUsers().then(users => {
            this.state = { ...this.state, users: users };
            this.emit("change", this.state);
        });
    }
    
    findByUsername(username) {
        debugger;
        for (let user of this.state.users) {
            if (user.username === username) {
                return user.userId;
            }
        }
    }

    findUsernameById(id) {
        debugger;
        for (let user of this.state.users) {
            if (user.userId == id) {
                return user.username;
            }
        }
    }

    changeStateProperty(property, value) {
        this.state = {
            ...this.state,
            [property]: value
        }
        this.emit("change", this.state);
    }

    login() {
        debugger;
        client = new RestClient(this.state.inputUsername, this.state.inputPassword);
        this.state.loggedUser = this.state.inputUsername;
        this.emit("change", this.state);
    }

    loadLoggedUser() {
        client.loadLoggedUser().then(user => {
            this.state = { ...this.state, loggedUser: user };
            this.emit("change", this.state);
        });
    }

    addUser(userId, email, username, password, type, score, isBanned) {
        this.state = {
            ...this.state,
            users: this.state.users.concat([{
                userId, 
                email, 
                username, 
                password, 
                type, 
                score, 
                isBanned
            }])
        };
        this.emit("change", this.state);
    }

    changeNewUserProperty(property, value) {
        this.state = {
            ...this.state,
            newUser: {
                ...this.state.newUser,
                [property]: value
            }
        }
        this.emit("change", this.state);
    }
}

const userModel = new UserModel();

export default userModel;