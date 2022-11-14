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
import view.IUsuarioV;

/**
 *
 * @author jhonn
 */
public class UsuarioR implements IUsuarioR{

    private UsuarioM modelUsuario[];
    
    private IUsuarioV viewUsuario;
    
    private static UsuarioR instace;

    private UsuarioR(IUsuarioV usuarioV) {
        modelUsuario = new UsuarioM[10];
        viewUsuario = usuarioV;
        modelUsuario[0] = UsuarioM.getInstance(1, "ADM", "Wellington", "C3fet", true);
        modelUsuario[1] = UsuarioM.getInstance(2, "ATEN", "Wellington", "C3fet", false);
    }
    
    public static UsuarioR getInstace(IUsuarioV usuarioV) {
        if (instace == null) {
            return new UsuarioR(usuarioV);
        } else {
            return instace;
        }
    }
    
    private boolean buscar(String Autenticacao) {
        for (UsuarioM user : modelUsuario) {
            if (user != null) {
                if (Autenticacao.equals(user.getLogin())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean autenticaSenha(String sSenha, String sLogin) {
        for (UsuarioM user : modelUsuario) {
            if (user != null) {
                if (sLogin.equals(user.getLogin())) {
                    if (user.getSenha().equals(sSenha)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private IUsuarioM buscarUsuario(String Login) {
        UsuarioM aux =  null;
        for (UsuarioM user : modelUsuario) {
            if (user != null) {
                if (user.getLogin().equalsIgnoreCase(Login)) {
                    aux = user;
                }
            }
        }
        return aux;
    }
    
    @Override
    public IUsuarioM autenticaUsuario() throws UsuarioInvalidoException{
        String sLogin = viewUsuario.soliciarAutenticacao(true);
        boolean login = buscar(sLogin);
        String sSenha = viewUsuario.soliciarAutenticacao(false);
        boolean senha = autenticaSenha(sSenha,sLogin);
        if ((!login && !senha) || (login && !senha) || (!login && senha)) {
            throw new UsuarioInvalidoException("Login ou senha invalido");
        } else {
            return buscarUsuario(sLogin);
        }
    }

    @Override
    public void criarUsuario() throws RepositorioCheioException {
        int p = 0; boolean entrei = false;
        for (int i = 0; i < modelUsuario.length; i++) {
            if (modelUsuario[i] == null) {
                p = i;
            } else if (i == modelUsuario.length - 1) {
                throw new RepositorioCheioException("Nao ha mais espaço para alocar usuarios");
            }
        }
        
        modelUsuario[p] = UsuarioM.getInstance();
        // atribui um codigo
        
        // procura um codigo q nn foi usado
        for (int codigo = 1; codigo < modelUsuario.length + 1; codigo++) {
            for (int j = 0; j < modelUsuario.length; j++) {
                if (modelUsuario[j] != null) {
                    if (modelUsuario[j].getCodigo()== codigo) {
                        break;
                    }
                }
                if (j == modelUsuario.length - 1) {
                    modelUsuario[p].setCodigo(codigo);
                    entrei = true;
                    break;
                }
            }
            if (entrei) {
                break;
            }
        }
        
        // atribui um login
        modelUsuario[p].setLogin(viewUsuario.insiraLogin());
        
        // atribui um nome
        modelUsuario[p].setNome(viewUsuario.insiraNome());
        
        try {
            // atribui uma senha
            modelUsuario[p].setSenha(viewUsuario.insiraSenha());
        } catch (SenhaInvalidaException ex) {
            System.out.println(ex.getMessage());
            modelUsuario[p] = null;
        }
        
    }

    @Override
    public UsuarioM[] listarUsuarios() {
        UsuarioM[] lista = new UsuarioM[modelUsuario.length];
        for (int i = 0; i < modelUsuario.length; i++) {
            lista[i] = modelUsuario[i];
        }
        return lista;
    }

    @Override
    public void excluirUsuario() throws NaoHaUsuarioComCodigoInseridoException {
        // pergunta o codigo do usuario.
        int codigo = Integer.valueOf(viewUsuario.codigoUsuario());
        for (int i = 0; i < modelUsuario.length; i++) {
            if (modelUsuario[i] != null) {
                if (modelUsuario[i].getCodigo() == codigo) {
                    modelUsuario[i] = null;
                } else if (i == modelUsuario.length -1) {
                    throw new NaoHaUsuarioComCodigoInseridoException("O codigo inserido nao corresponde a nenhum usuario");
                }
            }
        }
    }

    @Override
    public void auterarUsuario() throws NaoHaUsuarioComCodigoInseridoException, SenhaInvalidaException, 
            OpcaoInseridaInvalidaException {
        // pergunta o codigo do usuario.
        int codigo = Integer.valueOf(viewUsuario.codigoUsuario());
        for (int i = 0; i < modelUsuario.length; i++) {
            if (modelUsuario[i] != null) {
                if (modelUsuario[i].getCodigo() == codigo) {
                    
                    // pergunta oque qr auterar
                    int select = viewUsuario.selecionarDado();
                    switch(select) {
                        case 1:
                            // auterar nome
                            modelUsuario[i].setNome(auterarNome());
                            break;
                        case 2:
                            // auterar Login
                            modelUsuario[i].setLogin(auterarLogin());
                            break;
                        case 3:
                            // auterar senha
                            modelUsuario[i].setSenha(auterarSenha());
                            break;
                        default:
                            throw new OpcaoInseridaInvalidaException("Opcao inserida invalida");
                    }
                    break;
                } else if (i == modelUsuario.length -1) {
                    throw new NaoHaUsuarioComCodigoInseridoException("O codigo inserido nao corresponde a nenhum usuario");
                }
            }
        }
        
        
        
        
    }
    
    private String auterarLogin() {
        return viewUsuario.insiraLogin();
    }
    
    private String auterarNome() {
        return viewUsuario.insiraNome();
    }
    
    private String auterarSenha() {
        return viewUsuario.insiraSenha();
    }

}
