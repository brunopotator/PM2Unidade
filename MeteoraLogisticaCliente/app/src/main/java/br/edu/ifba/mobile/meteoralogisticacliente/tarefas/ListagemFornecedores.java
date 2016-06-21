package br.edu.ifba.mobile.meteoralogisticacliente.tarefas;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



import java.util.List;

import br.edu.ifba.mobile.meteoralogisticacliente.bd.FachadaBD;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Fornecedor;


/**
 * Created by Bruno on 20/06/16.
 */
public class ListagemFornecedores extends AsyncTask<Void, Void, List<Fornecedor>> {
    private Context contexto = null;
    private ListView listaFornecedores = null;

    public ListagemFornecedores(Context contexto, ListView listaFornecedores) {
        this.contexto = contexto;
        this.listaFornecedores = listaFornecedores;
    }

    @Override
    protected List doInBackground(Void... params) {
        List<Fornecedor> fornecedores = FachadaBD.getInstance().listarFornecedores();
        return fornecedores;
    }

    /*
    em background nao executa recursos graficos
    então so depois da execucacao do AsyncTest que
    pode ser exbido usando o metodo onPostExecute()
     */
    @Override
    protected void onPostExecute(List<Fornecedor> fornecedores) {
        if (fornecedores.isEmpty()) {
            Toast.makeText(contexto, "Nenhum Fornecedor Encontrado", Toast.LENGTH_LONG).show();
        } else {
            ArrayAdapter<Fornecedor> adaptador = new ArrayAdapter<Fornecedor>(
                    contexto,
                    android.R.layout.simple_list_item_single_choice,
                    fornecedores
            );
            listaFornecedores.setAdapter(adaptador);
        }
    }
}
