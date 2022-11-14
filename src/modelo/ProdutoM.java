/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jhonn
 */
public class ProdutoM implements IProdutoM{
    
    // Atributos
    private int codigoProduto;
    private String nome;
    private String marca;
    private double preco;
    private double qnt_estoque;
    private boolean haVendas;
    private boolean listavel;

    // cara garantir q dois produtos não tenho o mesmo codigo
    private static int codigoAux = 1;
    
    // Metodos especiais
    
    // contrutor de copia
    private ProdutoM(ProdutoM original) {
        this.codigoProduto = (original.getCodigoProduto());
        this.nome = (original.getNome());
        this.marca = (original.getMarca());
        this.preco = (original.getPreco());
        this.qnt_estoque = (original.getQnt_estoque());
        this.haVendas = (original.getHaVendas());
        this.listavel = (original.getListavel());
    }

    private ProdutoM(String nome, String marca, double preco, double qnt_estoque) {
        this();
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.qnt_estoque = qnt_estoque;
    }
    
    public ProdutoM() {
        codigoProduto = codigoAux;
        codigoAux++;
        haVendas = false;
        listavel = true;
    }
    
    // Fabrica de copia
    public static ProdutoM getInstance(ProdutoM original) {
        if (original != null) {
            return new ProdutoM(original);
        } else {
            return null;
        }
    }

    public static ProdutoM getInstance(String nome, String marca, double preco, double qnt_estoque) {
        if ((nome != null) && (marca != null) && (preco > 0) && (qnt_estoque > 0)) {
            return new ProdutoM(nome, marca, preco, qnt_estoque);
        } else {
            return null;
        }
    }
    
    public static ProdutoM getInstance() {
        return new ProdutoM();
    }
    
    @Override
    public int getCodigoProduto() {
        return codigoProduto;
    }

    @Override
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getMarca() {
        return marca;
    }

    @Override
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public double getQnt_estoque() {
        return qnt_estoque;
    }

    @Override
    public void setQnt_estoque(double qnt_estoque) {
        this.qnt_estoque = qnt_estoque;
    }

    @Override
    public boolean getHaVendas() {
        return haVendas;
    }

    @Override
    public void setHaVendas(boolean haVendas) {
        this.haVendas = haVendas;
    }

    @Override
    public boolean getListavel() {
        return listavel;
    }

    @Override
    public void setListavel(boolean listavel) {
        this.listavel = listavel;
    }

    public static void setCodigoAux(int codigoAux) {
        ProdutoM.codigoAux = codigoAux;
    }

    public static int getCodigoAux() {
        return codigoAux;
    }
}
