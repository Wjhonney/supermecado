/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import modelo.ProdutoM;

/**
 *
 * @author jhonn
 */
public interface IProdutoV {

    public void ImprimirTodosProdutos(ProdutoM[] produtos);

    public void ImprimirProduto(ProdutoM produto);

    public void caracteristicasDoProd(ProdutoM modelProduto);

    public String selecionarBusca();

    public String nomeProduto();

    public String codigoProduto();

    public void mensagemNaoEncontrado();

    public void mensagemNumInvalido();

    public void iniciandoInsercao();

    public void imprimeCodigoInserção(int cogido);

    public void iniciandoExclusao();

    public String iniciandoAlteração();

    public String SelecionarDado();

    public String novoCodigo();

    public String haProdutoComMesmoCodigo(int codigo);

    public String novoNome();

    public String haProdutoComMesmoNome(String nome);

    public String novaMarca();

    public String novoPreco();

    public String novaQnt_emEstoque();

    public void operacaoImpossível();

    public String marcaProduto();

    public String precoProduto();

    public String qntEstoqueProduto();

    public boolean selecionarListagem();

    public boolean exitoOperacional(boolean operacao);
}
