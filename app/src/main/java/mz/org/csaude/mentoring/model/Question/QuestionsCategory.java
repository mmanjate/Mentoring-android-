package mz.org.csaude.mentoring.model.Question;

import mz.org.csaude.mentoring.base.model.BaseModel;

public class QuestionsCategory extends BaseModel {

    private String category;

    public QuestionsCategory() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
