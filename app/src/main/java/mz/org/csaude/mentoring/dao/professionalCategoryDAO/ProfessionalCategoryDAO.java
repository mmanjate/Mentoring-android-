package mz.org.csaude.mentoring.dao.professionalCategoryDAO;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;

public interface ProfessionalCategoryDAO extends Dao<ProfessionalCategory, Integer> {

    boolean checkExisteProfessionalCategory(String uuid) throws SQLException;
}
