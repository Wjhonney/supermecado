/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

/**
 *
 * @author jhonn
 */
public class UsuarioInvalidoException extends Exception {

    public UsuarioInvalidoException() {
        super();
    }

    public UsuarioInvalidoException(String mensagem) {
        super(mensagem);
    }
    
}
