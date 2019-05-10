import answerModel from "../model/AnswerModel";
import userModel from "../model/UserModel";

class CreateAnswerPresenter {

    onCreate(id) {
        answerModel.addAnswer(12, answerModel.state.newAnswer.questionId, userModel.state.loggedUser, answerModel.state.newAnswer.text, "Today", 0);
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
        answerModel.removeAnswer(id);
        answerModel.addAnswer(id, answer.questionId, answer.user, answerModel.state.newAnswer.text, answer.creationDateTime, answer.score);
        window.location.assign("#/question-details/" + id);
    }
}

const createAnswerPresenter = new CreateAnswerPresenter();

export default createAnswerPresenter;