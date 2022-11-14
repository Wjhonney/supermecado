/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.text.ParseException;
import modelo.VendaM;

/**
 *
 * @author jhonn
 */
public interface IVendaR {
    
    public VendaM[] getModelVenda();

    public void init();

    public VendaM[] listarVendasPorData() throws ParseException;

    public VendaM buscaVendasPorCodigo(int codigo);

    public void inserirVenda(VendaM venda);

    public void fazerVenda();
    
    public VendaM BuscarVenda();
    
}
