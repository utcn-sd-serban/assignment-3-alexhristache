import React, { Component } from "react";
import questionModel from "../model/QuestionModel";
import answerModel from "../model/AnswerModel";
import QuestionDetails from "./QuestionDetails";
import AnswerList from "./AnswerList";
import answerPresenter from "../presenter/AnswerPresenter";

const mapModelStateToComponentState = (questionModelState, answerModelState, props) => ({
    question: questionModelState.questions.find((question) => question.questionId == props.match.params.index),
    answers: answerModelState.answers.filter((answer) => answer.questionId == props.match.params.index)
});

export default class SmartQuestionDetails extends Component {
    constructor(props) {
        super(props);
        this.state = mapModelStateToComponentState(questionModel.state, answerModel.state, this.props);
        this.questionListener = questionModelState => this.setState(mapModelStateToComponentState(questionModelState, answerModel.state, this.props));
        this.answerListener = answerModelState => this.setState(mapModelStateToComponentState(questionModel.state, answerModelState, this.props));
        questionModel.addListener("change", this.questionListener);
        answerModel.addListener("change", this.answerListener);
    }

    componentDidUpdate(prev) {
        if (prev.match.params.index !== this.props.match.params.index) {
            this.setState(
                mapModelStateToComponentState(questionModel.state, answerModel.state, this.props)
            );
        }
    }

    componentWillUnmount() {
        questionModel.removeListener("change", this.questionListener);
        answerModel.removeListener("change", this.answerListener);
    }

    render() {
        return (
            <div>
                <QuestionDetails
                    questionId={this.state.question.questionId}
                    user={this.state.question.user}
                    title={this.state.question.title}
                    text={this.state.question.text}
                    creationDateTime={this.state.question.creationDateTime}
                    score={this.state.question.score}
                    onCreateAnswer={answerPresenter.onCreateAnswer}
                />
                <AnswerList
                    answers={this.state.answers}
                    onRemove={answerPresenter.onRemove}
                    onUpvote={answerPresenter.onUpvote}
                    onDownvote={answerPresenter.onDownvote}
                    onEdit={answerPresenter.onEdit}
                />
            </div>
        );
    }
}