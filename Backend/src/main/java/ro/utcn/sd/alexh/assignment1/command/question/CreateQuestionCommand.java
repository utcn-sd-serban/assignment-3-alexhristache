package ro.utcn.sd.alexh.assignment1.command.question;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.dto.QuestionDTO;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;

@RequiredArgsConstructor
public class CreateQuestionCommand implements Command {

    private final QuestionDTO dto;
    private final QuestionManagementService questionManagementService;

    @Override
    public DTO execute() {
        return questionManagementService.addQuestion(dto);
    }
}
