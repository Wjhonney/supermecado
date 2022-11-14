/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import modelo.UsuarioM;

/**
 *
 * @author jhonn
 */
public interface IUsuarioV {
    
    public String soliciarAutenticacao(boolean login);

    public int menuInicial(boolean administrador);
    
    public void loginInvalido();

    public String insiraLogin();

    public String insiraNome();

    public String insiraSenha();

    public void listarUsuarios(UsuarioM[] listarUsuarios);

    public String codigoUsuario();

    public int selecionarDado();

    public void initUsuario();
}
