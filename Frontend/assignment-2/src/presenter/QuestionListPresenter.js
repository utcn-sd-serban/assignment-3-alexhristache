import questionModel from "../model/QuestionModel";
import answerModel from "../model/AnswerModel";
import userModel from "../model/UserModel";

class QuestionListPresenter {

    onInit() {
        userModel.loadUsers();
        questionModel.loadQuestions();
        answerModel.loadAnswers();
    }

    onCreateQuestion() {
        window.location.assign("#/create-question");
    }

    onViewDetails(index) {
        answerModel.loadAnswersForQuestion(index);
        window.location.assign("#/question-details/" + index);
    }

    onFilterByTag() {
        questionModel.filterByTag();
        window.location.assign("#/tag/");
    }

    onfilterByTitle() {
        questionModel.filterByTitle();
        window.location.assign("#/title/");
    }

    onChange(property, value) {
        questionModel.changeStateProperty(property, value);
    }

    onUpvote(questionId) {
        questionModel.vote(questionId, 1);
    }

    onDownvote(questionId) {
        questionModel.vote(questionId, -1);
    }
}

const questionListPresenter = new QuestionListPresenter();

export default questionListPresenter;