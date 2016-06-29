package br.edu.ifba.mobile.meteoralogisticacliente.fragmentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.meteoralogisticacliente.R;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Fornecedor;
import br.edu.ifba.mobile.meteoralogisticacliente.tarefas.ListagemFornecedores;
import br.edu.ifba.mobile.meteoralogisticacliente.tarefas.RemocaoFornecedor;


/**
 * Created by Bruno on 20/06/16.
 */
public class FragmentoListaFornecedores extends Fragment {
    private static FragmentoListaFornecedores instancia = null;

    public static FragmentoListaFornecedores getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoListaFornecedores();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_lista_fornecedores, vgrupo, false);
        preparar();
        return tela;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflador) {
        super.onCreateOptionsMenu(menu, inflador);
        inflador.inflate(R.menu.menu_meteora_logistica_cliente, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long id = item.getItemId();
        if (id != AdapterView.INVALID_ROW_ID) {
            if (id == R.id.cadastro_remover) {
                RemocaoFornecedor remocaoFornecedor = new RemocaoFornecedor(this.getContext(), this.getFornecedorSelecionado());
                remocaoFornecedor.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.listaFornecedores);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void atualizar() {
        ListagemFornecedores listagem = new ListagemFornecedores(this.getContext(), lista);
        listagem.execute();
    }

    public Fornecedor getFornecedorSelecionado() {
        Fornecedor fornecedor = new Fornecedor();
        int posicao = lista.getCheckedItemPosition();
        if (posicao != ListView.INVALID_POSITION) {
            fornecedor = (Fornecedor) lista.getItemAtPosition(posicao);
        }
        return fornecedor;
    }
}
