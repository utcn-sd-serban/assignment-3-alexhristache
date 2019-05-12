package ro.utcn.sd.alexh.assignment1.command.question;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.dto.QuestionListDTO;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;

@RequiredArgsConstructor
public class ReadAllQuestionsCommand implements Command {

    private final QuestionManagementService questionManagementService;

    @Override
    public DTO execute() {
        return new QuestionListDTO(questionManagementService.listQuestions());
    }
}
