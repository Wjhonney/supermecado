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
public class UsuarioM implements IUsuarioM {

    private int codigo;
    private String login;
    private String nome;
    private String senha;
    private boolean adm;

    private UsuarioM(int codigo, String login, String nome, String senha, boolean adm) {
        this.codigo = codigo;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.adm = adm;
    }
    
    private UsuarioM() {
    }

    public static UsuarioM getInstance(int codigo, String login, String nome, String senha, boolean adm) {
        if ((codigo > 0) && (login != null) && (nome != null) && (senha != null)) {
            return new UsuarioM(codigo, login, nome, senha, adm);
        } else {
            return null;
        }
    }
    
    public static UsuarioM getInstance() {
        return new UsuarioM();
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
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) throws SenhaInvalidaException{ // senha tem q de 4 a 8 caracteres, 
                                                                    // no minimo 1 letra maiuscula e 1 número
        boolean maiuscula = false;
        boolean numero = false;
        if (senha != null) {
            if ((senha.length() >= 4) && (senha.length() <= 8)) {
                char charSenha[] = senha.toCharArray();
                for (int i = 0; i < charSenha.length; i++) {
                    if (charSenha[i] == 'A' || charSenha[i] == 'B' || charSenha[i] == 'C' || charSenha[i] == 'D' || charSenha[i] == 'E'
                            || charSenha[i] == 'F' || charSenha[i] == 'G' || charSenha[i] == 'H' || charSenha[i] == 'I' || charSenha[i] == 'J'
                            || charSenha[i] == 'K' || charSenha[i] == 'L' || charSenha[i] == 'M' || charSenha[i] == 'N' || charSenha[i] == 'O'
                            || charSenha[i] == 'P' || charSenha[i] == 'Q' || charSenha[i] == 'R' || charSenha[i] == 'S' || charSenha[i] == 'T'
                            || charSenha[i] == 'U' || charSenha[i] == 'V' || charSenha[i] == 'W' || charSenha[i] == 'X' || charSenha[i] == 'Y'
                            || charSenha[i] == 'Z') {
                        maiuscula = true;
                        break;
                    }
                }
                for (int j = 0; j < charSenha.length; j++) {
                    if (charSenha[j] == '0' || charSenha[j] == '1' || charSenha[j] == '2' || charSenha[j] == '3'
                            || charSenha[j] == '4' || charSenha[j] == '5' || charSenha[j] == '6' || charSenha[j] == '7'
                            || charSenha[j] == '8' || charSenha[j] == '9') {
                        numero = true;
                        break;
                    }
                }
            }
        }
        
        if (maiuscula && numero){
            this.senha = senha;
        } else if (maiuscula && !numero) {
            throw new SenhaInvalidaException("senha nao possui numero");
        } else if (!maiuscula && numero) {
            throw new SenhaInvalidaException("senha nao possui letra maiuscula");
        } else {
            throw new SenhaInvalidaException("A senha deve ter de 4 a 8 caracteres, 1 letra Maiuscula e 1 numero");
        }
    }

    @Override
    public boolean isAdm() {
        return adm;
    }

    @Override
    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    
    
}
