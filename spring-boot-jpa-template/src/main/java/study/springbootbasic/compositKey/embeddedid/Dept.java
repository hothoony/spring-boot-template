package study.springbootbasic.compositKey.embeddedid;

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
public class Dept {

    @Id
    @GeneratedValue
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_nm")
    private String name;

    public Dept(String name) {
        this.name = name;
    }
}
