import React, { Component } from "react";
import answerModel from "../model/AnswerModel";
import CreateAnswer from "./CreateAnswer";
import createAnswerPresenter from "../presenter/CreateAnswerPresenter"

const mapModelStateToComponentState = modelState => ({
    text: modelState.newAnswer.text,
    id: modelState.newAnswer.answerId
});

export default class SmartCreateAnswer extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(answerModel.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        answerModel.addListener("change", this.listener);
    }

    componentWillUnmount() {
        answerModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <CreateAnswer
                onChange={createAnswerPresenter.onChange}
                onSubmit={createAnswerPresenter.onEditSubmit}
                text={this.state.text}
                id={this.state.id}
            />
        );
    }
}