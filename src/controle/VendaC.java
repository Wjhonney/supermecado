/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.text.ParseException;
import repositorio.IVendaR;
import repositorio.VendaR;
import view.IVendaV;
import view.ProdutoV;
import view.VendaV;

/**
 *
 * @author jhonn
 */
public class VendaC {
    
    private static VendaC instace;

    private VendaC() {
    }
   
    public static VendaC getInstace() {
        if (instace == null) {
            instace = new VendaC();
        }
        return instace;
    }

    public void listarVendasRealizadas(IVendaR repoVenda, IVendaV viewVenda) {
        viewVenda.ImprimirTodasVendas(repoVenda.getModelVenda());
    }

    public void listarVendasPorDia(IVendaR repoVenda, IVendaV viewVenda) {
        try {
            viewVenda.ImprimirTodasVendas(repoVenda.listarVendasPorData());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public void listarVendasPorCod(IVendaR repoVenda, IVendaV viewVenda) {
        viewVenda.caracteristicasDaVenda(repoVenda.BuscarVenda());
    }

    public void criarCarrinho(IVendaR repoVenda) {
        repoVenda.fazerVenda();
    }
    
}
