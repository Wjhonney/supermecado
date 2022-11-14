/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jhonn
 */
public interface IVendaM {
    
    public int getCodigo();

    public void setCodigo(int codigo);

    public Date getData();

    public void setData(String data);

    public ItemM[] getItensVendidos();

    public void setItensVendidos(CarrinhoM carrinho);

    public SimpleDateFormat getFormato();

    public boolean getFinaliza();

    public void setFinaliza(boolean finaliza);
}
