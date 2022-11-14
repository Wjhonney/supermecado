/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author jhonn
 */
public class SenhaInvalidaException extends Exception {

    public SenhaInvalidaException() {
        super();
    }
    
    public SenhaInvalidaException(String mensagem) {
        super(mensagem);
    }
    
}
