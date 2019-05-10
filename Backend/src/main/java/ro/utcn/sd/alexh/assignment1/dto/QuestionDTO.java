package ro.utcn.sd.alexh.assignment1.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import ro.utcn.sd.alexh.assignment1.entity.Question;
import ro.utcn.sd.alexh.assignment1.entity.Tag;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionDTO {
    private Integer questionId;
    private String user;
    private String title;
    private String text;
    private String creationDateTime;
    private int score;
    private List<String> tags;

    public static QuestionDTO ofEntity(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setCreationDateTime(question.getCreationDateTime().toString());
        questionDTO.setUser(question.getUserId().toString());
        questionDTO.setScore(question.getScore());
        questionDTO.setText(question.getText());
        questionDTO.setTitle(question.getTitle());
        if (!CollectionUtils.isEmpty(question.getTags())) {
            questionDTO.setTags(question.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.toList())
            );
        }

        return questionDTO;
    }
}
