package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.command.Invoker;
import ro.utcn.sd.alexh.assignment1.command.answer.*;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.AnswerListDTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;
import ro.utcn.sd.alexh.assignment1.service.UserManagementService;
import ro.utcn.sd.alexh.assignment1.service.UserUserDetailsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnswersController {

    private final AnswerManagementService answerManagementService;
    private final UserUserDetailsService userDetailsService;
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

    @DeleteMapping("/answers/{id}")
    public AnswerDTO delete(@PathVariable Integer id) {
        Integer userId = userDetailsService.loadCurrentUser().getUserId();
        invoker.setCommand(new DeleteAnswerCommand(id, userId, answerManagementService));
        return (AnswerDTO) invoker.invoke();
    }

    @PutMapping("/answers/{id}")
    public AnswerDTO edit(@RequestBody AnswerDTO dto, @PathVariable Integer id) {
        Integer userId = userDetailsService.loadCurrentUser().getUserId();
        invoker.setCommand(new EditAnswerCommand(userId, dto, answerManagementService));
        return (AnswerDTO) invoker.invoke();
    }
}
