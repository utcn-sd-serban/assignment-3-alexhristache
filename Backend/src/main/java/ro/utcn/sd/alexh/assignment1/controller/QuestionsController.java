package ro.utcn.sd.alexh.assignment1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.alexh.assignment1.command.Invoker;
import ro.utcn.sd.alexh.assignment1.command.question.CreateQuestionCommand;
import ro.utcn.sd.alexh.assignment1.command.question.ReadAllQuestionsCommand;
import ro.utcn.sd.alexh.assignment1.command.question.ReadQuestionCommand;
import ro.utcn.sd.alexh.assignment1.dto.QuestionDTO;
import ro.utcn.sd.alexh.assignment1.dto.QuestionListDTO;
import ro.utcn.sd.alexh.assignment1.event.BaseEvent;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionsController {

    private final QuestionManagementService questionManagementService;
    private final SimpMessagingTemplate messagingTemplate;
    private final Invoker invoker;

    @GetMapping("/questions")
    public List<QuestionDTO> readAll() {
        return ((QuestionListDTO) invoker.invoke(new ReadAllQuestionsCommand(questionManagementService))).getList();
    }

    @GetMapping("/questions/{id}")
    public QuestionDTO read(@PathVariable int id) {
        return (QuestionDTO) invoker.invoke(new ReadQuestionCommand(id, questionManagementService));
    }

    @GetMapping("/questions?title={title}")
    public List<QuestionDTO> filterByTitle(@PathVariable String title) {
        return questionManagementService.listQuestionsByText(title);
    }

    @GetMapping("/questions?tag={tag}")
    public List<QuestionDTO> filterByTag(@PathVariable String tag) {
        return questionManagementService.listQuestionsByTag(tag);
    }

    @PostMapping("/questions")
    public QuestionDTO create(@RequestBody QuestionDTO dto) {
        return (QuestionDTO) invoker.invoke(new CreateQuestionCommand(dto, questionManagementService));
    }

    @EventListener(BaseEvent.class)
    public void handleEvent(BaseEvent event) {
        log.info("Got an event: {}.", event);
        messagingTemplate.convertAndSend("/topic/events", event);
    }
}
