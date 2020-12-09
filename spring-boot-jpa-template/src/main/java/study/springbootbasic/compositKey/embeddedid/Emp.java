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
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emp {

    @Id
    @GeneratedValue
    @Column(name = "emp_id")
    private Long id;

    @Column(name = "emp_nm")
    private String name;

    public Emp(String name) {
        this.name = name;
    }
}
