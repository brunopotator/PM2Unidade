package br.edu.ifba.mobile.meteoralogisticacliente.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.edu.ifba.mobile.meteoralogisticacliente.bd.FachadaBD;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Produto;


/**
 * Created by Bruno on 20/06/16.
 */
public class GravacaoProduto extends AsyncTask<Void, Void, String> {
    private Produto produto = null;
    private Context context = null;

    public GravacaoProduto(Context contexto, Produto produto) {
        this.produto = produto;
        this.context = contexto;
    }

    @Override
    protected String doInBackground(Void... params) {
        String mensagem = "";
        long codigo = -1;
        if(produto.getCodigo() == -1) {
            codigo = FachadaBD.getInstance().inserirProd(produto);
        } else {
            codigo = FachadaBD.getInstance().atualizarProd(produto);
        }

        if (codigo > 0) {
            mensagem = "Produto gravado com sucesso!";
        } else {
            mensagem = "Erro ao gravar o produto!";
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
