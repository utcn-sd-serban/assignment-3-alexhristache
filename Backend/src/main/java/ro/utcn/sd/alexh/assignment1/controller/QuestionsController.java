package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.dto.QuestionDTO;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionsController {

    private final QuestionManagementService questionManagementService;

    @GetMapping("/questions")
    public List<QuestionDTO> readAll() {
        return questionManagementService.listQuestions();
    }

    @GetMapping("/questions/{id}")
    public QuestionDTO read(@PathVariable int id) {
        return questionManagementService.findQuestionById(id);
    }

    @PostMapping("/questions")
    public QuestionDTO create(@RequestBody QuestionDTO dto) {
        return questionManagementService.addQuestion(dto);
    }
}
