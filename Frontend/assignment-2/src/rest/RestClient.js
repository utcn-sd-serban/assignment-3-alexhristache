import answerModel from "../model/AnswerModel";

const BASE_URL = "http://localhost:8080";

export default class RestClient {
    constructor(username, password) {
        debugger;
        this.authorization = "Basic " + btoa(username + ":" + password); 
    }

    loadAllQuestions() {
        debugger;
        return fetch(BASE_URL + "/questions", {
            method: "GET", 
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    createQuestion(questionId, user, username, title, text, creationDateTime, score, tags) {
        return fetch(BASE_URL + "/questions", {
            method: "POST",
            body: JSON.stringify({
                questionId: questionId,
                user: user,
                username: username,
                title: title,
                text: text,
                creationDateTime: creationDateTime,
                score: score,
                tags: tags
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }

    loadAllAnswers() {
        return fetch(BASE_URL + "/answers", {
            method: "GET", 
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    createAnswer(answerId, questionId, userId, text, creationDateTime, score) {
        return fetch(BASE_URL + "/answers/", {
            method: "POST",
            body: JSON.stringify({
                answerId: answerId,
                questionId: questionId,
                userId: userId,
                text: text,
                creationDateTime: creationDateTime,
                score: score
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json",
                //"Accept" : "application/json"
            }
        }).then(response => response.json());
    }

    loadAnswersForQuestion(questionId) {
        return fetch(BASE_URL + "/questions/" + questionId + "/answers", {
            method: "GET", 
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadAllUsers() {
        debugger;
        return fetch(BASE_URL + "/users", {
            method: "GET", 
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    loadLoggedUser() {
        return fetch(BASE_URL + "/current-user", {
            method: "GET", 
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    deleteAnswer(answerId) {
        return fetch(BASE_URL + "/answers/" + answerId, {
            method: "DELETE", 
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => response.json());
    }

    editAnswer(answerId, questionId, userId, text, creationDateTime, score) {
        return fetch(BASE_URL + "/answers/" + answerId, {
            method: "PUT",
            body: JSON.stringify({
                answerId: answerId,
                questionId: questionId,
                userId: userId,
                text: text,
                creationDateTime: creationDateTime,
                score: score
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type": "application/json"
            }
        }).then(response => response.json());
    }
}