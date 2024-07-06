package mz.org.csaude.mentoring.dto.resource;

import lombok.Data;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.resourceea.Resource;

@Data
public class ResourceDTO extends BaseEntityDTO {

    private String resource;

    public ResourceDTO() {
    }

    public ResourceDTO(Resource resource) {
        super(resource);
        this.setResource(resource.getResource());
    }

    public Resource getResourceModel(){
        Resource resource1 = new Resource();
        resource1.setUuid(this.getUuid());
        resource1.setUpdatedAt(this.getUpdatedAt());
        resource1.setCreatedAt(this.getCreatedAt());
        resource1.setResource(this.getResource());
        return resource1;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
