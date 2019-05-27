package ro.utcn.sd.alexh.assignment1.command.answer;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.AnswerListDTO;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReadAnswersForQuestion implements Command {

    private final Integer questionId;
    private final AnswerManagementService answerManagementService;

    @Override
    public DTO execute() {
        return new AnswerListDTO(answerManagementService.findAnswerForQuestion(questionId).stream()
                .map(AnswerDTO::ofEntity)
                .collect(Collectors.toList()));
    }
}
