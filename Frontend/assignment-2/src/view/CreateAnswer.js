import React from "react";

const CreateAnswer = ({ id, text, onSubmit, onChange }) => (
    <div>
        <h2>Add Answer</h2>
        <div>
            <label>Text: </label>
            <input value={text}
                onChange={e => onChange("text", e.target.value)} />
            <br />
            <button onClick={() => onSubmit(id)}>Submit</button>
        </div>
    </div>
);

export default CreateAnswer;