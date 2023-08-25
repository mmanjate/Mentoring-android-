package mz.org.csaude.mentoring.service.location;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.location.Province;
import mz.org.csaude.mentoring.model.tutor.Tutor;

public interface ProvinceService extends BaseService<Province> {
    Listble getAllOfTutor(Tutor tutor);
}
