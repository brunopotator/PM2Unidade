package br.edu.ifba.mobile.meteoralogisticacliente.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteoralogisticacliente.bd.FachadaBD;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Fornecedor;


/**
 * Created by Bruno on 20/06/16.
 */
public class RemocaoFornecedor extends AsyncTask<Void, Void, String> {
    private Fornecedor fornecedor = null;
    private Context context = null;
    public RemocaoFornecedor(Context contexto, Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        this.context = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(fornecedor.getCodigo() != -1) {
            if(FachadaBD.getInstance().removerFor(fornecedor) == 0) {
                mensagem = "Problemas na remoção!";
            } else {
                mensagem = "Fornecedor foi removido";
            }
        } else {
            mensagem = "Selecione um Fornecedor!";
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
