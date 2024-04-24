package mz.org.csaude.mentoring.service.ronda;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.rondatype.RondaTypeDAO;
import mz.org.csaude.mentoring.dto.location.RondaTypeDTO;
import mz.org.csaude.mentoring.model.rondatype.RondaType;

public class RondaTypeServiceImpl extends BaseServiceImpl<RondaType> implements RondaTypeService {
    RondaTypeDAO rondaTypeDAO;
    public RondaTypeServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.rondaTypeDAO = getDataBaseHelper().getRondaTypeDAO();
    }

    @Override
    public RondaType save(RondaType record) throws SQLException {
        return null;
    }

    @Override
    public RondaType update(RondaType record) throws SQLException {
        return null;
    }

    @Override
    public int delete(RondaType record) throws SQLException {
        return 0;
    }

    @Override
    public List<RondaType> getAll() throws SQLException {
        return null;
    }

    @Override
    public RondaType getById(int id) throws SQLException {
        return null;
    }

    @Override
    public void saveOrUpdateRondaTypes(List<RondaTypeDTO> rondaTypeDTOS) throws SQLException {
        for (RondaTypeDTO dto: rondaTypeDTOS) {
            boolean doesRondaTypeExist = this.rondaTypeDAO.checkRondaTypeExistance(dto.getUuid());
            if(!doesRondaTypeExist) {
                RondaType rondaType = new RondaType(dto);
                this.rondaTypeDAO.createOrUpdate(rondaType);
            }
        }
    }

    @Override
    public RondaType saveOrUpdateRondaType(RondaType rondaType) throws SQLException {
        RondaType rt = this.rondaTypeDAO.getByUuid(rondaType.getUuid());
        if(rt!=null) {
            rondaType.setId(rt.getId());
        }
        this.rondaTypeDAO.createOrUpdate(rondaType);
        return rondaType;
    }

    @Override
    public RondaType getRondaTypeByCode(String code) throws SQLException {
        return rondaTypeDAO.getRondaTypeByCode(code);
    }

    @Override
    public List<RondaType> doSearch(long offset, long limit) {
        return null;
    }
}
