/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import Exception.NaoHaUsuarioComCodigoInseridoException;
import Exception.OpcaoInseridaInvalidaException;
import Exception.RepositorioCheioException;
import Exception.SenhaInvalidaException;
import modelo.IUsuarioM;
import modelo.UsuarioM;

/**
 *
 * @author jhonn
 */
public interface IUsuarioR {
    
    public IUsuarioM autenticaUsuario() throws UsuarioInvalidoException;

    public void criarUsuario() throws RepositorioCheioException;

    public UsuarioM[] listarUsuarios();

    public void excluirUsuario() throws NaoHaUsuarioComCodigoInseridoException;

    public void auterarUsuario() throws NaoHaUsuarioComCodigoInseridoException, SenhaInvalidaException, 
            OpcaoInseridaInvalidaException;
    
}
