package ro.utcn.sd.alexh.assignment1.dto;

import lombok.Data;
import ro.utcn.sd.alexh.assignment1.entity.Answer;

@Data
public class AnswerDTO {
    private Integer answerId;
    private Integer questionId;
    private String user;
    private String text;
    private String creationDateTime;
    private int score;

    public static AnswerDTO ofEntity(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerId(answer.getAnswerId());
        answerDTO.setQuestionId(answer.getQuestionId());
        answerDTO.setUser(answer.getUserId().toString()); // TODO: use username instead of id
        answerDTO.setText(answer.getText());
        answerDTO.setCreationDateTime(String.valueOf(answer.getCreationDateTime()));
        answerDTO.setScore(answer.getScore());
        return answerDTO;
    }
}
