/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import modelo.VendaM;

/**
 *
 * @author jhonn
 */
public interface IVendaV {
    
    public void ImprimirTodasVendas(VendaM[] vendas);
    
    public void caracteristicasDaVenda(VendaM venda);
    
    public boolean exitoOperacional(boolean operacao);
    
    public String insiraData();
    
    public int insiraCodigo();
    
    public String inserirProduto();
    
    public String quantidadeProduto();
    
    public String inserirMaisProdutos();
}
