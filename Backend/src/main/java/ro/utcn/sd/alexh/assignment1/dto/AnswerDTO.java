package ro.utcn.sd.alexh.assignment1.dto;

import lombok.Data;
import ro.utcn.sd.alexh.assignment1.entity.Answer;

import java.sql.Timestamp;

@Data
public class AnswerDTO implements DTO {
    private Integer answerId;
    private Integer questionId;
    private Integer userId;
    private String text;
    private String creationDateTime;
    private int score;

    public static AnswerDTO ofEntity(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerId(answer.getAnswerId());
        answerDTO.setQuestionId(answer.getQuestionId());
        answerDTO.setUserId(answer.getUserId());
        answerDTO.setText(answer.getText());
        answerDTO.setCreationDateTime(String.valueOf(answer.getCreationDateTime()));
        answerDTO.setScore(answer.getScore());
        return answerDTO;
    }

    public static Answer ofDTO(AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setAnswerId(answerDTO.getAnswerId());
        answer.setQuestionId(answerDTO.getQuestionId());
        answer.setUserId(answerDTO.getUserId());
        answer.setText(answerDTO.getText());
        answer.setCreationDateTime(Timestamp.valueOf(answerDTO.getCreationDateTime()));
        answer.setScore(answerDTO.getScore());
        return answer;
    }
}
