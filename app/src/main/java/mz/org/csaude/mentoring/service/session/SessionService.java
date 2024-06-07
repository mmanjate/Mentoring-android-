package mz.org.csaude.mentoring.service.session;

import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.ronda.Ronda;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public interface SessionService extends BaseService<Session> {
    List<Session> getAllOfRondaAndMentee(Ronda currRonda, Tutored selectedMentee, long offset, long limit);
}
