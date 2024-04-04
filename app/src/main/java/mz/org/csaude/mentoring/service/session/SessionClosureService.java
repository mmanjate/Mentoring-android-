package mz.org.csaude.mentoring.service.session;

import java.util.Date;

import mz.org.csaude.mentoring.base.service.BaseService;
import mz.org.csaude.mentoring.model.session.Session;

public interface SessionClosureService extends BaseService<Session> {
    Session endSession(Session record, Date endDate);
}
