import { EventEmitter } from "events";
import RestClient from "../rest/RestClient";

const client = new RestClient("user4", "123");

class QuestionModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            questions: [
            //     {
            //     questionId: 1,
            //     user: "Alex",
            //     title: "What does Linus Torvalds mean when he says that Git never ever tracks a file?",
            //     text: "Yet, when you dive into the Git book, the first thing you are told is that a file in Git can be either tracked or untracked. Furthermore, it seems to me like the whole Git experience is geared towards file versioning. When using git diff or git status output is presented on a per file basis. When using git add you also get to choose on a per file basis. You can even review history on a file basis and is lightning fast. How should this statement be interpreted?",
            //     creationDateTime: "2018-09-18 16:39:22",
            //     score: 0,
            //     tags: "java, programming, templates",
            // }, {
            //     questionId: 2,
            //     user: "Andrei",
            //     title: "What does Lambda Calculus mean?",
            //     text: "Furthermore, it seems to me like the whole Git experience is geared towards file versioning. When using git diff or git status output is presented on a per file basis. When using git add you also get to choose on a per file basis. You can even review history on a file basis and is lightning fast. How should this statement be interpreted?",
            //     creationDateTime: "2018-02-18 16:39:22",
            //     score: 0,
            //     tags: "c++, programming, design",
            //     answers: [],
            // }
            ],
            newQuestion: {
                questionId: -1,
                user: "",
                title: "",
                text: "",
                creationDateTime: "",
                tags: "",
                score: -1,
            },
            filter: "",
            filteredQuestions: []
        };
    }

    loadQuestions() {
        client.loadAllQuestions().then(questions => {
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

    addQuestion(questionId, user, title, text, creationDateTime, tags, score) {
        debugger;

        return client.createQuestion(questionId, user, title, text, creationDateTime, score, tags)
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