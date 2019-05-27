package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.command.Invoker;
import ro.utcn.sd.alexh.assignment1.command.answer.*;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.AnswerListDTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;
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
        return ((AnswerListDTO) invoker.invoke(new ReadAllAnswersCommand(answerManagementService))).getList();
    }

    @GetMapping("/answers/{id}")
    public AnswerDTO read(@PathVariable int id) {
        return answerManagementService.findAnswerById(id);
    }

    @GetMapping("/questions/{questionId}/answers")
    public List<AnswerDTO> readAnswersForQuestion(@PathVariable Integer questionId) {
        return ((AnswerListDTO) invoker.invoke(new ReadAnswersForQuestion(questionId, answerManagementService))).getList();
    }

    @PostMapping("/answers")
    public AnswerDTO create(@RequestBody AnswerDTO dto) {
        return (AnswerDTO) invoker.invoke(new CreateAnswerCommand(dto, answerManagementService));
    }

    @DeleteMapping("/answers/{id}")
    public AnswerDTO delete(@PathVariable Integer id) {
        Integer userId = userDetailsService.loadCurrentUser().getUserId();
        return (AnswerDTO) invoker.invoke(new DeleteAnswerCommand(id, userId, answerManagementService));
    }

    @PutMapping("/answers/{id}")
    public AnswerDTO edit(@RequestBody AnswerDTO dto, @PathVariable Integer id) {
        Integer userId = userDetailsService.loadCurrentUser().getUserId();
        return (AnswerDTO) invoker.invoke(new EditAnswerCommand(userId, dto, answerManagementService));
    }
}
