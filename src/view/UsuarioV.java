/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import modelo.UsuarioM;

/**
 *
 * @author jhonn
 */
public class UsuarioV implements IUsuarioV{

    private static Scanner ler = new Scanner(System.in);
    
    private UsuarioV() {
    }
    
    public static UsuarioV getInstace() {
        return new UsuarioV();
    }
    
    @Override
    public String soliciarAutenticacao(boolean login) {
        if (login) {
            System.out.printf("%-30S","Insira seu login: ");
            return ler.nextLine();
        }else{
            System.out.printf("%-30S","Insira sua senha: ");
        return ler.nextLine();
        }
        
    }

    @Override
    public int menuInicial(boolean administrador) {
        
        if (administrador) {
            System.out.printf("%S%n%-30S%-30S%-30S%n%-30S%-30S%-30S%n%-30S%-30S%-30S%n%-30S%-30S%-30S%n", "Qual será a ação execultada?", "(1) Inserir produto",
                            "(2) Excluir produto", "(3) Alterar Produto", "(4) Listar Todos Produtos", "(5) Listar vendas realizadas",
                            "(6) Listar Vendas por dia", "(7) pesquisar vendas por cod.", "(8) Criar Usuario", "(9) Listar Usuarios", 
                            "(10) Alterar Usuario", "(11) deletar Usuario", "(0) Finalizar Programa");
        } else {
            System.out.printf("%S%n%-30S%-30S%-30S%n", "Qual será a ação execultada?",
                            "(1) Buscar produtos", "(2) Fazer Venda","(0) Finalizar Programa");
            
        }
        String aux = ler.nextLine();
        return Integer.valueOf(aux);
    }
    
    @Override
    public void loginInvalido() {
        System.out.println("Senha ou Login invalido");
    }

    @Override
    public String insiraLogin() {
        System.out.println("Insira um login para entrar");
        return ler.nextLine();
    }

    @Override
    public String insiraNome() {
        System.out.println("Insira um nome para usuário");
        return ler.nextLine();
    }

    @Override
    public String insiraSenha() {
        System.out.printf("%S\n%S\n","Insira uma senha para usuário",
                "A senha deve ter entre 4 a 8 caracteres, no mínimo 1 letra maiuscaula e 1 numero");
        return ler.nextLine();
    }

    @Override
    public void listarUsuarios(UsuarioM[] listarUsuarios) {
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println("");
        
        System.out.printf("%-30S%-30S%-30S\n","codigo", "nome", "login");
        
        for (int i = 0; i < listarUsuarios.length; i++) {
            if (listarUsuarios[i] != null) {
                if (listarUsuarios[i].getNome() != null) {
                    if (listarUsuarios[i].getLogin() != null) {
                        System.out.printf("%-30S%-30S%-30S\n",listarUsuarios[i].getCodigo(), listarUsuarios[i].getNome(),
                                listarUsuarios[i].getLogin());
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
    public String codigoUsuario() {
        System.out.println("Insira o codigo do usuario que será auterado");
        return ler.nextLine();
    }

    @Override
    public int selecionarDado() {
        System.out.printf("%-30S%-30S%-30S\n","(1) Auterar Nome", "(2) Auterar Login", "(3) Auterar Senha");
        return Integer.valueOf(ler.nextLine());
    }

    @Override
    public void initUsuario() {
        System.out.printf("%S%n%S\t%S\t%s%n%S\t%S\t%s%n","Para testes use: ", "Administrador", "login: ADM", "SENHA: C3fet", 
                "Atendente", "login: ATEN", "SENHA: C3fet");
    }
    
}
