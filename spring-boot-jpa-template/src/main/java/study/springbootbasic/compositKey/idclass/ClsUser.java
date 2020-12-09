package study.springbootbasic.compositKey.idclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(ClsUerId.class)
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClsUser {

    @Id
    private Long clsId;

    @Id
    private Long userId;

    public ClsUser(Long clsId, Long userId) {
        this.clsId = clsId;
        this.userId = userId;
    }
}
