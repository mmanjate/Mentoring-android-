package mz.org.csaude.mentoring.model.tutored;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.model.BaseModel;
import mz.org.csaude.mentoring.dao.tutored.TutoredDaoImpl;
import mz.org.csaude.mentoring.dto.tutored.TutoredDTO;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.user.UserIndividual;



@DatabaseTable(tableName = Tutored.COLUMN_TABLE_NAME, daoClass = TutoredDaoImpl.class)
public class Tutored extends BaseModel implements Listble {

    public static final String COLUMN_TABLE_NAME = "tutored";
    public static final String COLUMN_EMPLOYEE = "emplyee_id";

    @DatabaseField(columnName = COLUMN_EMPLOYEE, foreign = true, foreignAutoRefresh = true )
    private Employee employee;

    public Tutored() {
    }

    public Tutored(Employee employee) {
        this.setEmployee(employee);
    }

    public Tutored(TutoredDTO tutoredDTO) {
        this.setUuid(tutoredDTO.getUuid());
        this.setEmployee(new Employee(tutoredDTO.getEmployeeDTO()));
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getDrawable() {
        return 0;
    }

    @Override
    public String getCode() {
        return null;
    }
}
