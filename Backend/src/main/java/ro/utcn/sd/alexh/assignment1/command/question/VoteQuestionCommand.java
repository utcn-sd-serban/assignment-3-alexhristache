package ro.utcn.sd.alexh.assignment1.command.question;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.dto.VoteDTO;
import ro.utcn.sd.alexh.assignment1.entity.QuestionVote;
import ro.utcn.sd.alexh.assignment1.exception.QuestionVoteNotFoundException;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;
import ro.utcn.sd.alexh.assignment1.service.UserUserDetailsService;
import ro.utcn.sd.alexh.assignment1.service.VoteService;

@RequiredArgsConstructor
public class VoteQuestionCommand implements Command {

    private final VoteDTO voteDTO;
    private final QuestionManagementService questionManagementService;
    private final VoteService voteService;
    private final UserUserDetailsService userUserDetailsService;

    @Override
    public DTO execute() {
        try {
            questionManagementService.removeVote(voteService.removeQuestionVote(voteDTO.getId(), userUserDetailsService.loadCurrentUser().getUserId()));
        } catch (QuestionVoteNotFoundException ignored) {}
        QuestionVote questionVote = voteService.addQuestionVote(voteDTO.getId(), userUserDetailsService.loadCurrentUser().getUserId(), voteDTO.getVote());
        questionManagementService.addVote(questionVote);
        return null;
    }
}
