package mz.org.csaude.mentoring.service.ronda;

import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.ronda.Ronda;

public interface RondaService extends BaseService<Ronda> {
    List<Ronda> doSearch(long offset, long limit);
}
