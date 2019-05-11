package ro.utcn.sd.alexh.assignment1.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.utcn.sd.alexh.assignment1.entity.Question;
import ro.utcn.sd.alexh.assignment1.entity.Tag;
import ro.utcn.sd.alexh.assignment1.service.TagManagementService;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class QuestionDTO {
    private Integer questionId;
    private String user;
    private String title;
    private String text;
    private String creationDateTime;
    private int score;
    private String tags;

    public static QuestionDTO ofEntity(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setCreationDateTime(question.getCreationDateTime().toString());
        questionDTO.setUser(question.getUserId().toString());
        questionDTO.setScore(question.getScore());
        questionDTO.setText(question.getText());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setTags(question.getTags().toString().replace("[","").replace("]",""));

        return questionDTO;
    }

    public static Question ofDTO(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setQuestionId(questionDTO.getQuestionId());
        question.setUserId(Integer.parseInt(questionDTO.getUser())); // TODO
        question.setTitle(questionDTO.getTitle());
        question.setText(questionDTO.getText());
        question.setCreationDateTime(Timestamp.valueOf(questionDTO.getCreationDateTime()));
        question.setScore(questionDTO.getScore());
        question.setAnswers(Collections.emptyList());

        List<Tag> tags = new LinkedList<>();
        String[] stringTags = questionDTO.getTags().split("\\s*,\\s*");
        for (String stringTag : stringTags) {
//            Tag tag = tagManagementService.addTag(null, stringTag);
            Tag tag = new Tag(null, stringTag);
            tags.add(tag);
        }
        question.setTags(tags);
        return question;
    }
}
