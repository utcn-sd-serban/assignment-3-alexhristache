package ro.utcn.sd.alexh.assignment1.command.answer;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.AnswerListDTO;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;

@RequiredArgsConstructor
public class ReadAllAnswersCommand implements Command {

    private final AnswerManagementService answerManagementService;

    @Override
    public DTO execute() {
        return new AnswerListDTO(answerManagementService.listAnswers());
    }
}
