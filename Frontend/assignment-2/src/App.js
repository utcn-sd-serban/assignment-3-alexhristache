import React, { Component } from 'react';
import './App.css';

import { HashRouter, Switch, Route } from "react-router-dom";
import SmartQuestionList from './view/SmartQuestionList';
import SmartCreateQuestion from './view/SmartCreateQuestion';
import SmartQuestionDetails from './view/SmartQuestionDetails';
import SmartQuestionListByTag from './view/SmartQuestionListByTag';
import SmartQuestionListByText from './view/SmartQuestionListByText';
import SmartLogin from './view/SmartLogin';
import SmartCreateAnswer from './view/SmartCreateAnswer';
import SmartEditAnswer from './view/SmartEditAnswer';

class App extends Component {
  render() {
    return (
      <div className="App">
        <HashRouter>
          <Switch>
            <Route exact={true} component={SmartQuestionList} path="/" />
            <Route exact={true} component={SmartCreateQuestion} path="/create-question" />
            <Route exact={true} component={SmartQuestionDetails} path="/question-details/:index" />
            <Route exact={true} component={SmartQuestionListByTag} path="/tag/" />
            <Route exact={true} component={SmartQuestionListByText} path="/title/" />
            <Route exact={true} component={SmartLogin} path="/login/" />
            <Route exact={true} component={SmartCreateAnswer} path="/create-answer" />
            <Route exact={true} component={SmartEditAnswer} path="/edit-answer" />
          </Switch>
        </HashRouter>
      </div>
    );
  }
}

export default App;
