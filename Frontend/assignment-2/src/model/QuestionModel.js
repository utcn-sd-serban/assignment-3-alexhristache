import { EventEmitter } from "events";
import userModel, { client } from "./UserModel";

class QuestionModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            questions: [],
            newQuestion: {
                questionId: -1,
                user: "",
                username: "",
                title: "",
                text: "",
                creationDateTime: "",
                tags: "",
                score: 0,
            },
            filter: "",
            filteredQuestions: []
        };
    }

    loadQuestions() {
        debugger;
        client.loadAllQuestions().then(questions => {
            for (let question of questions) {
                question.username = userModel.findUsernameById(question.user);
            }
            this.state = { ...this.state, questions: questions };
            this.emit("change", this.state);
        });
    }

    findById(id) {
        for (let question of this.state.questions) {
            if (question.questionId === id) {
                return question;
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

    filterByTag(filter) {
        let newQuestions = []
        for (let question of this.state.questions) {
            if (question.tags.includes(filter)) {
                newQuestions.push(question);
            }
        }

        this.state = {
            ...this.state,
            filteredQuestions: newQuestions
        }
        this.emit("change", this.state);
    }

    filterByText(filter) {
        let newQuestions = []
        for (let question of this.state.questions) {
            if (question.title.includes(filter)) {
                newQuestions.push(question);
            }
        }

        this.state = {
            ...this.state,
            filteredQuestions: newQuestions
        }
        this.emit("change", this.state);
    }

    addQuestion(questionId, user, username, title, text, creationDateTime, tags, score) {
        return client.createQuestion(questionId, user, username, title, text, creationDateTime, score, tags)
            .then(question => {
                this.state = {
                    ...this.state,
                    questions: this.state.questions.concat([question])
                };
                this.emit("change", this.state);
            });
    }

    changeNewQuestionProperty(property, value) {
        this.state = {
            ...this.state,
            newQuestion: {
                ...this.state.newQuestion,
                [property]: value
            }
        }
        this.emit("change", this.state);
    }

    vote(id, vote) {
        let newQuestions = [];
        for (let question of this.state.questions) {
            if (question.questionId === id) {
                question.score = question.score + vote;
            }
            newQuestions.push(question);
        }

        this.state = {
            ...this.state,
            questions: newQuestions
        }
        this.emit("change", this.state);
    }
}

const questionModel = new QuestionModel();

export default questionModel;