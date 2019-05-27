import React from "react";

const QuestionDetails = ({ questionId, user, title, text, creationDateTime, score, onCreateAnswer }) => (
    <div>
        <h2>Question</h2>
        <label>Title: </label>
        <span>{title}</span>
        <br />
        <label>User: </label>
        <span>{user}</span>
        <br />
        <label>Score: </label>
        <span>{score}</span>
        <br />
        <label>Posted on: </label>
        <span>{creationDateTime}</span>
        <br />
        <label>Question: </label>
        <span>{text}</span>
        <br />
        <button onClick={() => onCreateAnswer(questionId)}>Add Answer</button>
        <br /><br />
    </div>
);

export default QuestionDetails;