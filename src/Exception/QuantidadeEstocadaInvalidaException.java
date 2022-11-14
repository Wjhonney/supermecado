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
public class QuantidadeEstocadaInvalidaException extends Exception {

    public QuantidadeEstocadaInvalidaException() {
        super();
    }
    
    public QuantidadeEstocadaInvalidaException(String mensagem) {
        super(mensagem);
    }
    
}
