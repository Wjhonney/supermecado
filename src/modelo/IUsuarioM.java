/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Exception.SenhaInvalidaException;

/**
 *
 * @author jhonn
 */
public interface IUsuarioM {
    
    public int getCodigo();

    public void setCodigo(int codigo);

    public String getLogin();

    public void setLogin(String login);

    public String getNome();

    public void setNome(String nome);

    public String getSenha();

    public void setSenha(String senha) throws SenhaInvalidaException;
    
    public boolean isAdm();

    public void setAdm(boolean adm);
}
