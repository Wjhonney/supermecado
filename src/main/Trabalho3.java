package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import controle.Principal;
import modelo.IUsuarioM;
import repositorio.ProdutoR;
import repositorio.UsuarioR;
import repositorio.VendaR;
import view.ProdutoV;
import view.UsuarioV;
import view.VendaV;
/**
 *
 * @author jhonn
 */
public class Trabalho3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProdutoV viewProduto = ProdutoV.getInstace();
        ProdutoR repoProduto = ProdutoR.getInstance(viewProduto);
        UsuarioV viewUsuario = UsuarioV.getInstace();
        UsuarioR repoUsuario = UsuarioR.getInstace(viewUsuario);
        VendaV viewVenda = VendaV.getInstace();
        VendaR repoVenda = VendaR.getInstace(viewVenda, viewProduto);
        Principal controPricipal = Principal.getInstace(repoProduto, viewProduto, repoUsuario, viewUsuario, repoVenda, viewVenda);
        boolean autenticacao = true;
        IUsuarioM usuario;
        
        do {
        usuario = controPricipal.iniciar();
        
        if (usuario != null) {
            autenticacao = false;
        }
        
        } while (autenticacao);
        
        autenticacao = true;
        
        while (autenticacao){ // sai quando autenticacao eh falso
            int opcao = controPricipal.menuInicial(usuario.isAdm());
            autenticacao = controPricipal.execultar(opcao, usuario.isAdm());
        }
        
    }
    
}
