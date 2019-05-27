import React, { Component } from "react";
import questionModel from "../model/QuestionModel";
import CreateQuestion from "./CreateQuestion";
import createQuestionPresenter from "../presenter/CreateQuestionPresenter"

const mapModelStateToComponentState = modelState => ({
    title: modelState.newQuestion.title,
    text: modelState.newQuestion.text,
    tags: modelState.newQuestion.tags
});

export default class SmartCreateQuestion extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(questionModel.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        questionModel.addListener("change", this.listener);
    }

    componentWillUnmount() {
        questionModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <CreateQuestion
                onCreate={createQuestionPresenter.onCreate}
                onChange={createQuestionPresenter.onChange}
                title={this.state.title}
                text={this.state.text}
                tags={this.state.tags}
            />
        );
    }
}