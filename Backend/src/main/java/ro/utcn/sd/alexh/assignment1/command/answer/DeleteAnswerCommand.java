package ro.utcn.sd.alexh.assignment1.command.answer;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;

@RequiredArgsConstructor
public class DeleteAnswerCommand implements Command {

    private final Integer answerId;
    private final Integer userId;
    private final AnswerManagementService answerManagementService;

    @Override
    public DTO execute() {
        AnswerDTO answerDTO = answerManagementService.findAnswerById(answerId);
        answerManagementService.deleteAnswer(userId, answerId);
        return answerDTO;
    }
}
