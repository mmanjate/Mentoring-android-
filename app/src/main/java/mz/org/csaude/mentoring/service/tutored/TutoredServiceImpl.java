package mz.org.csaude.mentoring.service.tutored;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.career.CareerDAO;
import mz.org.csaude.mentoring.dao.tutored.TutoredDao;
import mz.org.csaude.mentoring.dto.career.CareerDTO;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.tutored.Tutored;
import mz.org.csaude.mentoring.model.user.User;
import mz.org.csaude.mentoring.service.career.CareerService;

public class TutoredServiceImpl extends BaseServiceImpl<Tutored> implements TutoredService{

    TutoredDao tutoredDao;

    CareerDAO careerDAO;

    public TutoredServiceImpl(Application application, User currentUser) {
        super(application, currentUser);
    }

    public TutoredServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application, User currentUser) throws SQLException {
        super.init(application, currentUser);
        this.tutoredDao = getDataBaseHelper().getTutoredDao();
        this.careerDAO = getDataBaseHelper().getCareerDAO();
    }

    public Tutored save(Tutored tutored) throws SQLException {
        this.tutoredDao.create(tutored);
        return tutored;

    }

    @Override
    public Tutored update(Tutored record) throws SQLException {
        this.tutoredDao.update(record);
        return record;
    }

    @Override
    public int delete(Tutored record) throws SQLException {
        return this.tutoredDao.delete(record);
    }

    @Override
    public List<Tutored> getAll() throws SQLException {
        return this.tutoredDao.queryForAll();
    }

    @Override
    public Tutored getById(int id) throws SQLException {
        return this.tutoredDao.queryForId(id);
    }

    @Override
    public void savedOrUpdateTutoreds(List<TutoredDTO> tutoredDTOS) throws SQLException {
        for (TutoredDTO tutoredDTO: tutoredDTOS) {
            boolean doesTutoredExist = this.tutoredDao.checkTutoredExistance(tutoredDTO.getUuid());
            if(!doesTutoredExist){
                CareerDTO careerDTO = tutoredDTO.getCareerDTO();
                List<Career> careers = this.careerDAO.queryForEq("uuid", careerDTO.getUuid());
                if(careers.isEmpty()){
                    throw new RuntimeException("Respectiva carreira com uuid: "+careerDTO.getUuid()+" nao existe!");
                }
                Career career = careers.get(0);
                Tutored tutored = new Tutored(tutoredDTO);
                tutored.setCareer(career);
                this.tutoredDao.createOrUpdate(tutored);
            }
        }

    }
}
