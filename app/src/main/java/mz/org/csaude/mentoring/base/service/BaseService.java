package mz.org.csaude.mentoring.base.service;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.model.BaseModel;

public interface BaseService <T extends BaseModel>{

    public T save(T record) throws SQLException;

    public T update(T record) throws SQLException;

    public int delete(T record) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id)  throws SQLException;

    T getByuuid(String uuid) throws SQLException;

    public void close();
}
