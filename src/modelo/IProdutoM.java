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
public interface IProdutoM {
    
    public int getCodigoProduto();

    public void setCodigoProduto(int codigoProduto);

    public String getNome();

    public void setNome(String nome);

    public String getMarca();

    public void setMarca(String marca);

    public double getPreco();

    public void setPreco(double preco);
    
    public double getQnt_estoque();

    public void setQnt_estoque(double qnt_estoque);

    public boolean getHaVendas();

    public void setHaVendas(boolean haVendas);

    public boolean getListavel();

    public void setListavel(boolean listavel);
}
