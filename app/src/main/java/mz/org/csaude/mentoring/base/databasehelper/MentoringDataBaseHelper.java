package mz.org.csaude.mentoring.base.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.dao.answer.AnswerDAO;
import mz.org.csaude.mentoring.dao.career.CareerDAO;
import mz.org.csaude.mentoring.dao.form.FormDAO;
import mz.org.csaude.mentoring.dao.form.FormTargetDAO;
import mz.org.csaude.mentoring.dao.formQuestion.FormQuestionDAO;
import mz.org.csaude.mentoring.dao.indicator.IndicatorDAO;
import mz.org.csaude.mentoring.dao.location.CabinetDAO;
import mz.org.csaude.mentoring.dao.location.DistrictDAO;
import mz.org.csaude.mentoring.dao.location.HealthFacilityDAO;
import mz.org.csaude.mentoring.dao.mentorship.MentorshipDAO;
import mz.org.csaude.mentoring.dao.partner.PartnerDao;
import mz.org.csaude.mentoring.dao.programmaticArea.ProgrammaticAreaDAO;
import mz.org.csaude.mentoring.dao.programmaticArea.TutorProgrammaticAreaDAO;
import mz.org.csaude.mentoring.dao.question.QuestionDAO;
import mz.org.csaude.mentoring.dao.question.QuestionsCategoryDAO;
import mz.org.csaude.mentoring.dao.session.SessionDAO;
import mz.org.csaude.mentoring.dao.setting.SettingDAO;
import mz.org.csaude.mentoring.dao.tutor.TutorDAO;
import mz.org.csaude.mentoring.dao.tutor.TutorLocationDAO;
import mz.org.csaude.mentoring.dao.tutored.TutoredDao;
import mz.org.csaude.mentoring.model.Question.Question;
import mz.org.csaude.mentoring.model.Question.QuestionsCategory;
import mz.org.csaude.mentoring.model.answer.Answer;
import mz.org.csaude.mentoring.model.career.Career;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.form.FormTarget;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.model.indicator.Indicator;
import mz.org.csaude.mentoring.model.location.Cabinet;
import mz.org.csaude.mentoring.model.location.District;
import mz.org.csaude.mentoring.model.location.HealthFacility;
import mz.org.csaude.mentoring.model.mentorship.Mentorship;
import mz.org.csaude.mentoring.model.partner.Partner;
import mz.org.csaude.mentoring.model.programmaticArea.ProgrammaticArea;
import mz.org.csaude.mentoring.model.programmaticArea.TutorProgrammaticArea;
import mz.org.csaude.mentoring.model.session.Session;
import mz.org.csaude.mentoring.model.setting.Setting;
import mz.org.csaude.mentoring.model.tutor.Tutor;
import mz.org.csaude.mentoring.model.tutor.TutorLocation;
import mz.org.csaude.mentoring.model.tutored.Tutored;

public class MentoringDataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "mentoring.db";
    private static final int    DATABASE_VERSION = 1;

    private final Context context;

    private TutoredDao tutoredDao;

    private PartnerDao partnerDao;

    private CareerDAO careerDAO;

    private TutorDAO tutorDAO;

    private HealthFacilityDAO healthFacilityDAO;

    private TutorLocationDAO tutorLocationDAO;

    private ProgrammaticAreaDAO programmaticAreaDAO;

    private FormDAO formDAO;

    private FormTargetDAO formTargetDAO;

    private SessionDAO sessionDAO;

    private CabinetDAO cabinetDAO;

    private DistrictDAO districtDAO;

    private MentorshipDAO mentorshipDAO;

    private QuestionDAO questionDAO;

    private QuestionsCategoryDAO questionsCategoryDAO;

    private IndicatorDAO indicatorDAO;

    private AnswerDAO answerDAO;

    private FormQuestionDAO formQuestionDAO;

    private SettingDAO settingDAO;

    private TutorProgrammaticAreaDAO tutorProgrammaticAreaDAO;

    private static MentoringDataBaseHelper dataBaseHelper;


    private MentoringDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        this.context = context;
    }

    public static MentoringDataBaseHelper getInstance(Context context) {
        if (dataBaseHelper == null){
            dataBaseHelper = new MentoringDataBaseHelper(context);
        }
        return dataBaseHelper;
    }

    public TutoredDao getTutoredDao() throws SQLException {
        if(tutoredDao == null){
            tutoredDao = getDao(Tutored.class);
        }
        return tutoredDao;
    }

    public PartnerDao getPartnerDao() throws SQLException{
        if (partnerDao == null){
            partnerDao = getDao(Partner.class);
        }
        return partnerDao;
    }
    public CareerDAO getCareerDAO() throws SQLException {
        if (careerDAO == null){
            careerDAO = getDao(Career.class);
        }
        return careerDAO;
    }

    public TutorDAO getTutorDAO() throws SQLException {
        if(tutorDAO == null){
            tutorDAO = getDao(Tutor.class);
        }
        return tutorDAO;
    }

    public TutorLocationDAO getTutorLocationDAO() throws SQLException {
        if(tutorLocationDAO == null){
            tutorLocationDAO = getDao(TutorLocation.class);
        }
        return tutorLocationDAO;
    }

    public ProgrammaticAreaDAO getProgrammaticAreaDAO() throws SQLException {
        if(programmaticAreaDAO == null){
            programmaticAreaDAO = getDao(ProgrammaticArea.class);
        }
        return programmaticAreaDAO;
    }

    public FormDAO getFormDAO() throws SQLException {
        if(formDAO == null){
            formDAO = getDao(Form.class);
        }
        return formDAO;
    }
    public FormTargetDAO getFormTargetDAO() throws SQLException {
        if(formTargetDAO == null){
            formTargetDAO = getDao(FormTarget.class);
        }
        return formTargetDAO;
    }

    public SessionDAO getSessionDAO() throws SQLException {
        if(sessionDAO == null){
            sessionDAO = getDao(Session.class);
        }
        return sessionDAO;
    }

    public HealthFacilityDAO getHealthFacilityDAO() throws SQLException {
        if(healthFacilityDAO == null){
            healthFacilityDAO = getDao(HealthFacility.class);
        }
        return healthFacilityDAO;
    }

    public CabinetDAO getCabinetDAO() throws SQLException {
        if(cabinetDAO == null){
            cabinetDAO = getDao(Cabinet.class);
        }
        return cabinetDAO;
    }
    public DistrictDAO getDistrictDAO() throws SQLException {
        if(districtDAO == null){
            districtDAO = getDao(District.class);
        }
        return districtDAO;
    }

    public MentorshipDAO getMentorshipDAO() throws SQLException {
        if(mentorshipDAO == null){
            mentorshipDAO = getDao(Mentorship.class);
        }
        return mentorshipDAO;
    }
    public QuestionDAO getQuestionDAO() throws SQLException {
        if(questionDAO == null){
            questionDAO = getDao(Question.class);
        }
        return questionDAO;
    }

    public QuestionsCategoryDAO getQuestionsCategoryDAO() throws SQLException {
        if(questionsCategoryDAO == null){
            questionsCategoryDAO = getDao(QuestionsCategory.class);
        }
        return questionsCategoryDAO;
    }
    public IndicatorDAO getIndicatorDAO() throws SQLException {
        if(indicatorDAO == null){
            indicatorDAO = getDao(Indicator.class);
        }
        return indicatorDAO;
    }
    public AnswerDAO getAnswerDAO() throws SQLException {
        if(answerDAO == null){
            answerDAO = getDao(Answer.class);
        }
        return answerDAO;
    }
    public FormQuestionDAO getFormQuestionDAO() throws SQLException {
        if(formQuestionDAO == null){
            formQuestionDAO = getDao(FormQuestion.class);
        }
        return formQuestionDAO;
    }
    public SettingDAO getSettingDAO() throws SQLException {
        if(settingDAO == null){
            settingDAO = getDao(Setting.class);
        }
        return settingDAO;
    }
    public TutorProgrammaticAreaDAO getTutorProgrammaticAreaDAO() throws SQLException {
        if(tutorProgrammaticAreaDAO == null){
            tutorProgrammaticAreaDAO = getDao(TutorProgrammaticArea.class);
        }
        return tutorProgrammaticAreaDAO;
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTableIfNotExists(connectionSource, Tutored.class);
            TableUtils.createTableIfNotExists(connectionSource, Partner.class);
            TableUtils.createTableIfNotExists(connectionSource, Career.class);
            TableUtils.createTableIfNotExists(connectionSource, Tutor.class);
            TableUtils.createTableIfNotExists(connectionSource, TutorLocation.class);
            TableUtils.createTableIfNotExists(connectionSource, Form.class);
            TableUtils.createTableIfNotExists(connectionSource, FormTarget.class);
            TableUtils.createTableIfNotExists(connectionSource, Session.class);
            TableUtils.createTableIfNotExists(connectionSource, HealthFacility.class);
            TableUtils.createTableIfNotExists(connectionSource, Cabinet.class);
            TableUtils.createTableIfNotExists(connectionSource, District.class);
            TableUtils.createTableIfNotExists(connectionSource, Mentorship.class);
            TableUtils.createTableIfNotExists(connectionSource, Question.class);
            TableUtils.createTableIfNotExists(connectionSource, QuestionsCategory.class);
            TableUtils.createTableIfNotExists(connectionSource, Indicator.class);
            TableUtils.createTableIfNotExists(connectionSource, Answer.class);
            TableUtils.createTableIfNotExists(connectionSource, FormQuestion.class);
            TableUtils.createTableIfNotExists(connectionSource, Setting.class);
            TableUtils.createTableIfNotExists(connectionSource, TutorProgrammaticArea.class);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
