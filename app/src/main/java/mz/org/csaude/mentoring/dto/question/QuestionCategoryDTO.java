package mz.org.csaude.mentoring.dto.question;

import lombok.Data;
import lombok.NoArgsConstructor;
import mz.org.csaude.mentoring.base.dto.BaseEntityDTO;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;
@Data
@NoArgsConstructor
public class QuestionCategoryDTO extends BaseEntityDTO {
    private String category;
    public QuestionCategoryDTO(QuestionsCategory questionsCategory) {
        super(questionsCategory);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public QuestionsCategory getQuestionCategory() {
        QuestionsCategory questionsCategory = new QuestionsCategory();
        questionsCategory.setId(this.getId());
        questionsCategory.setCategory(this.getCategory());
        return questionsCategory;
    }
}
