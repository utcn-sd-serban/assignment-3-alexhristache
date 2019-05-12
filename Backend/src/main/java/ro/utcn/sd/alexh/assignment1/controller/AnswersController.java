package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.command.Command;
import ro.utcn.sd.alexh.assignment1.command.Invoker;
import ro.utcn.sd.alexh.assignment1.command.answer.CreateAnswerCommand;
import ro.utcn.sd.alexh.assignment1.command.answer.ReadAllAnswersCommand;
import ro.utcn.sd.alexh.assignment1.command.answer.ReadAnswersForQuestion;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.AnswerListDTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final AnswerManagementService answerManagementService;
    private final Invoker invoker;

    @GetMapping("/answers")
    public List<AnswerDTO> readAll() {
        invoker.setCommand(new ReadAllAnswersCommand(answerManagementService));
        return ((AnswerListDTO) invoker.invoke()).getList();
    }

    @GetMapping("/answers/{id}")
    public AnswerDTO read(@PathVariable int id) {
        return answerManagementService.findAnswerById(id);
    }

    @GetMapping("/questions/{questionId}/answers")
    public List<AnswerDTO> readAnswersForQuestion(@PathVariable Integer questionId) {
        invoker.setCommand(new ReadAnswersForQuestion(questionId, answerManagementService));
        return ((AnswerListDTO) invoker.invoke()).getList();
    }

    @PostMapping("/answers")
    public AnswerDTO create(@RequestBody AnswerDTO dto) {
        invoker.setCommand(new CreateAnswerCommand(dto, answerManagementService));
        return (AnswerDTO) invoker.invoke();
    }
}
