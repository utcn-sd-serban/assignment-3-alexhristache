import React, { Component } from "react";
import questionModel from "../model/QuestionModel";
import QuestionList from "./QuestionList";
import questionListPresenter from "../presenter/QuestionListPresenter";

const mapModelStateToComponentState = (modelState) => ({
    questions: modelState.filteredQuestions,
    filter: modelState.filter,
});

export default class SmartQuestionListByTag extends Component {
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
            <QuestionList
                questions={this.state.questions}
                onCreateQuestion={questionListPresenter.onCreateQuestion}
                onViewDetails={questionListPresenter.onViewDetails}
                onChange={questionListPresenter.onChange}
                filter={this.state.filter}
                onFilterByTag={questionListPresenter.onFilterByTag}
                onfilterByTitle={questionListPresenter.onfilterByTitle}
                onUpvote={questionListPresenter.onUpvote}
                onDownvote={questionListPresenter.onDownvote}
            />
        );
    }
}