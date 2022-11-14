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
public interface IItemM {
    public ProdutoM getModelProduto();
    
    public void setModelProduto(ProdutoM produto);

    public double getPrecoItem();
    
    public double getQuantidade();

    public void setQuantidade(double quantidadeTotal);
}
