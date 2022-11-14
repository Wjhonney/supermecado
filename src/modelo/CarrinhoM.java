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
public class CarrinhoM implements ICarrinhoM{
    
    private ItemM[] modelItens = new ItemM[10];

    // metodos especiais
    @Override
    public ItemM[] getModelItens() {
        // cria um novo vetor de items para impedir quebra de encapsulamento
        ItemM[] itemAux = new ItemM[modelItens.length];
        int i = 0;
        for (ItemM itens1 : modelItens) {
            itemAux[i] = ItemM.getInstance(itens1);
            i++;
        }
        // retorna uma copia de Itens
        return itemAux;
    }

    @Override
    public boolean setItens(ItemM itens) {

        boolean verificador = false;
        // procura algum item com o mesmo nome do produto do paramtro
        for (int j = 0; j < this.modelItens.length; j++) {
            // se tiver o mesmo nome aumenta a quantidade e atualiza o preço
            if (this.modelItens[j] == null) {
                continue;
            }
            if (this.modelItens[j].getModelProduto().getNome().equalsIgnoreCase(itens.getModelProduto().getNome())) {
                // testa se a soma das quantidades é menor q ou igual o disponivel em estoque 
                if (((this.modelItens[j].getQuantidade() + itens.getQuantidade()) <= itens.getModelProduto().getQnt_estoque()) && ((this.modelItens[j].getQuantidade() + itens.getQuantidade()) >= 0)) {
                    this.modelItens[j].setQuantidade(this.modelItens[j].getQuantidade() + itens.getQuantidade());
                    verificador = true;
                } else if (!((this.modelItens[j].getQuantidade() + itens.getQuantidade()) <= itens.getModelProduto().getQnt_estoque())) {
                    this.modelItens[j].setQuantidade(itens.getModelProduto().getQnt_estoque());
                    verificador = true;
                } else {
                    this.modelItens[j].setQuantidade(0);
                    verificador = true;
                }
            }
        }

        // se nn tiver achado nenhum produto com o mesmo nome cria mais uma
        // posição e introduz o parametro
        if (!verificador) {

            int i = 0;
            for (ItemM itens1 : this.modelItens) {
                if (itens1 == null) {
                    this.modelItens[i] = ItemM.getInstance(itens);
                    break;
                }
                i++;
            }
            verificador = true;
        }
        return verificador;
    }
}
