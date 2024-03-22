package mz.org.csaude.mentoring.service.form;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.form.FormDAO;
import mz.org.csaude.mentoring.dao.programmaticArea.TutorProgrammaticAreaDAO;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.user.User;

public class FormServiceImpl extends BaseServiceImpl<Form> implements FormService{

    FormDAO formDAO;

    TutorProgrammaticAreaDAO tutorProgrammaticAreaDAO;



    public FormServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) {
        try {
            super.init(application);
            this.formDAO = getDataBaseHelper().getFormDAO();
            this.tutorProgrammaticAreaDAO = getDataBaseHelper().getTutorProgrammaticAreaDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Form save(Form record) throws SQLException {
        this.formDAO.create(record);
        return record;
    }

    @Override
    public Form update(Form record) throws SQLException {
        this.formDAO.update(record);
        return record;
    }

    @Override
    public int delete(Form record) throws SQLException {
        return this.formDAO.delete(record);
    }

    @Override
    public List<Form> getAll() throws SQLException {
        return this.formDAO.queryForAll();
    }

    @Override
    public Form getById(int id) throws SQLException {
        return this.formDAO.queryForId(id);
    }

    @Override
    public List<Form> getAllOfTutor(Tutor tutor) throws SQLException {
        return formDAO.getAllOfTutor(tutor, application);
    }
}
