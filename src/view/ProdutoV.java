/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import modelo.ProdutoM;

/**
 *
 * @author jhonn
 */
public class ProdutoV implements IProdutoV{
    
    private static Scanner ler = new Scanner(System.in);
    
    private ProdutoV() {
    }
    
    public static ProdutoV getInstace() {
        return new ProdutoV();
    }
    
    @Override
    public void ImprimirTodosProdutos(ProdutoM[] produtos) {
        System.out.printf("%-20S%-20S%-20S%-20S%-20S%n", "Código", "Nome", "Marca", "Preço", "Quantidade em estoque");
        for (ProdutoM prod : produtos) {
            if (prod != null) {
                if ((prod.getNome() != null)) {
                    if (prod.getListavel()) {
                        System.out.printf("%-20d%-20s%-20s%S%-18.2f%-20.2f%n", prod.getCodigoProduto(), prod.getNome(), prod.getMarca(),
                                "R$", prod.getPreco(), prod.getQnt_estoque());
                    }
                }
            }
        }
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
    
    @Override
    public void ImprimirProduto(ProdutoM produto) {
        System.out.printf("%-20S%-20S%-20S%-20S%-20S%n", "Código", "Nome", "Marca", "Preço", "Quantidade em estoque");
        System.out.printf("%-20d%-20s%-20s%S%-18.2f%-20.2f%n", produto.getCodigoProduto(), produto.getNome(), produto.getMarca(), "R$", produto.getPreco(), produto.getQnt_estoque());
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
    
    @Override
    public void caracteristicasDoProd(ProdutoM modelProduto) {
        if (modelProduto != null) {
            System.out.printf("%-20S%-20S%-20S%-20S%-20S%n", "Código", "Nome", "Marca", "Preço", "Quantidade em estoque");
            System.out.printf("%-20d%-20s%-20s%S%-18.2f%-20.2f%n", modelProduto.getCodigoProduto(), modelProduto.getNome(), modelProduto.getMarca(), "R$", modelProduto.getPreco(), modelProduto.getQnt_estoque());
            for (int i = 0; i < 100; i++) {
                System.out.print("-");
            }
            System.out.println("");

        }
    }
    
    @Override
    public String selecionarBusca() {
        System.out.printf("%s\n%-20S%-20S\n", "Buscar por código ou nome? (Insira o número correspondente)", "(1) nome", "(2) código");
        return ler.nextLine();
    }
    
    @Override
    public String nomeProduto() {
        System.out.println("Insira o nome do produto ");
        return ler.nextLine();
    }
    
    @Override
    public String codigoProduto() {
        System.out.println("Insira o codigo do produto ");
        return ler.nextLine();
    }
    
    @Override
    public void mensagemNaoEncontrado() {
        System.out.println("Produto não encontrado...");
    }
    
    @Override
    public void mensagemNumInvalido() {
        System.out.println("Número inválido...");
    }
    
    @Override
    public void iniciandoInsercao() {
        System.out.println("Iniciando processo de inserção de novo produto...");
        System.out.println("Por padrão use sempre letras MAIÚSCULAS, sem acentos e 'c' no lugar de 'ç'...");
    }
    
    @Override
    public void imprimeCodigoInserção(int cogido) {
        System.out.printf("%-20S%-20d%n", "código: ", cogido);
    }
    
    @Override
    public void iniciandoExclusao() {
        System.out.println("Iniciando o processo de exclusão de um produto...");
    }
    
    @Override
    public String iniciandoAlteração() {
        System.out.println("Iniciando processo de alteração de dados...");
        System.out.print("Insira o código do produto que deseja auterar: ");
        return ler.nextLine();
    }
    
    @Override
    public String SelecionarDado() {
        System.out.printf("%s\n%S\t%S\n%S\t%S\n%S\n", "Qual dado será auterado? (Insira o número correspondente)", "(1) - Código",
                    "(2) - Nome", "(3) - Marca", "(4) - Preço", "(5) - Quantidade em estoque");
        return ler.nextLine();
    }
    
    @Override
    public String novoCodigo() {
        System.out.print("Insira o novo código: ");
        return ler.nextLine();
    }
    
    @Override
    public String haProdutoComMesmoCodigo(int codigo) {
        System.out.printf("%S%d%n%S%d%n%S%n", "Foi identificado que há um produto com o código ", codigo,
                "Insira (1) para excluir o produto de codigo ", codigo, "Insira (2) para cancelar a ação");
        return ler.nextLine();
    }
    
    @Override
    public String novoNome() {
        System.out.print("Insira o novo nome: ");
        return ler.nextLine();
    }
    
    @Override
    public String haProdutoComMesmoNome(String nome) {
        System.out.printf("%s%S%n%s%S%n%s%n", "Foi identificado que há um produto com o nome ", nome,
                "Insira (1) para alterar o nome para ", nome, "Insira (2) para cancelar a ação");
        return ler.nextLine();
    }
    
    @Override
    public String novaMarca(){
        System.out.print("Insira o nova marca: ");
        return ler.nextLine();
    }
    
    @Override
    public String novoPreco() {
        System.out.print("Insira o novo preço: ");
        return ler.nextLine();
    }
    
    @Override
    public String novaQnt_emEstoque() {
        System.out.print("Insira a nova quantidade em estoque: ");
        return ler.nextLine();
    }
    
    @Override
    public void operacaoImpossível(){
        System.out.println("Operação Impossível");
    }
    
    @Override
    public String marcaProduto() {
        System.out.println("Insira a marca do produto ");
        return ler.nextLine();
    }
    
    @Override
    public String precoProduto() {
        System.out.println("Insira o preco do produto ");
        return ler.nextLine();
    }
    
    @Override
    public String qntEstoqueProduto() {
        System.out.println("Insira o a quantidade em estoque ");
        return ler.nextLine();
    }

    @Override
    public boolean selecionarListagem() {
        System.out.printf("%s\n%-20S%-20S\n", "Listar por código ou nome? (Insira o número correspondente)", "(1) nome", "(2) código");
        String aux = ler.nextLine();
        return aux.equals("1");
    }

    @Override
    public boolean exitoOperacional(boolean operacao) {
        if (operacao) {
            System.out.println("EXITO");
            return operacao;
        } else {
            System.out.println("INEXITO");
            return operacao;
        }
    }
}
