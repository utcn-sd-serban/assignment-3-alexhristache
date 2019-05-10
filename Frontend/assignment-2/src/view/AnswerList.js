import React from "react";

const AnswerList = ({ answers, onRemove, onEdit, onUpvote, onDownvote }) => (
    <div>
        <h3>Answers</h3>
        <div>
            <ul>
                {
                    answers.map((answer) => (
                        <li>
                            <ul key={answer.answerId}>
                                <li>{answer.text} </li>
                                <li>Posted by: {answer.user}</li>
                                <li>{answer.creationDateTime}</li>
                                <li>Score: {answer.score}</li>
                                <button onClick={() => onEdit(answer.answerId)}>Edit</button>
                                <button onClick={() => onRemove(answer.answerId)}>Delete</button>
                                <button onClick={() => onUpvote(answer.answerId)}>Upvote</button>
                                <button onClick={() => onDownvote(answer.answerId)}>Downvote</button>
                                <br /><br />
                            </ul>
                        </li>
                    ))
                }
            </ul>
        </div>
    </div>
);

export default AnswerList;