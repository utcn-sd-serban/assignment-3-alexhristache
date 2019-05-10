import { EventEmitter } from "events";

class AnswerModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            answers: [{
                answerId: 1,
                questionId: 1,
                user: "Andrei",
                text: "My first answer to Q1",
                creationDateTime: "2018-09-18T16:39:22",
                score: 0,
            }, {
                answerId: 2,
                questionId: 1,
                user: "Andrei",
                text: "My second answer to  Q1",
                creationDateTime: "2018-09-18T16:39:22",
                score: 0,
            }, {
                answerId: 3,
                questionId: 2,
                user: "Andrei",
                text: "Answer blabla",
                creationDateTime: "2018-09-18T16:39:22",
                score: 50,
            }],
            newAnswer: {
                answerId: -1,
                questionId: -1,
                user: "",
                text: "",
                creationDateTime: "",
                score: 0,
            },
            answersForQuestion: [],
        };
    }

    findAnswersForQuestion(id) {
        let answersToFind = [];
        for (let answer of this.state.answers) {
            if (answer.questionId === id) {
                answersToFind.push(answer);
            }
        }
        this.state.answersForQuestion = answersToFind;
        this.emit("change", this.state);
    }

    findById(id) {
        for (let answer of this.state.answers) {
            if (answer.answerId === id) {
                return answer;
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

    removeAnswer(id) {
        let newAnswers = []
        for (let answer of this.state.answers) {
            if (answer.answerId !== id) {
                newAnswers.push(answer);
            }
        }
        this.state = {
            ...this.state,
            answers: newAnswers
        };
        this.emit("change", this.state);
        // return newAnswers;
    }

    addAnswer(answerId, questionId, user, text, creationDateTime, score) {
        this.state = {
            ...this.state,
            answers: this.state.answers.concat([{
                answerId,
                questionId,
                user,
                text,
                creationDateTime,
                score
            }])
        };
        this.emit("change", this.state);
    }

    changeNewAnswerProperty(property, value) {
        this.state = {
            ...this.state,
            newAnswer: {
                ...this.state.newAnswer,
                [property]: value
            }
        }
        this.emit("change", this.state);
    }

    vote(id, vote) {
        let newAnswers = [];
        for (let answer of this.state.answers) {
            if (answer.answerId === id) {
                answer.score = answer.score + vote;
            }
            newAnswers.push(answer);
        }

        this.state = {
            ...this.state,
            answers: newAnswers
        }
        this.emit("change", this.state);
    }
}

const answerModel = new AnswerModel();

export default answerModel;