import React, { Component } from "react";
import questionModel from "../model/QuestionModel";
import QuestionList from "./QuestionList";
import questionListPresenter from "../presenter/QuestionListPresenter";

const mapModelStateToComponentState = modelState => ({
    questions: modelState.questions
});

export default class SmartQuestionList extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(questionModel.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        questionModel.addListener("change", this.listener);
        questionListPresenter.onInit();
    }

    componentWillUnmount() {
        questionModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <QuestionList
                questions={this.state.questions}
                onCreateQuestion={questionListPresenter.onCreateQuestion}
                onViewDetails={questionListPresenter.onViewDetails}
                onFilterByTag={questionListPresenter.onFilterByTag}
                onfilterByTitle={questionListPresenter.onfilterByTitle}
                onChange={questionListPresenter.onChange}
                filter={this.state.filter}
                onUpvote={questionListPresenter.onUpvote}
                onDownvote={questionListPresenter.onDownvote}
            />
        );
    }
}