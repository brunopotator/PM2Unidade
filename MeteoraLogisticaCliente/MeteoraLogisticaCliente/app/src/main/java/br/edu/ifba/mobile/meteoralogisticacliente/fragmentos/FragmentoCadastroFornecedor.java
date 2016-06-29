package br.edu.ifba.mobile.meteoralogisticacliente.fragmentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.meteoralogisticacliente.R;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Fornecedor;
import br.edu.ifba.mobile.meteoralogisticacliente.tarefas.GravacaoFornecedor;


/**
 * Created by Bruno on 20/06/16.
 */
public class FragmentoCadastroFornecedor extends Fragment {

    private static FragmentoCadastroFornecedor instancia = null;

    public static FragmentoCadastroFornecedor getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoCadastroFornecedor();
        }
        return instancia;
    }

    private View tela = null;
    private EditText nomeFornecedor = null;
    private EditText email = null;
    private EditText telefone = null;
    private Button botaoGravar = null;
    private Fornecedor fornecedor= null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_fornecedor, vgroup, false);

        preparar();

        return tela;
    }

    private void preparar() {
        nomeFornecedor = (EditText) tela.findViewById(R.id.nomeFornecedor);
        email = (EditText) tela.findViewById(R.id.email);
        telefone = (EditText) tela.findViewById(R.id.telefone);
        botaoGravar = (Button) tela.findViewById(R.id.botaoGravar);

        botaoGravar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GravacaoFornecedor gravacao = new GravacaoFornecedor(getContexto(), getFornecedor());
                        gravacao.execute();
                    }
                }
        );
    }

    private Context getContexto() {
        return this.getContext();
    }

    private Fornecedor getFornecedor() {
        fornecedor.setNome(nomeFornecedor.getText().toString());
        fornecedor.setEmail(email.getText().toString());
        fornecedor.setTelefone(telefone.getText().toString());

        return fornecedor;
    }

    public void exibirFornecedorSelecionado() {
        fornecedor = FragmentoListaFornecedores.getInstancia().getFornecedorSelecionado();
        if (fornecedor.getCodigo() == -1) {
            limparCampos();
        } else {
            carregarCampos();
        }
    }

    private void limparCampos() {
        nomeFornecedor.setText("");
        email.setText("");
        telefone.setText("");
    }

    private void carregarCampos() {
        nomeFornecedor.setText(fornecedor.getNome());
        email.setText(fornecedor.getEmail());
        telefone.setText(fornecedor.getTelefone());
    }
}