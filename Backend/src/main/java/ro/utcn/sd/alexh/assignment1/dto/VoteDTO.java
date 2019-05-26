package ro.utcn.sd.alexh.assignment1.dto;

import lombok.Data;

@Data
public class VoteDTO implements DTO {
    private Integer id;
    private int vote;
}
