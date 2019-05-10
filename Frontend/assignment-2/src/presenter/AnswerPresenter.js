import answerModel from "../model/AnswerModel";

class AnswerPresenter {

    onChange(id, property, value) {
        answerModel.changeAnswerProperty(id, property, value);
    }

    onRemove(id) {
        answerModel.removeAnswer(id);
    }

    onCreateAnswer(id) {
        answerModel.changeNewAnswerProperty("questionId", id);
        window.location.assign("#/create-answer");
    }

    onUpvote(answerId) {
        answerModel.vote(answerId, 1);
    }

    onDownvote(answerId) {
        answerModel.vote(answerId, -1);
    }
    
    onEdit(id) {
        debugger;
        answerModel.changeNewAnswerProperty("answerId", id);
        window.location.assign("#/edit-answer");
    }
}

const answerPresenter = new AnswerPresenter();

export default answerPresenter;