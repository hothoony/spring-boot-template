package study.springbootbasic.compositKey.embeddedid;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeptEmp {

    @EmbeddedId
    private DeptEmpId deptEmpId;

    public DeptEmp(DeptEmpId deptEmpId) {
        this.deptEmpId = deptEmpId;
    }
}
