/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.logging.Level;
import java.util.logging.Logger;
import Exception.NaoHaUsuarioComCodigoInseridoException;
import Exception.OpcaoInseridaInvalidaException;
import Exception.RepositorioCheioException;
import Exception.SenhaInvalidaException;
import modelo.IUsuarioM;
import repositorio.IUsuarioR;
import repositorio.UsuarioInvalidoException;
import view.IUsuarioV;

/**
 *
 * @author jhonn
 */
public class UsuarioC {
    
    private static UsuarioC instace;

    private UsuarioC() {
    }
    
    public static UsuarioC getInstance() {
        if(instace == null){
            instace = new UsuarioC();
        }
        return instace;
    }
    
    public IUsuarioM autenticar(IUsuarioV viewUsuario, IUsuarioR repoUsuario) {
        IUsuarioM aux = null;
        viewUsuario.initUsuario();
        try {
            aux = repoUsuario.autenticaUsuario();
        } catch (UsuarioInvalidoException ex) {
            System.out.println(ex.getMessage());
        }
        return aux;
    }
    
    public int menuInicial(IUsuarioV viewUsuario, boolean administrador) {
        return viewUsuario.menuInicial(administrador);
    }
    
    public void criarUsuario(IUsuarioR repoUsuario) {
        try {
            repoUsuario.criarUsuario();
        } catch (RepositorioCheioException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void listarUsuarios(IUsuarioV viewUsuario, IUsuarioR repoUsuario) {
        viewUsuario.listarUsuarios(repoUsuario.listarUsuarios());
    }
    
    public void auterarUsuario(IUsuarioR repoUsuario) {
        try {
            repoUsuario.auterarUsuario();
        } catch (NaoHaUsuarioComCodigoInseridoException | SenhaInvalidaException | 
                OpcaoInseridaInvalidaException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void excluirUsuario(IUsuarioR repoUsuario) {
        try {
            repoUsuario.excluirUsuario();
        } catch (NaoHaUsuarioComCodigoInseridoException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
