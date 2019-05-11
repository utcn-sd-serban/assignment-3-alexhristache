package ro.utcn.sd.alexh.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.alexh.assignment1.dto.AnswerDTO;
import ro.utcn.sd.alexh.assignment1.dto.QuestionDTO;
import ro.utcn.sd.alexh.assignment1.dto.UserDTO;
import ro.utcn.sd.alexh.assignment1.entity.Answer;
import ro.utcn.sd.alexh.assignment1.entity.Question;
import ro.utcn.sd.alexh.assignment1.entity.Tag;
import ro.utcn.sd.alexh.assignment1.entity.User;
import ro.utcn.sd.alexh.assignment1.service.AnswerManagementService;
import ro.utcn.sd.alexh.assignment1.service.QuestionManagementService;
import ro.utcn.sd.alexh.assignment1.service.TagManagementService;
import ro.utcn.sd.alexh.assignment1.service.UserManagementService;

import java.sql.Timestamp;
import java.util.*;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Seed implements CommandLineRunner {

    private final QuestionManagementService questionService;
    private final TagManagementService tagService;
    private final UserManagementService userService;
    private final AnswerManagementService answerService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) {

        int GEN_COUNT = 110;

        if (tagService.listTags().isEmpty()) {
            for (int i = 100; i < GEN_COUNT; i++) {
                tagService.addTag(i, "Tag" + i);
            }
        }

        if (userService.listUsers().isEmpty()) {
            for (int i = 100; i < GEN_COUNT; i++) {
                userService.addUser(UserDTO.ofEntity(new User(i, i + "@email.com", "user" + i, passwordEncoder.encode("123"), "regular", 0, false)));
            }
        }

        if (questionService.listQuestions().isEmpty()) {
            for (int i = 100; i < GEN_COUNT; i++) {
                questionService.addQuestion(QuestionDTO.ofEntity(new Question(i, i, "Title" + i, "Text " + i, new Timestamp(System.currentTimeMillis()),
                        Collections.singletonList(tagService.findTagById(i)), 0)));
            }
        }

        if (answerService.listAnswers().isEmpty()) {
            for (int i = 100; i < GEN_COUNT; i++) {
                answerService.addAnswer(AnswerDTO.ofEntity(new Answer(i, i, i, "Answer text " + i, new Timestamp(System.currentTimeMillis()),0)));
            }
        }
    }
}
