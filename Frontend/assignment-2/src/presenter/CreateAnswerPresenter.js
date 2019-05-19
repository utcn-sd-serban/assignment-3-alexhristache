import answerModel from "../model/AnswerModel";
import userModel from "../model/UserModel";

class CreateAnswerPresenter {

    onCreate(id) {
        var tempDate = new Date();
        var dateTime = tempDate.getFullYear() + '-' + (tempDate.getMonth() + 1) + '-' + tempDate.getDate() + ' ' + tempDate.getHours() + ':' + tempDate.getMinutes() + ':' + tempDate.getSeconds();

        answerModel.addAnswer(answerModel.state.newAnswer.answerId, answerModel.state.newAnswer.questionId, userModel.findByUsername(userModel.state.loggedUser), answerModel.state.newAnswer.text, dateTime, 0);
        window.location.assign("#/question-details/" + answerModel.state.newAnswer.questionId);
        answerModel.changeNewAnswerProperty("answerId", 0);
        answerModel.changeNewAnswerProperty("questionId", 0);
        answerModel.changeNewAnswerProperty("user", "");
        answerModel.changeNewAnswerProperty("text", "");
        answerModel.changeNewAnswerProperty("score", 0);
    }

    onChange(property, value) {
        answerModel.changeNewAnswerProperty(property, value);
    }

    onEditSubmit(id) {
        debugger;
        let answer = answerModel.findById(id);
        answerModel.editAnswer(id, answer.questionId, answer.userId, answerModel.state.newAnswer.text, answer.creationDateTime, answer.score);
        window.location.assign("#/question-details/" + id);
    }
}

const createAnswerPresenter = new CreateAnswerPresenter();

export default createAnswerPresenter;