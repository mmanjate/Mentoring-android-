package mz.org.csaude.mentoring.service.career;

import java.sql.SQLException;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.career.CareerType;

public interface CareerTypeService extends BaseService<CareerType> {

    public CareerType savedOrUpdateCareerTypes(CareerType careerType) throws SQLException;

    public Listble getAllCareerTypes();
}
