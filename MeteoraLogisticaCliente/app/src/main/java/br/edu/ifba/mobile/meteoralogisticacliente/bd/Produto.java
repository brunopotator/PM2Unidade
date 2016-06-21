package br.edu.ifba.mobile.meteoralogisticacliente.bd;

/**
 * Created by Bruno on 20/06/16.
 */
public class Produto {
    private long codigo = -1;
    private String nome;
    private int quantidade;
    private Double preco;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto: " + nome + "\n" +
                "Quantidade: " + quantidade + "\n" + "Preço: " + preco;
    }
}
