package mz.org.csaude.mentoring.dto.responseType;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.responseType.ResponseType;
@Data
@NoArgsConstructor
public class ResponseTypeDTO extends BaseEntityDTO {
    private String code;
    private String description;
    public ResponseTypeDTO(ResponseType responseType) {
        super(responseType);
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
    public ResponseType getResponseType() {
        ResponseType responseType = new ResponseType();
        responseType.setUuid(this.getUuid());
        responseType.setCode(this.getCode());
        responseType.setDescription(this.getDescription());
        return responseType;
    }
}
