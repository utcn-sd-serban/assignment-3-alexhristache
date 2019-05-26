package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.alexh.assignment1.command.Invoker;
import ro.utcn.sd.alexh.assignment1.command.answer.VoteAnswerCommand;
import ro.utcn.sd.alexh.assignment1.command.question.VoteQuestionCommand;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.dto.VoteDTO;
import ro.utcn.sd.alexh.assignment1.entity.AnswerVote;
import ro.utcn.sd.alexh.assignment1.entity.QuestionVote;
import ro.utcn.sd.alexh.assignment1.exception.AnswerVoteNotFoundException;
import ro.utcn.sd.alexh.assignment1.exception.QuestionVoteNotFoundException;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;
import ro.utcn.sd.alexh.assignment1.service.UserUserDetailsService;
import ro.utcn.sd.alexh.assignment1.service.VoteService;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;
    private final QuestionManagementService questionManagementService;
    private final AnswerManagementService answerManagementService;
    private final UserUserDetailsService userUserDetailsService;
    private final Invoker invoker;

    @PostMapping("/vote-question")
    public DTO voteQuestion(@RequestBody VoteDTO dto) {
        return invoker.invoke(new VoteQuestionCommand(dto, questionManagementService, voteService, userUserDetailsService));
    }

    @PostMapping("/vote-answer")
    public DTO voteAnswer(@RequestBody VoteDTO dto) {
        return invoker.invoke(new VoteAnswerCommand(dto, answerManagementService, voteService, userUserDetailsService));
    }
}
