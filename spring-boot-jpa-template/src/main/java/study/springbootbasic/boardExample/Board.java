package study.springbootbasic.boardExample;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import study.springbootbasic.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@DynamicUpdate // 변경된 field 만 update
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
//    private String createdBy;
//    private String modifiedBy;
//    private LocalDateTime createdDate;
//    private LocalDateTime modifiedDate;
}
