import React, { Component } from "react";
import answerModel from "../model/AnswerModel";
import CreateAnswer from "./CreateAnswer";
import createAnswerPresenter from "../presenter/CreateAnswerPresenter"

const mapModelStateToComponentState = modelState => ({
    text: modelState.newAnswer.text,
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
                onSubmit={createAnswerPresenter.onCreate}
                onChange={createAnswerPresenter.onChange}
                text={this.state.text}
            />
        );
    }
}