package mz.org.csaude.mentoring.service.form;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.form.FormTargetDAO;
import mz.org.csaude.mentoring.model.form.FormTarget;
import mz.org.csaude.mentoring.model.user.User;

public class FormTargetServiceImpl extends BaseServiceImpl<FormTarget> implements FormTargetService {

    FormTargetDAO formTargetDAO;


    public FormTargetServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.formTargetDAO = getDataBaseHelper().getFormTargetDAO();
    }

    @Override
    public FormTarget save(FormTarget record) throws SQLException {
        this.formTargetDAO.create(record);
        return record;
    }

    @Override
    public FormTarget update(FormTarget record) throws SQLException {
        this.formTargetDAO.update(record);
        return record;
    }

    @Override
    public int delete(FormTarget record) throws SQLException {
        return this.formTargetDAO.delete(record);
    }

    @Override
    public List<FormTarget> getAll() throws SQLException {
        return this.formTargetDAO.queryForAll();
    }

    @Override
    public FormTarget getById(int id) throws SQLException {
        return this.formTargetDAO.queryForId(id);
    }
}
