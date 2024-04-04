package mz.org.csaude.mentoring.view.session;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import mz.org.csaude.mentoring.R;
import mz.org.csaude.mentoring.base.activity.BaseActivity;
import mz.org.csaude.mentoring.base.viewModel.BaseViewModel;
import mz.org.csaude.mentoring.databinding.ActivitySessionClosureBinding;
import mz.org.csaude.mentoring.util.Utilities;
import mz.org.csaude.mentoring.viewmodel.ronda.RondaVM;
import mz.org.csaude.mentoring.viewmodel.session.SessionClosureVM;
import mz.org.csaude.mentoring.viewmodel.tutored.TutoredVM;

import androidx.activity.EdgeToEdge;
import androidx.lifecycle.ViewModelProvider;

public class SessionClosureActivity extends BaseActivity {

    private @NonNull ActivitySessionClosureBinding binding;


    @Override
    public BaseViewModel initViewModel() {
        return new ViewModelProvider(this).get(SessionClosureVM.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_session_closure_summary);
        binding = ActivitySessionClosureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.pontosFortesLyt.setVisibility(View.GONE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // título da barra de ferramentas
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(R.string.session_closure);

        // Configurando o botão de voltar
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        Bar
    }

    @Override
    public SessionClosureVM getRelatedViewModel() {
        return (SessionClosureVM) super.getRelatedViewModel();
    }

    private void switchLayout(){
        getRelatedViewModel().setInitialDataVisible(!getRelatedViewModel().isInitialDataVisible());
    }
    public void changeFormSectionVisibility(View view){
        if(view.equals(binding.pontosFortes)){
            if(binding.pontosFortesLyt.getVisibility() == View.VISIBLE){
                binding.btnCollapsePontosFortes.setImageResource(R.drawable.sharp_arrow_drop_up_24);
                switchLayout();
                Utilities.collapse(binding.pontosFortesLyt);
            } else {
                switchLayout();
                Utilities.expand(binding.pontosFortesLyt);
                binding.btnCollapsePontosFortes.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }

        } else if(view.equals(binding.aspectosMelhorar)){
            if(binding.aspectosPorMelhorarLyt.getVisibility() == View.VISIBLE){
                binding.btnCollapseAspectosMelhorar.setImageResource(R.drawable.sharp_arrow_drop_up_24);
                switchLayout();
                Utilities.collapse(binding.aspectosPorMelhorarLyt);
            } else {
                switchLayout();
                Utilities.expand(binding.aspectosPorMelhorarLyt);
                binding.btnCollapseAspectosMelhorar.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }
        }  else if(view.equals(binding.planoMelhoria)){
            if(binding.planoMelhorariaLyt.getVisibility() == View.VISIBLE){
                switchLayout();
                Utilities.collapse(binding.planoMelhorariaLyt);
                binding.btnCollapsePlanoTrabalho.setImageResource(R.drawable.sharp_arrow_drop_up_24);
            } else {
                switchLayout();
                Utilities.expand(binding.planoMelhorariaLyt);
                binding.btnCollapsePlanoTrabalho.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }
        } else if(view.equals(binding.observacoes)){
            if(binding.observacoesLyt.getVisibility() == View.VISIBLE){
                switchLayout();
                Utilities.collapse(binding.observacoesLyt);
                binding.btnObservacoes.setImageResource(R.drawable.sharp_arrow_drop_up_24);
            } else {
                switchLayout();
                Utilities.expand(binding.observacoesLyt);
                binding.btnObservacoes.setImageResource(R.drawable.baseline_arrow_drop_down_24);
            }
        }

    }
}
