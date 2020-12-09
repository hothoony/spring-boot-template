package study.springbootbasic.compositKey.idclass;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ClsUerId implements Serializable {
    private Long clsId;
    private Long userId;
}
