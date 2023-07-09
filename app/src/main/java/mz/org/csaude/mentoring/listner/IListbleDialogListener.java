package mz.org.csaude.mentoring.listner;

import java.sql.SQLException;

import mz.org.csaude.mentoring.base.model.BaseModel;

public interface IListbleDialogListener {

    void remove(int position) throws SQLException;

    void remove(BaseModel baseModel);
}
