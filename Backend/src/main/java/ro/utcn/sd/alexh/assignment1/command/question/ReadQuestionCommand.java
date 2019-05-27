package ro.utcn.sd.alexh.assignment1.command.question;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.dto.DTO;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;

@RequiredArgsConstructor
public class ReadQuestionCommand implements Command {

    private final Integer id;
    private final QuestionManagementService questionManagementService;

    @Override
    public DTO execute() {
        return questionManagementService.findQuestionById(id);
    }
}
