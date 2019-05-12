package ro.utcn.sd.alexh.assignment1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionListDTO implements DTO {
    private List<QuestionDTO> list;
}
