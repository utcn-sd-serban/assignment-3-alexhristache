import questionModel from "../model/QuestionModel";
import userModel from "../model/UserModel";

class CreateQuestionPresenter {

    onCreate() {
        var tempDate = new Date();
        var dateTime = tempDate.getFullYear() + '-' + (tempDate.getMonth()+1) + '-' + tempDate.getDate() +' '+ tempDate.getHours()+':'+ tempDate.getMinutes()+':'+ tempDate.getSeconds();

        questionModel.addQuestion(questionModel.state.newQuestion.questionId, userModel.state.loggedUser, questionModel.state.newQuestion.title, questionModel.state.newQuestion.text, dateTime, questionModel.state.newQuestion.tags, 0)
        .then(() => {
            questionModel.changeNewQuestionProperty("questionId", 0);
            questionModel.changeNewQuestionProperty("user", "");
            questionModel.changeNewQuestionProperty("title", "");
            questionModel.changeNewQuestionProperty("text", "");
            questionModel.changeNewQuestionProperty("creationDateTime", "");
            questionModel.changeNewQuestionProperty("tags", "");
            questionModel.changeNewQuestionProperty("score", 0);
            window.location.assign("#/");
        });
    }

    onChange(property, value) {
        questionModel.changeNewQuestionProperty(property, value);
    }

}

const createQuestionPresenter = new CreateQuestionPresenter();

export default createQuestionPresenter;