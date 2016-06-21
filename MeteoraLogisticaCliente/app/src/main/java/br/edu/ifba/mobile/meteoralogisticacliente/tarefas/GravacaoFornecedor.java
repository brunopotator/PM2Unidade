package br.edu.ifba.mobile.meteoralogisticacliente.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteoralogisticacliente.bd.FachadaBD;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Fornecedor;


/**
 * Created by Bruno on 20/06/16.
 */
public class GravacaoFornecedor extends AsyncTask<Void, Void, String> {
    private Fornecedor fornecedor = null;
    private Context context = null;

    public GravacaoFornecedor(Context contexto, Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        this.context = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo = -1;
        if(fornecedor.getCodigo() == -1) {
            codigo = FachadaBD.getInstance().inserirFor(fornecedor);
        } else {
            codigo = FachadaBD.getInstance().atualizarFor(fornecedor);
        }

        if (codigo > 0) {
            mensagem = "Fornecedor gravado com sucesso!";
        } else {
            mensagem = "Erro ao gravar o Fornecedor!";
        }
        return mensagem;
    }

    /*
    em background nao executa recursos graficos
    então so depois da execucacao do AsyncTest que
    pode ser exbido usando o metodo onPostExecute()
     */
    @Override
    protected void onPostExecute(String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }
}
