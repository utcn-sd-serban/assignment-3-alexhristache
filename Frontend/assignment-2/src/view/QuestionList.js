import React from "react";

const QuestionList = ({ questions, onCreateQuestion, onViewDetails, onFilterByTag, onChange, onFilterByText, filter, onUpvote, onDownvote }) => (
    <div>
        <h2>Questions</h2>
        <br />
        <button onClick={onCreateQuestion}>Add Question</button>
        <br />
        <br />
        <input value={filter}
            onChange={e => onChange("filter", e.target.value)} />
        <br />
        <button onClick={() => onFilterByTag(filter)}>Filter by tag</button>
        <button onClick={() => onFilterByText(filter)}>Filter by title</button>
        <br />
        <br /><br />
        <div>
            <table border='1'>
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Title</th>
                        <th>Text</th>
                        <th>Creation Date/Time</th>
                        <th>Tags</th>
                        <th>Score</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {
                        questions.map((question) => (
                            <tr key={question.questionId}>
                                <td>{question.user}</td>
                                <td>{question.title}</td>
                                <td>{question.text}</td>
                                <td>{question.creationDateTime}</td>
                                <td>{question.tags}</td>
                                <td>{question.score}</td>
                                <td><button onClick={() => onViewDetails(question.questionId)}>View Details</button></td>
                                <td><button onClick={() => onUpvote(question.questionId)}>Upvote</button></td>
                                <td><button onClick={() => onDownvote(question.questionId)}>Downvote</button></td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    </div>
);

export default QuestionList;