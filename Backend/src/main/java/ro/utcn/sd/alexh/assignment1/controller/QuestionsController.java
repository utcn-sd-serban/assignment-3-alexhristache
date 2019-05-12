package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.command.Invoker;
import ro.utcn.sd.alexh.assignment1.command.question.CreateQuestionCommand;
import ro.utcn.sd.alexh.assignment1.command.question.ReadAllQuestionsCommand;
import ro.utcn.sd.alexh.assignment1.command.question.ReadQuestionCommand;
import ro.utcn.sd.alexh.assignment1.dto.QuestionListDTO;
import ro.utcn.sd.alexh.assignment1.dto.QuestionDTO;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionsController {

    private final QuestionManagementService questionManagementService;
    private final Invoker invoker;

    @GetMapping("/questions")
    public List<QuestionDTO> readAll() {
        invoker.setCommand(new ReadAllQuestionsCommand(questionManagementService));
        return ((QuestionListDTO) invoker.invoke()).getList();
    }

    @GetMapping("/questions/{id}")
    public QuestionDTO read(@PathVariable int id) {
        invoker.setCommand(new ReadQuestionCommand(id, questionManagementService));
        return (QuestionDTO) invoker.invoke();
    }

    @PostMapping("/questions")
    public QuestionDTO create(@RequestBody QuestionDTO dto) {
        invoker.setCommand(new CreateQuestionCommand(dto, questionManagementService));
        return (QuestionDTO) invoker.invoke();
    }
}
