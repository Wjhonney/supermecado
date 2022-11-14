/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import Exception.HaviaProdutoComMesmoDadoException;
import Exception.NaoHaProdutodoComCodigoInseridoException;
import Exception.QuantidadeEstocadaInvalidaException;
import Exception.RepositorioCheioException;
import Exception.OpcaoInseridaInvalidaException;
import repositorio.IProdutoR;
import view.IProdutoV;

/**
 *
 * @author jhonn
 */
public class ProdutoC {
    
    private static ProdutoC instace;

    private ProdutoC() {
    }
    
    public static ProdutoC getInstace() {
        if (instace == null) {
            instace = new ProdutoC();
        }
        return instace;
    }
    
    public void inserirProduto(IProdutoR repoProduto) {
        try {
            repoProduto.inserirProduto();
        } catch (RepositorioCheioException | HaviaProdutoComMesmoDadoException | 
                QuantidadeEstocadaInvalidaException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void excluirProduto(IProdutoR repoProduto) {
        repoProduto.excluirProduto();
    }

    public void alterarProduto(IProdutoR repoProduto) {
        try {
            repoProduto.alterar();
        } catch (HaviaProdutoComMesmoDadoException | NaoHaProdutodoComCodigoInseridoException | 
                QuantidadeEstocadaInvalidaException | OpcaoInseridaInvalidaException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void listarTodosProdutos(IProdutoR repoProduto, IProdutoV viewProduto) {
        boolean select = viewProduto.selecionarListagem();
        if (select) {
            viewProduto.ImprimirTodosProdutos(repoProduto.listarProdutosPorNome());
        } else {
            viewProduto.ImprimirTodosProdutos(repoProduto.listarProdutosPorCodigo());
        }
    }

    public void buscarProduto(IProdutoR repoProduto, IProdutoV viewProduto) {
        try {
            viewProduto.ImprimirProduto(repoProduto.buscar());
        } catch (OpcaoInseridaInvalidaException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
