package br.edu.ifba.mobile.meteoralogisticacliente.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bruno on 20/06/16.
 */

public class FachadaBD extends SQLiteOpenHelper {

    private static FachadaBD instancia = null;

    private static String NOME_BANCO = "MeteoroClienteBD";
    private static int VERSAO_BANCO = 1;

    public static FachadaBD criarInstancia(Context context) {
        if (instancia == null) {
            instancia = new FachadaBD(context);
        }
        return instancia;
    }

    public static FachadaBD getInstance() {
        return instancia;
    }

    public FachadaBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    private static String COMANDO_CRIACAO_TABELA_FORNECEDOR =
            "CREATE TABLE FORNECEDORES(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, EMAIL TEXT, TELEFONE TEXT)";

    private static String COMANDO_CRIACAO_TABELA_PRODUTOS =
            "CREATE TABLE PRODUTOS(" +
                    "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, QUANTIDADE INTEGER, PRECO REAL)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMANDO_CRIACAO_TABELA_FORNECEDOR);
        db.execSQL(COMANDO_CRIACAO_TABELA_PRODUTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        // TODO Auto-generated method stub
    }

    // metodos de criacao de um CRUD utilizando o SQLite

    // inserir fornecedor
    public long inserirFor(Fornecedor fornecedor) {
        SQLiteDatabase db = this.getWritableDatabase(); // escreve
        ContentValues valores = new ContentValues();

        valores.put("NOME", fornecedor.getNome());
        valores.put("EMAIL", fornecedor.getEmail());
        valores.put("TELEFONE", fornecedor.getTelefone());

        long codigo = db.insert("FORNECEDORES", null, valores);
        return codigo;
    }

    // inserir produto
    public long inserirProd(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase(); // escreve
        ContentValues valores = new ContentValues();

        valores.put("NOME", produto.getNome());
        valores.put("QUANTIDADE", produto.getQuantidade());
        valores.put("PRECO", produto.getPreco());

        long codigo = db.insert("PRODUTOS", null, valores);
        return codigo;
    }

    // atualizar fornecedor
    public long atualizarFor(Fornecedor fornecedor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", fornecedor.getNome());
        valores.put("EMAIL", fornecedor.getEmail());
        valores.put("TELEFONE", fornecedor.getTelefone());

        long codigo = db.update("FORNECEDORES", valores, "CODIGO = " + fornecedor.getCodigo(), null);
        return codigo;
    }

    //atualizar produto
    public long atualizarProd(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("NOME", produto.getNome());
        valores.put("QUANTIDADE", produto.getQuantidade());
        valores.put("PRECO", produto.getPreco());

        long codigo = db.update("PRODUTOS", valores, "CODIGO = " + produto.getCodigo(), null);
        return codigo;
    }

    //remover Fornecedor
    public int removerFor(Fornecedor fornecedor) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("FORNECEDORES", "CODIGO = " + fornecedor.getCodigo(), null);
    }

    //remover produto
    public int removerProd(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PRODUTOS", "CODIGO = " + produto.getCodigo(), null);
    }

    //listar forecedores
    public List<Fornecedor> listarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, EMAIL, TELEFONE FROM FORNECEDORES";

        Cursor cursor = db.rawQuery(selecao, null); // executa o comando sql
        if (cursor != null) {
            boolean temProximo = cursor.moveToFirst(); // mover o cursor para o primeiro registro
            while (temProximo) {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                fornecedor.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                fornecedor.setEmail(cursor.getString(cursor.getColumnIndex("EMAIL")));
                fornecedor.setTelefone(cursor.getString(cursor.getColumnIndex("TELEFONE")));

                fornecedores.add(fornecedor);
                temProximo = cursor.moveToNext();
            }
        }
        return fornecedores;
    }

    //listar produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<Produto>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selecao = "SELECT CODIGO, NOME, QUANTIDADE, PRECO FROM PRODUTOS";

        Cursor cursor = db.rawQuery(selecao, null); // executa o comando sql
        if (cursor != null) {
            boolean temProximo = cursor.moveToFirst(); // mover o cursor para o primeiro registro
            while (temProximo) {

                Produto produto = new Produto();
                produto.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                produto.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
                produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("QUANTIDADE")));
                produto.setPreco(cursor.getDouble(cursor.getColumnIndex("PRECO")));

                produtos.add(produto);
                temProximo = cursor.moveToNext();
            }
        }
        return produtos;
    }
}