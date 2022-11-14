/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.CarrinhoM;
import modelo.ItemM;
import modelo.VendaM;
import view.IProdutoV;
import view.IVendaV;

/**
 *
 * @author jhonn
 */
public class VendaR implements IVendaR{

    private static VendaR instace;

    private VendaM[] modelVenda;

    static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    static Date data;

    private ProdutoR repoProduto;

    private IVendaV viewVenda;

    private IProdutoV viewProduto;

    private VendaR(IVendaV vendaV, IProdutoV produtoV) {
        modelVenda = new VendaM[100];
        repoProduto = ProdutoR.getInstance(produtoV);
        viewVenda = vendaV;
        viewProduto = produtoV;
        init();
    }
    
    public static VendaR getInstace(IVendaV vendaV, IProdutoV produtoV) {
        if (instace == null) {
            instace = new VendaR(vendaV, produtoV);
            return instace;
        } else {
            return instace;
        }
    }

    @Override
    public VendaM[] getModelVenda() {
        VendaM[] aux = new VendaM[this.modelVenda.length];
        int i = 0;
        for (VendaM vend : this.modelVenda) {
            aux[i] = VendaM.getInstance(vend);
            i++;
        }
        return aux;
    }

    @Override
    public void init() {

        // Venda
        // 1ª Venda
        modelVenda[0] = new VendaM();
        modelVenda[0].setData("01/02/2022");
        CarrinhoM carrinho00 = new CarrinhoM();
        ItemM itens00 = ItemM.getInstance(repoProduto.buscarPorCodigo(1), 2);
        carrinho00.setItens(itens00);
        itens00.setModelProduto(repoProduto.buscarPorCodigo(2));
        itens00.setQuantidade(1);
        carrinho00.setItens(itens00);
        itens00.setModelProduto(repoProduto.buscarPorCodigo(3));
        itens00.setQuantidade(1);
        carrinho00.setItens(itens00);
        modelVenda[0].setFinaliza(true);
        modelVenda[0].setItensVendidos(carrinho00);

        // 2ª Venda
        modelVenda[1] = new VendaM();
        modelVenda[1].setData("05/02/2022");
        itens00.setModelProduto(repoProduto.buscarPorCodigo(4));
        itens00.setQuantidade(2);
        carrinho00.setItens(itens00);
        itens00.setModelProduto(repoProduto.buscarPorCodigo(5));
        itens00.setQuantidade(5);
        carrinho00.setItens(itens00);
        modelVenda[1].setFinaliza(true);
        modelVenda[1].setItensVendidos(carrinho00);

    }

    @Override
    public VendaM[] listarVendasPorData() throws ParseException {

        data = formato.parse(viewVenda.insiraData());

        VendaM[] listaPorDia = new VendaM[100];

        int i = 0;

        for (VendaM vend : modelVenda) {
            if ((vend != null))  {
                if (data.equals(vend.getData())) {
                    listaPorDia[i] = vend;
                    i += 1;
                }
            }
        }
        return listaPorDia;
    }

    @Override
    public VendaM buscaVendasPorCodigo(int codigo) {

        VendaM busca = null;
        
        for (int i = 0; i < modelVenda.length; i++) {
            if (modelVenda[i] != null) {
                if (modelVenda[i].getCodigo() == codigo) {
                    busca = modelVenda[i];
                    break;
                }
            }
        }
        return busca;
    }

    @Override
    public void inserirVenda(VendaM venda) {
        for (int i = 0; i < modelVenda.length; i++) {
            if (modelVenda[i] == null) {
                modelVenda[i] = venda;
                break;
            }
        }
        for (int i = 0; i < venda.getItensVendidos().length; i++) {
            for (int j = 0; j < repoProduto.getModelProduto().length; j++) {
                if ((repoProduto.getModelProduto()[j] != null) && (venda.getItensVendidos()[i] != null)) {
                    // da baixa na quantidade
                    if (venda.getItensVendidos()[i].getModelProduto().getNome().equalsIgnoreCase(repoProduto.getModelProduto()[j].getNome())) {
                        repoProduto.getModelProduto()[j].setQnt_estoque(repoProduto.getModelProduto()[j].getQnt_estoque() - venda.getItensVendidos()[i].getQuantidade());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void fazerVenda() {
        // cria um objeto de carrinho
        CarrinhoM carrinho = new CarrinhoM();

        int select = Integer.valueOf(viewVenda.inserirProduto());
        viewProduto.ImprimirProduto(repoProduto.buscarPorCodigo(select));
        double quantidade = Double.valueOf(viewVenda.quantidadeProduto());

        // cria um item
        ItemM modelItem = ItemM.getInstance(repoProduto.buscarPorCodigo(select), quantidade);

        // coloca item no carrinho
        carrinho.setItens(modelItem);
        modelItem = null;
        boolean cont = true;
        cont = "s".equalsIgnoreCase(viewVenda.inserirMaisProdutos());

        while (cont) {
            select = Integer.valueOf(viewVenda.inserirProduto());
            viewProduto.ImprimirProduto(repoProduto.buscarPorCodigo(select));
            quantidade = Double.valueOf(viewVenda.quantidadeProduto());
            modelItem = ItemM.getInstance(repoProduto.buscarPorCodigo(select), quantidade);
            carrinho.setItens(modelItem);
            cont = "s".equalsIgnoreCase(viewVenda.inserirMaisProdutos());
        }

        // cria uma venda
        VendaM modelVenda = new VendaM();
        modelVenda.setItensVendidos(carrinho);
        
        boolean temCodigoIgual = false;
        boolean entrei = false;
        
        // procura se tem this.modelVenda com o mesmo codigo
        for (int i = 0; i < this.modelVenda.length; i++) {
            if (buscaVendasPorCodigo(modelVenda.getCodigo()) != null) {
                temCodigoIgual = true;
                break;
            }
        }

        // procura um codigo q nn foi usado
        if (temCodigoIgual) {
            for (int codigo = 1; codigo < this.modelVenda.length + 1; codigo++) {
                for (int j = 0; j < this.modelVenda.length; j++) {
                    if (this.modelVenda[j] != null) {
                        if (this.modelVenda[j].getCodigo() == codigo) {
                            break;
                        }
                    }
                    if (j == this.modelVenda.length - 1) {
                        modelVenda.setCodigo(codigo);
                        entrei = true;
                        break;
                    }
                }
                if (entrei) {
                    break;
                }
            }
        }
        
        // atribui uma data
        modelVenda.setData(viewVenda.insiraData());
        modelVenda.setFinaliza(true);

        // Inseri a venda no vetor de vendas
        inserirVenda(modelVenda);
        viewVenda.ImprimirTodasVendas(this.modelVenda);
    }
    
    @Override
    public VendaM BuscarVenda() {
        
        int codigo = viewVenda.insiraCodigo();
        
        for (int i = 0; i < modelVenda.length; i++) {
            if (modelVenda[i] != null) {
                if (modelVenda[i].getCodigo() == codigo) {
                    return modelVenda[i];
                }
            }
        }
        return null;
    }

}
