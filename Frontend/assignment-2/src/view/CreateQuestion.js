import React from "react";

const CreateQuestion = ({ title, text, tags, onCreate, onChange }) => (
    <div>
        <h2>Add Question</h2>
        <div>
            <label>Title: </label>
            <input value={title} data-cy="questionTitle"
                onChange={e => onChange("title", e.target.value)} />
            <br />
            <label>Text: </label>
            <input value={text} data-cy="questionText"
                onChange={e => onChange("text", e.target.value)} />
            <br />
            <label>Tags: </label>
            <input value={tags}  data-cy="questionTags"
                onChange={e => onChange("tags", e.target.value)} />
            <br />
            <button onClick={onCreate} data-cy="createQuestion">Create</button>
        </div>
    </div>
);

export default CreateQuestion;