package ro.utcn.sd.alexh.assignment1.command.answer;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;

@RequiredArgsConstructor
public class CreateAnswerCommand implements Command {

    private final AnswerDTO dto;
    private final AnswerManagementService answerManagementService;

    @Override
    public DTO execute() {
        return answerManagementService.addAnswer(dto);
    }
}
