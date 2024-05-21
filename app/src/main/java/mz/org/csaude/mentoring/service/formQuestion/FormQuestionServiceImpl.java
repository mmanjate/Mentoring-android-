package mz.org.csaude.mentoring.service.formQuestion;

import android.app.Application;

import java.sql.SQLException;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseServiceImpl;
import mz.org.csaude.mentoring.dao.formQuestion.FormQuestionDAO;
import mz.org.csaude.mentoring.dao.question.QuestionDAO;
import mz.org.csaude.mentoring.dao.question.QuestionsCategoryDAO;
import mz.org.csaude.mentoring.dto.form.FormQuestionDTO;
import mz.org.csaude.mentoring.dto.question.QuestionCategoryDTO;
import mz.org.csaude.mentoring.dto.question.QuestionDTO;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.question.Question;
import mz.org.csaude.mentoring.model.question.QuestionsCategory;
import mz.org.csaude.mentoring.model.user.User;

public class FormQuestionServiceImpl extends BaseServiceImpl<FormQuestion> implements FormQuestionService {

    FormQuestionDAO formQuestionDAO;
    QuestionDAO questionDAO;
    QuestionsCategoryDAO questionsCategoryDAO;
    public FormQuestionServiceImpl(Application application) {
        super(application);
    }

    @Override
    public void init(Application application) throws SQLException {
        super.init(application);
        this.formQuestionDAO = getDataBaseHelper().getFormQuestionDAO();
        this.questionDAO = getDataBaseHelper().getQuestionDAO();
        this.questionsCategoryDAO = getDataBaseHelper().getQuestionsCategoryDAO();
    }
    @Override
    public FormQuestion save(FormQuestion record) throws SQLException {
        this.formQuestionDAO.create(record);
        return record;
    }

    @Override
    public FormQuestion update(FormQuestion record) throws SQLException {
        this.formQuestionDAO.update(record);
        return record;
    }

    @Override
    public int delete(FormQuestion record) throws SQLException {
        return this.formQuestionDAO.delete(record);
    }

    @Override
    public List<FormQuestion> getAll() throws SQLException {
        return this.formQuestionDAO.queryForAll();
    }

    @Override
    public FormQuestion getById(int id) throws SQLException {
        return this.formQuestionDAO.queryForId(id);
    }

    @Override
    public void saveOrUpdateFormQuestions(List<FormQuestionDTO> formQuestionDTOS) throws SQLException {
        for (FormQuestionDTO formQuestionDTO: formQuestionDTOS) {
            this.saveOrUpdateFormQuestion(formQuestionDTO);
        }
    }
    @Override
    public FormQuestion saveOrUpdateFormQuestion(FormQuestionDTO formQuestionDTO) throws SQLException {
        QuestionCategoryDTO questionCategoryDTO = formQuestionDTO.getQuestion().getQuestionCategory();
        QuestionsCategory qc = this.questionsCategoryDAO.getByUuid(questionCategoryDTO.getUuid());
        QuestionsCategory questionsCategory = questionCategoryDTO.getQuestionCategory();
        if(qc!=null) {
            questionsCategory.setId(qc.getId());
        }
        this.questionsCategoryDAO.createOrUpdate(questionsCategory);

        QuestionDTO questionDTO = formQuestionDTO.getQuestion();
        Question q = this.questionDAO.getByUuid(questionDTO.getUuid());
        Question question = questionDTO.getQuestionObj();
        if(q!=null) {
            question.setId(q.getId());
        }
        this.questionDAO.createOrUpdate(question);

        FormQuestion fq = this.formQuestionDAO.getByUuid(formQuestionDTO.getUuid());
        FormQuestion formQuestion = formQuestionDTO.getFormQuestion();
        if(fq!=null) {
            formQuestion.setId(fq.getId());
        }
        this.formQuestionDAO.createOrUpdate(formQuestion);
        return formQuestion;
    }
}
