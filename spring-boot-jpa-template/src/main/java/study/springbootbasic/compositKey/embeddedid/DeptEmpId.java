package study.springbootbasic.compositKey.embeddedid;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeptEmpId implements Serializable {

    private Long deptId;
    private Long empId;

    public DeptEmpId(Long deptId, Long empId) {
        this.deptId = deptId;
        this.empId = empId;
    }
}
