package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnswersDTO {

    private final AnswerManagementService answerManagementService;

    @GetMapping("/answers")
    public List<AnswerDTO> readAll() {
        return answerManagementService.listAnswers();
    }

    @GetMapping("/answers/{id}")
    public AnswerDTO read(@PathVariable int id) {
        return answerManagementService.findAnswerById(id);
    }

    @PostMapping("/answers")
    public AnswerDTO create(@RequestBody AnswerDTO dto) {
        return answerManagementService.addAnswer(dto);
    }
}
