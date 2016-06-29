package br.edu.ifba.mobile.meteoralogisticacliente.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import br.edu.ifba.mobile.meteoralogisticacliente.bd.FachadaBD;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Produto;


/**
 * Created by Bruno on 20/06/16.
 */
public class ListagemProdutos extends AsyncTask<Void, Void, List<Produto>> {
    private Context contexto = null;
    private ListView listaProdutos = null;

    public ListagemProdutos(Context contexto, ListView listaProdutos) {
        this.contexto = contexto;
        this.listaProdutos = listaProdutos;
    }

    @Override
    protected List doInBackground(Void... params) {
        List<Produto> produtos = FachadaBD.getInstance().listarProdutos();
        return produtos;
    }

    /*
    em background nao executa recursos graficos
    então so depois da execucacao do AsyncTest que
    pode ser exbido usando o metodo onPostExecute()
     */
    @Override
    protected void onPostExecute(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            Toast.makeText(contexto, "Nenhum Produto Encontrado", Toast.LENGTH_LONG).show();
        } else {
            ArrayAdapter<Produto> adaptador = new ArrayAdapter<Produto>(
                    contexto,
                    android.R.layout.simple_list_item_single_choice,
                    produtos
            );
            listaProdutos.setAdapter(adaptador);
        }
    }
}
