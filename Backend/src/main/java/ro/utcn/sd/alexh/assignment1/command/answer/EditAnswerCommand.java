package ro.utcn.sd.alexh.assignment1.command.answer;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;

@RequiredArgsConstructor
public class EditAnswerCommand implements Command {

    private final Integer userId;
    private final AnswerDTO dto;
    private final AnswerManagementService answerManagementService;

    @Override
    public DTO execute() {
        answerManagementService.editAnswer(userId, dto.getAnswerId(), dto.getText());
        return dto;
    }
}
