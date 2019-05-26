import { EventEmitter } from "events";
import userModel, { client, listener } from "./UserModel";

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

    filterByTag() {
        client.filterQuestionsByTag(this.state.filter).then(questions => {
            for (let question of questions) {
                question.username = userModel.findUsernameById(question.user);
            }
            this.state = { ...this.state, filteredQuestions: questions };
            this.emit("change", this.state);
        });
    }

    filterByText() {
        client.filterQuestionsByTitle(this.state.filter).then(questions => {
            for (let question of questions) {
                question.username = userModel.findUsernameById(question.user);
            }
            this.state = { ...this.state, filteredQuestions: questions };
            this.emit("change", this.state);
        });
    }

    addQuestion(questionId, user, username, title, text, creationDateTime, tags, score) {
        return client.createQuestion(questionId, user, username, title, text, creationDateTime, score, tags);
        // .then(question => this.appendQuestion(question));
    }

    appendQuestion(question) {
        this.state = {
            ...this.state,
            questions: this.state.questions.concat([question])
        };
        this.emit("change", this.state);
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
        return client.voteQuestion(id, vote)
            .then(() => {
                this.loadQuestions();
            });
    }
}

listener.on("event", event => {
    if (event.type === "QUESTION_CREATED") {
        questionModel.appendQuestion(event.question);
    }
});

const questionModel = new QuestionModel();

export default questionModel;