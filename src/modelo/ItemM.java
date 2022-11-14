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
public class ItemM implements IItemM{
    
    // Atributos
    private ProdutoM modelProduto = new ProdutoM();
    private double precoItem;
    private double quantidade;

    // Metodos especiais
    
    // Contrutor de copia
    private ItemM(ItemM original) {
        this.modelProduto = (original.getModelProduto());
        this.quantidade = (original.getQuantidade());
        this.setPrecoItem();
    }
    
    public ItemM(modelo.ProdutoM modelproduto, double quantidade) {
        this.modelProduto = modelproduto;
        this.quantidade = quantidade;
    }

    // Metodo fabrica padrão de criação de itens
    public static ItemM getInstance(modelo.ProdutoM modelproduto, double quantidade) {
        if ((modelproduto != null) && (quantidade <= modelproduto.getQnt_estoque())) {
            modelo.ItemM item = new modelo.ItemM(modelproduto, quantidade);
            item.setPrecoItem();
            return item;
        } else {
            return null;
        }
    }

    // Metodo fabrica de copia
    public static ItemM getInstance(ItemM original) {
        if ((original != null)) {
            ItemM copia = new ItemM(original);
            return copia;
        } else {
            return null;
        }
    }

    public ProdutoM getModelProduto() {
        // cria um novo produto para impedir quebra de encapsulamento
        return modelo.ProdutoM.getInstance(modelProduto);
    }
    
    // Preciso acessar o produto original em setFinaliza(boolean finaliza) 
    protected ProdutoM getModelProdutoOriginal() {
        return modelProduto;
    }
    
    public void setModelProduto(ProdutoM produto) {
        this.modelProduto = produto;
    }

    public double getPrecoItem() {
        return precoItem;
    }

    // não faz sentido o setPreço possuir parametros, pois o
    // o preço é gerado automaticamente
    private void setPrecoItem() {
        precoItem = getQuantidade() * getModelProduto().getPreco();
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidadeTotal) {
        // Quantidade não pode ser menor q 0 ou maior q a quantidade em estoque
        if ((quantidadeTotal >= 0) && (quantidadeTotal <= this.modelProduto.getQnt_estoque())) {
            this.quantidade = quantidadeTotal;
            // Se quantidadeTotal for menor q 0 quantidade recebe 0
        } else if (!(quantidadeTotal >= 0)) {
            this.quantidade = 0;
            // se quantidadeTotalfor maior q a quantidade em estoque 
            //quantidade recebe quantidade em estoque
        } else if (!(quantidadeTotal <= this.getModelProduto().getQnt_estoque())) {
            this.quantidade = this.modelProduto.getQnt_estoque();
        }

        // calcula o preço
        this.setPrecoItem();

    }
}
