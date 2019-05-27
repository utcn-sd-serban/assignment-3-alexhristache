import React, { Component } from "react";
import userModel from "../model/UserModel";
import Login from "./Login"
import loginPresenter from "../presenter/LoginPresenter";

const mapModelStateToComponentState = (modelState) => ({
    inputUsername: modelState.users.inputUsername,
    inputPassword: modelState.users.inputPassword
})

export default class SmartLogin extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(userModel.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        userModel.addListener("change", this.listener);
    }

    componentWillUnmount() {
        userModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <Login
                inputUsername={this.state.inputUsername}
                inputPassword={this.state.inputPassword}
                onCreate={loginPresenter.onCreate}
                onChange={loginPresenter.onChange}
                onLogin={loginPresenter.onLogin}
            />
        );
    }
}