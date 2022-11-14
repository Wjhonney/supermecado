/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import Exception.HaviaProdutoComMesmoDadoException;
import Exception.NaoHaProdutodoComCodigoInseridoException;
import Exception.OpcaoInseridaInvalidaException;
import Exception.QuantidadeEstocadaInvalidaException;
import Exception.RepositorioCheioException;
import modelo.ProdutoM;

/**
 *
 * @author jhonn
 */
public interface IProdutoR {

    public ProdutoM[] getModelProduto();

    public void init();

    public ProdutoM buscar() throws OpcaoInseridaInvalidaException;

    public ProdutoM buscarPorNome();

    public ProdutoM buscarPorCodigo();

    public ProdutoM buscarPorCodigo(int codigo);

    public void inserirProduto() throws RepositorioCheioException, HaviaProdutoComMesmoDadoException,
            QuantidadeEstocadaInvalidaException;

    public void excluirProduto();

    public void excluirProduto(int codigo);

    public void alterar() throws HaviaProdutoComMesmoDadoException, NaoHaProdutodoComCodigoInseridoException,
            QuantidadeEstocadaInvalidaException, OpcaoInseridaInvalidaException;

    public void alterarCodigo(int codigo) throws HaviaProdutoComMesmoDadoException;

    public void alterarNome(int codigo) throws HaviaProdutoComMesmoDadoException;

    public ProdutoM[] listarProdutosPorCodigo();

    public ProdutoM[] listarProdutosPorNome();
}
