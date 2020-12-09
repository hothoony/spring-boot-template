package study.springbootbasic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BoardDto2 {

    private Long id3;
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    private LocalDate startDate;
    private LocalDate endDate;

    public BoardDto2(String name) {
        this.name = name;
    }
}
