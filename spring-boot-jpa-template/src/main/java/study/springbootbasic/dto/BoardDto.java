package study.springbootbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardDto {

    @NotNull
    private Long id2;
    private String name;
    private Integer age;
    private LocalDate startDate;
    private LocalDate endDate;

    public BoardDto(String name) {
        this.name = name;
    }
}
