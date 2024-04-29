package mz.org.csaude.mentoring.dto.session;

import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.session.SessionStatus;

public class SessionStatusDTO extends BaseEntityDTO {
    private String code;
    private String description;
    public SessionStatusDTO(SessionStatus sessionStatus) {
        super(sessionStatus);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
