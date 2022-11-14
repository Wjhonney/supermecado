/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.IUsuarioM;
import repositorio.IProdutoR;
import repositorio.IUsuarioR;
import repositorio.IVendaR;
import view.IProdutoV;
import view.IUsuarioV;
import view.IVendaV;
/**
 *
 * @author jhonn
 */
public class Principal {

    // Inicializar outros controles
    private IProdutoR repoProduto;
    private IProdutoV viewProduto;
    private IUsuarioR repoUsuario;
    private IUsuarioV viewUsuario;
    private IVendaR repoVenda;
    private IVendaV viewVenda;

    private VendaC controVenda;
    private ProdutoC controProduto;
    private UsuarioC controUsuario;
    
    private static Principal instace;

    private Principal(IProdutoR repoProduto, IProdutoV viewProduto, IUsuarioR repoUsuario, IUsuarioV viewUsuario,
            IVendaR repoVenda, IVendaV viewVenda) {
        this.repoProduto = repoProduto;
        this.viewProduto = viewProduto;
        this.repoUsuario = repoUsuario;
        this.viewUsuario = viewUsuario;
        this.repoVenda = repoVenda;
        this.viewVenda = viewVenda;
        controVenda = VendaC.getInstace();
        controProduto = ProdutoC.getInstace();
        controUsuario = UsuarioC.getInstance();
    }

    public static Principal getInstace(IProdutoR repoProduto, IProdutoV viewProduto, IUsuarioR repoUsuario, IUsuarioV viewUsuario,
            IVendaR repoVenda, IVendaV viewVenda) {
        if ((instace == null) && (repoProduto != null) && (viewProduto != null) && (repoUsuario != null) &&
                (viewUsuario != null) && (repoVenda != null) && (viewVenda != null)) {
            instace = new Principal(repoProduto, viewProduto, repoUsuario, viewUsuario, repoVenda, viewVenda);
        }
        return instace;
    }

    public IUsuarioM iniciar() {
        // solicita login e senha do usuário
        return controUsuario.autenticar(viewUsuario, repoUsuario);
    }

    public int menuInicial(boolean administrador) {
        return controUsuario.menuInicial(viewUsuario, administrador);
    }

    public boolean execultar(int opcao, boolean administrador) {
        if (administrador) {
            switch (opcao) {
            case 1: // INSERIR PRODUTO
                controProduto.inserirProduto(repoProduto);
                return true;
            case 2: // EXCLUIR PRODUTO
                controProduto.excluirProduto(repoProduto);
                return true;
            case 3: // ALTERAR PRODUTO
                controProduto.alterarProduto(repoProduto);
                return true;
            case 4: // LISTAR TODOS PRODUTOS
                controProduto.listarTodosProdutos(repoProduto, viewProduto);
                return true;
            case 5: // LISTAR VENDAS REALIZADAS
                controVenda.listarVendasRealizadas(repoVenda, viewVenda);
                return true;
            case 6: // LISTAR VENDAS POR DIA
                controVenda.listarVendasPorDia(repoVenda, viewVenda);
                return true;
            case 7: // PESQUISAR VENDAS POR COD.
                controVenda.listarVendasPorCod(repoVenda, viewVenda);
                return true;
            case 8: // CRIAR USUARIO
                controUsuario.criarUsuario(repoUsuario);
                return true;
            case 9: // LISTAR USUARIO
                controUsuario.listarUsuarios(viewUsuario, repoUsuario);
                return true;
            case 10: // ALTERAR USUARIO
                controUsuario.auterarUsuario(repoUsuario);
                return true;
            case 11: // DELETAR USUARIO
                controUsuario.excluirUsuario(repoUsuario);
                return true;
            default:
                return false;
            }
        } else {
            switch (opcao) {
            case 1: // BUSCAR PRODUTOS
                controProduto.buscarProduto(repoProduto, viewProduto);
                return true;
            case 2: // CRIAR CARRINHO
                controVenda.criarCarrinho(repoVenda);
                return true;
            default:
                return false;

            }
        }
    }
}
