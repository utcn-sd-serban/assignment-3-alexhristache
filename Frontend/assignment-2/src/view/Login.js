import React from "react";

const Login = ({inputUsername, inputPassword, onChange, onLogin}) => (
    <div>
        <h2>Login</h2>
        <br />
        <label>Username: </label>
        <input value={inputUsername}
                onChange={e => onChange("inputUsername", e.target.value)} />
        <br />
        <label>Password: </label>
        <input value={inputPassword}
                onChange={e => onChange("inputPassword", e.target.value)} />
        <br />
        <button onClick={onLogin}>Login</button>
        <button>Register</button>
        <br />
    </div>
);

export default Login;