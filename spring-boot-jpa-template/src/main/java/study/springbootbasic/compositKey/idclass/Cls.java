package study.springbootbasic.compositKey.idclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cls {

    @Id
    @GeneratedValue
    @Column(name = "cls_id")
    private Long id;

    @Column(name = "cls_nm")
    private String name;

    public Cls(String name) {
        this.name = name;
    }
}
