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
public class VendaM implements IVendaM{
    
    // Atributos
    private int codigo;
    private final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private Date data;
    private ItemM[] itensVendidos = new ItemM[100];
    private boolean finaliza;
    
    // para garantir que duas vendas nn tenham o mesmo codigo
    private static int proximoCodigo = 1;

    // Metodos especiais
    public VendaM() {
        this.codigo = proximoCodigo;
        proximoCodigo++;
    }

    // construtor por copia
    private VendaM(VendaM original) {
        this.codigo = original.codigo;
        this.data = original.getData();
        this.itensVendidos = original.getItensVendidos();
    }

    // Metodo Fabica por copia
    public static VendaM getInstance(VendaM original) {
        if (original != null) {
            VendaM aux = new VendaM(original);
            return aux;
        } else {
            return null;
        }
    }

    @Override
    public int getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public Date getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        try {
            this.data = formato.parse(data);
        } catch (ParseException ex) {
            System.out.println("Data invalida, tente no formato dd/mm/aaaa");
        }
    }

    @Override
    public ItemM[] getItensVendidos() {
        // cria um novo vetor de items para impedir quebra de encapsulamento
        modelo.ItemM[] itemAux = new modelo.ItemM[itensVendidos.length];
        int i = 0;
        for (ItemM itens1 : itensVendidos) {
            itemAux[i] = ItemM.getInstance(itens1);
            i++;
        }
        // retorna uma copia de Itens
        return itemAux;
    }

    @Override
    public void setItensVendidos(CarrinhoM carrinho) {
        itensVendidos = carrinho.getModelItens();
    }

    @Override
    public SimpleDateFormat getFormato() {
        return formato;
    }

    @Override
    public boolean getFinaliza() {
        return finaliza;
    }

    @Override
    public void setFinaliza(boolean finaliza) {
        
        if (finaliza) {
            for (ItemM itens : itensVendidos) {
                if (itens != null) {
                    ProdutoM aux = itens.getModelProduto();
                    aux.setHaVendas(true);
                    aux.setQnt_estoque(-itens.getQuantidade());
                    itens.setModelProduto(aux);
                }
            }
        }
        this.finaliza = finaliza;
    }
    
}
