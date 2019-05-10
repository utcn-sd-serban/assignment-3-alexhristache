import { EventEmitter } from "events";

class UserModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            users: [{
                userId: 1,
                email: "alex@gmail.com",
                username: "alex",
                password: "123",
                type: "regular",
                score: 0,
                isBanned: false,
            }, {
                userId: 1,
                email: "andrei@gmail.com",
                username: "andrei",
                password: "123",
                type: "regular",
                score: 0,
                isBanned: false,
            }],
            newUser: {
                userId: 0,
                email: "",
                username: "",
                password: "",
                type: "",
                score: 0,
                isBanned: false,
            },
            inputUsername: "",
            inputPassword: "",
            loggedUser: "",
        };
    }

    changeStateProperty(property, value) {
        this.state = {
            ...this.state,
            [property]: value
        }
        this.emit("change", this.state);
    }

    login(username, password) {
        this.state.loggedUser = "";
        for (let user of this.state.users) {
            if (user.username === username && user.password === password) {
                this.state.loggedUser = username;
            }
        }
        this.emit("change", this.state);
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