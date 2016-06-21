package br.edu.ifba.mobile.meteoralogisticacliente.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteoralogisticacliente.bd.FachadaBD;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Produto;


/**
 * Created by Bruno on 20/06/16.
 */
public class RemocaoProduto extends AsyncTask<Void, Void, String> {
    private Produto produto = null;
    private Context context = null;
    public RemocaoProduto(Context contexto, Produto produto) {
        this.produto = produto;
        this.context = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";

        if(produto.getCodigo() != -1) {
            if(FachadaBD.getInstance().removerProd(produto) == 0) {
                mensagem = "Problemas na remoção!";
            } else {
                mensagem = "Produto foi removido";
            }
        } else {
            mensagem = "Selecione um Produto!";
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
