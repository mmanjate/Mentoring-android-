package mz.org.csaude.mentoring.model.career;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class Career extends BaseModel {

    private CareerType careerType;

    private String position;

    public Career() {
    }

    public CareerType getCareerType() {
        return careerType;
    }

    public String getPosition() {
        return position;
    }

    public void setCareerType(CareerType careerType) {
        this.careerType = careerType;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
