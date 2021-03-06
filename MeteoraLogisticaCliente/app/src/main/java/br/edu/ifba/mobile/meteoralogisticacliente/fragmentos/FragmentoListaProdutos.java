package br.edu.ifba.mobile.meteoralogisticacliente.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.ifba.mobile.meteoralogisticacliente.R;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Produto;
import br.edu.ifba.mobile.meteoralogisticacliente.tarefas.ListagemProdutos;
import br.edu.ifba.mobile.meteoralogisticacliente.tarefas.RemocaoProduto;


/**
 * Created by Bruno on 20/06/16.
 */
public class FragmentoListaProdutos extends Fragment {
    private static FragmentoListaProdutos instancia = null;

    public static FragmentoListaProdutos getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoListaProdutos();
        }
        return instancia;
    }

    private View tela = null;
    private ListView lista = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgrupo, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_lista_produtos, vgrupo, false);
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
                RemocaoProduto remocaoProduto = new RemocaoProduto(this.getContext(), this.getProdutoSelecionado());
                remocaoProduto.execute();
                atualizar();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void preparar() {
        lista = (ListView) tela.findViewById(R.id.listaProdutos);
        this.setHasOptionsMenu(true);
        lista.setClickable(true);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void atualizar() {
        ListagemProdutos listagem = new ListagemProdutos(this.getContext(), lista);
        listagem.execute();
    }

    public Produto getProdutoSelecionado() {
        Produto produto = new Produto();
        int posicao = lista.getCheckedItemPosition();
        if (posicao != ListView.INVALID_POSITION) {
            produto = (Produto) lista.getItemAtPosition(posicao);
        }
        return produto;
    }
}
