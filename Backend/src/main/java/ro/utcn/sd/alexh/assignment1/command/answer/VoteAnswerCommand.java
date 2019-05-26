package ro.utcn.sd.alexh.assignment1.command.answer;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.dto.VoteDTO;
import ro.utcn.sd.alexh.assignment1.entity.AnswerVote;
import ro.utcn.sd.alexh.assignment1.exception.AnswerVoteNotFoundException;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;
import ro.utcn.sd.alexh.assignment1.service.UserUserDetailsService;
import ro.utcn.sd.alexh.assignment1.service.VoteService;

@RequiredArgsConstructor
public class VoteAnswerCommand implements Command {

    private final VoteDTO voteDTO;
    private final AnswerManagementService answerManagementService;
    private final VoteService voteService;
    private final UserUserDetailsService userUserDetailsService;

    @Override
    public DTO execute() {
        try {
            answerManagementService.removeVote(voteService.removeAnswerVote(voteDTO.getId(), userUserDetailsService.loadCurrentUser().getUserId()));
        } catch (AnswerVoteNotFoundException ignored) {}
        AnswerVote answerVote = voteService.addAnswerVote(voteDTO.getId(), userUserDetailsService.loadCurrentUser().getUserId(), voteDTO.getVote());
        answerManagementService.addVote(answerVote);
        return null;
    }
}
