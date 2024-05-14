package mz.org.csaude.mentoring.workSchedule.rest;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mz.org.csaude.mentoring.base.service.BaseRestService;
import mz.org.csaude.mentoring.dto.form.FormQuestionDTO;
import mz.org.csaude.mentoring.listner.rest.RestResponseListener;
import mz.org.csaude.mentoring.model.form.Form;
import mz.org.csaude.mentoring.model.formQuestion.FormQuestion;
import mz.org.csaude.mentoring.service.form.FormService;
import mz.org.csaude.mentoring.service.form.FormServiceImpl;
import mz.org.csaude.mentoring.service.formQuestion.FormQuestionService;
import mz.org.csaude.mentoring.service.formQuestion.FormQuestionServiceImpl;
import mz.org.csaude.mentoring.service.metadata.LoadMetadataServiceImpl;
import mz.org.csaude.mentoring.util.SyncSatus;
import mz.org.csaude.mentoring.util.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormQuestionRestService extends BaseRestService {

    public FormQuestionRestService(Application application) {
        super(application);
    }

    public void restGetFormQuestion(RestResponseListener<FormQuestion> listener){
        List<String> formsUuids = new ArrayList<>();
        try {
            FormService formService = new FormServiceImpl(LoadMetadataServiceImpl.APP);
            List<Form> forms = formService.getAllSynced(getApplication());
            for (Form form: forms) {
                formsUuids.add(form.getUuid());
            }
        } catch (SQLException e) {
            Log.e("Error while loading synced forms Uuids -- ", e.getMessage(), e);
        }
        Call<List<FormQuestionDTO>> formQuestionsCall = syncDataService.getFormsQuestionsByFormsUuids(formsUuids);

        formQuestionsCall.enqueue(new Callback<List<FormQuestionDTO>>() {
            @Override
            public void onResponse(Call<List<FormQuestionDTO>> call, Response<List<FormQuestionDTO>> response) {
                List<FormQuestionDTO> data = response.body();
                if (Utilities.listHasElements(data)) {
                    try {
                        FormQuestionService formQuestionService = new FormQuestionServiceImpl(LoadMetadataServiceImpl.APP);

                        List<FormQuestion> formQuestions = new ArrayList<>();

                        for (FormQuestionDTO formQuestionDTO: data) {
                            formQuestionDTO.setSyncSatus(SyncSatus.SENT);
                            formQuestionDTO.getFormQuestion().setSyncStatus(SyncSatus.SENT);
                            formQuestions.add(formQuestionDTO.getFormQuestion());
                        }

                        Toast.makeText(APP.getApplicationContext(), "Carregando as Competências das Tabelas.", Toast.LENGTH_SHORT).show();
                        formQuestionService.saveOrUpdateFormQuestions(data);

                        listener.doOnResponse(BaseRestService.REQUEST_SUCESS, formQuestions);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    Toast.makeText(APP.getApplicationContext(), "COMPETÊNCIAS DAS TABELAS CARREGADAS COM SUCESSO", Toast.LENGTH_SHORT).show();
                } else {
                    listener.doOnResponse(BaseRestService.REQUEST_NO_DATA, null);
                }
            }

            @Override
            public void onFailure(Call<List<FormQuestionDTO>> call, Throwable t) {
                Toast.makeText(APP.getApplicationContext(), "Não foi possivel carregar as Competências das Tabelas. Tente mais tarde....", Toast.LENGTH_SHORT).show();
                Log.i("METADATA LOAD --", t.getMessage(), t);
            }
        });
    }
}
