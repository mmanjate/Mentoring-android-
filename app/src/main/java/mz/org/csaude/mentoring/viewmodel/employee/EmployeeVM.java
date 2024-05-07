package mz.org.csaude.mentoring.viewmodel.employee;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import java.sql.SQLException;

import mz.org.csaude.mentoring.adapter.recyclerview.listable.Listble;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.model.employee.Employee;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.professionalCategory.ProfessionalCategory;
import mz.org.csaude.mentoring.service.employee.EmployeeService;
import mz.org.csaude.mentoring.service.employee.EmployeeServiceImpl;

public class EmployeeVM extends BaseViewModel {

    private Employee employee;
    private EmployeeService employeeService;
    public EmployeeVM(@NonNull Application application) {
        super(application);
        this.employeeService = new EmployeeServiceImpl(application );
    }
    @Override
    public void preInit() {

    }

    @Bindable
    public String getName(){
        return this.employee.getName();
    }
    public void setName(String name){
        this.employee.setName(name);
    }
    @Bindable
    public String getSurname(){
       return this.employee.getSurname();
    }
    public void setSurname(String surname){
        this.employee.setSurname(surname);
    }
    @Bindable
    public String getPhoneNumber(){
        return this.employee.getPhoneNumber();
    }
    public void setPhoneNumber(String phoneNumber){
        this.employee.setPhoneNumber(phoneNumber);
    }
    @Bindable
    public long getNuit(){
       return this.employee.getNuit();
    }
    public void setNuit(long nuit){
        this.employee.setNuit(nuit);
    }

    @Bindable
    public int getTrainingYear(){
        return this.employee.getTrainingYear();
    }
    public void setgetTrainingYear(int trainingYear){
        this.employee.setTrainingYear(trainingYear);
    }
    //@Bindable
    public Listble gePartne(){
      return this.employee.getPartner();
    }
    public void setPartne(Listble partner){
        this.employee.setPartner((Partner) partner);
    }

    //@Bindable
    public Listble geProfessionalCategory(){
        return this.employee.getProfessionalCategory();
    }

    public void setProfessionalCategory(Listble professionalCategory){
        this.employee.setProfessionalCategory((ProfessionalCategory) professionalCategory);
    }

    @Bindable
    public String getEmail(){
      return  this.employee.getEmail();
    }
    public void setEmail(String email){
        this.employee.setEmail(email);
    }
    @Bindable
    public Employee getEmployee(){
        return this.employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public Employee saveOrUpdate() {
        Employee employee1 = null;
        try {
            employee1 = this.employeeService.saveOrUpdateEmployee(this.employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee1;
    }

}
