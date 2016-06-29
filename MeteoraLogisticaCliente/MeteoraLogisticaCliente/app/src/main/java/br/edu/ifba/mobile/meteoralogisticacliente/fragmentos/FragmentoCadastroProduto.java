package br.edu.ifba.mobile.meteoralogisticacliente.fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifba.mobile.meteoralogisticacliente.R;
import br.edu.ifba.mobile.meteoralogisticacliente.bd.Produto;
import br.edu.ifba.mobile.meteoralogisticacliente.tarefas.GravacaoProduto;


/**
 * Created by Bruno on 20/06/16.
 */
public class FragmentoCadastroProduto extends Fragment {

    private static FragmentoCadastroProduto instancia = null;

    public static FragmentoCadastroProduto getInstancia() {
        if (instancia == null) {
            instancia = new FragmentoCadastroProduto();
        }
        return instancia;
    }

    private View tela = null;
    private EditText nomeProduto = null;
    private EditText quantidade = null;
    private EditText preco = null;
    private Button botaoGravar = null;
    private Produto produto = null;

    @Override
    public View onCreateView(LayoutInflater inflador, ViewGroup vgroup, Bundle bundle) {
        tela = inflador.inflate(R.layout.fragmento_cadastro_produto, vgroup, false);

        preparar();

        return tela;
    }

    private void preparar() {
        nomeProduto = (EditText) tela.findViewById(R.id.nome);
        quantidade = (EditText) tela.findViewById(R.id.quantidade);
        preco = (EditText) tela.findViewById(R.id.preco);
        botaoGravar = (Button) tela.findViewById(R.id.botaoGravar);

        botaoGravar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GravacaoProduto gravacao = new GravacaoProduto(getContexto(), getProduto());
                        gravacao.execute();
                    }
                }
        );
    }

    private Context getContexto() {
        return this.getContext();
    }

    private Produto getProduto() {
        produto.setNome(nomeProduto.getText().toString());
        produto.setQuantidade(Integer.valueOf(quantidade.getText().toString()));
        produto.setPreco(Double.valueOf(preco.getText().toString()));

        return produto;
    }

    public void exibirProdutoSelecionado() {
        produto = FragmentoListaProdutos.getInstancia().getProdutoSelecionado();
        if (produto.getCodigo() == -1) {
            limparCampos();
        } else {
            carregarCampos();
        }
    }

    private void limparCampos() {
        nomeProduto.setText("");
        quantidade.setText("0");
        preco.setText("0.00");
    }

    private void carregarCampos() {
        nomeProduto.setText(produto.getNome());
        quantidade.setText(produto.getQuantidade()+"");
        preco.setText(produto.getPreco()+"");
    }
}