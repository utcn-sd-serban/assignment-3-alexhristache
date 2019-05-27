import { EventEmitter } from "events";
import userModel, {client} from "./UserModel";

class AnswerModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
             answers: [],
            newAnswer: {
                answerId: -1,
                questionId: -1,
                user: "",
                username: "",
                text: "",
                creationDateTime: "",
                score: 0,
            },
            answersForQuestion: [],
        };
    }

    loadAnswers() {
        client.loadAllAnswers().then(answers => {
            for (let answer of answers) {
                answer.username = userModel.findUsernameById(answer.userId);
            }
            this.state = { ...this.state, answers: answers };
            this.emit("change", this.state);
        });
    }

    loadAnswersForQuestion(id) {
        client.loadAnswersForQuestion(id).then(answers => {
            for (let answer of answers) {
                answer.username = userModel.findUsernameById(answer.user);
            }
            this.state = {...this.state, answersForQuestion: answers};
            this.emit("change", this.state);
        });
    }

    deleteAnswer(id) {
        client.deleteAnswer(id).then(answer => {
            this.loadAnswersForQuestion(answer.questionId);
        });
    }

    findById(id) {
        for (let answer of this.state.answers) {
            if (answer.answerId === id) {
                return answer;
            }
        }
    }

    findByUsername(username) {
        for (let answer of this.state.answers) {
            if (answer.user === username) {
                return answer;
            }
        }
    }

    editAnswer(answerId, questionId, userId, text, creationDateTime, score) {
        return client.editAnswer(answerId, questionId, userId, text, creationDateTime, score)
            .then(answer => {
                // this.state = {
                //     ...this.state,
                //     answers: this.state.answers.concat([answer])
                // };
                this.loadAnswersForQuestion(questionId); // idk
                //this.emit("change", this.state);
            });
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
    }

    addAnswer(answerId, questionId, user, text, creationDateTime, score) {
        return client.createAnswer(answerId, questionId, user, text, creationDateTime, score)
            .then(answer => {
                this.state = {
                    ...this.state,
                    answers: this.state.answers.concat([answer])
                };
                this.emit("change", this.state);
            });
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
        return client.voteAnswer(id, vote)
            .then(() => {
                this.loadAnswersForQuestion(id);
            });
    }
}

const answerModel = new AnswerModel();

export default answerModel;