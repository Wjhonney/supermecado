/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import Exception.OpcaoInseridaInvalidaException;
import Exception.NaoHaProdutodoComCodigoInseridoException;
import Exception.QuantidadeEstocadaInvalidaException;
import Exception.HaviaProdutoComMesmoDadoException;
import Exception.RepositorioCheioException;
import modelo.ProdutoM;
import view.IProdutoV;

/**
 *
 * @author jhonn
 */
public class ProdutoR implements IProdutoR{

    private static ProdutoR instance;

    private IProdutoV viewProduto;

    private ProdutoM[] modelProduto;

    private ProdutoR(IProdutoV produtoV) {
        viewProduto = produtoV;
        modelProduto = new ProdutoM[100];
        init();
    }

    public static ProdutoR getInstance(IProdutoV produtoV) {
        if (instance == null) {
            instance = new ProdutoR(produtoV);
            return instance;
        } else {
            return instance;
        }
    }

    // gera uma cópia do vetor de produtos
    @Override
    public ProdutoM[] getModelProduto() {
        ProdutoM[] aux = new ProdutoM[this.modelProduto.length];
        int i = 0;
        for (ProdutoM prod : modelProduto) {
            aux[i] = ProdutoM.getInstance(prod);
            i += 1;
        }
        return aux;
    }

    // insere alguns produtos no vetor
    @Override
    public void init() {
        // PRODUTOS

        // Arroz
        modelProduto[0] = ProdutoM.getInstance("ARROZ", "REI ARTHUR", 21.30, 750);

        // Feijao
        modelProduto[1] = ProdutoM.getInstance("FEIJAO", "IDEAL", 3.50, 500);

        // Oleo
        modelProduto[2] = ProdutoM.getInstance("OLEO", "CAMARADA", 3.80, 800);

        // Acucar
        modelProduto[3] = ProdutoM.getInstance("ACUCAR", "UNIAO", 5.89, 650);

        // Pó de café
        modelProduto[4] = ProdutoM.getInstance("PO DE CAFE", "3 CORACOES", 3.89, 457);

    }

    // busca por nome ou codigo
    @Override
    public ProdutoM buscar() throws OpcaoInseridaInvalidaException{

        ProdutoM buscar = null;
        // pergunta o tipo de busca
        String sbla = viewProduto.selecionarBusca();
        int ibla = Integer.valueOf(sbla);

        switch (ibla) {
            case 1: {
                buscar = buscarPorNome();
                break;
            }

            case 2: {
                buscar = buscarPorCodigo();
                break;
            }

            default: {
                throw new OpcaoInseridaInvalidaException("A opcao corresponde a nenhuma acao");
            }
        }

        return buscar;
    }

    @Override
    public ProdutoM buscarPorNome() {
        ProdutoM buscar = null;

        String aux = viewProduto.nomeProduto();

        for (int j = 0; j < modelProduto.length; j++) {
            if (modelProduto[j] != null) {
                if (aux.equalsIgnoreCase(modelProduto[j].getNome())) {
                    buscar = ProdutoM.getInstance(modelProduto[j]);
                    break;
                }
            } else if (j == modelProduto.length - 1) {
                viewProduto.mensagemNaoEncontrado();
            }
        }

        return buscar;
    }

    @Override
    public ProdutoM buscarPorCodigo() {
        ProdutoM buscar = null;

        String saux = viewProduto.codigoProduto();
        int iaux = Integer.valueOf(saux);

        for (int j = 0; j < modelProduto.length; j++) {
            if (modelProduto[j] != null) {
                if (modelProduto[j].getCodigoProduto() == iaux) {
                    buscar = ProdutoM.getInstance(modelProduto[j]);
                    break;
                }
            } else if (j == modelProduto.length - 1) {
                viewProduto.mensagemNaoEncontrado();
            }

        }
        return buscar;
    }

    @Override
    public ProdutoM buscarPorCodigo(int codigo) {
        
        ProdutoM buscar = null;

        for (int j = 0; j < modelProduto.length; j++) {
            if (modelProduto[j] != null) {
                if (modelProduto[j].getCodigoProduto() == codigo) {
                    buscar = ProdutoM.getInstance(modelProduto[j]);
                    break;
                }
            }

        }
        return buscar;
    }

    // inseri produtos novos
    @Override
    public void inserirProduto() throws RepositorioCheioException, HaviaProdutoComMesmoDadoException, 
            QuantidadeEstocadaInvalidaException{

        int p = 0;

        // procura por um lugar vazio no vetor de Produtos
        for (int i = 0; i < modelProduto.length; i++) {
            if (modelProduto[i] == null) {
                p = i;
                break;
            } else if (i == modelProduto.length - 1) {
                throw new RepositorioCheioException("Nao ha mais espaço para alocar produtos");
            }
        }

        modelProduto[p] = ProdutoM.getInstance();

        boolean entrei = false;
        boolean temCodigoIgual = false;

        // procura se tem modelProduto com o mesmo codigo
        for (int i = 0; i < modelProduto.length; i++) {
            if (buscarPorCodigo(modelProduto[p].getCodigoProduto()) != null) {
                temCodigoIgual = true;
                break;
            }
        }

        // procura um codigo q nn foi usado
        if (temCodigoIgual) {
            for (int codigo = 1; codigo < modelProduto.length + 1; codigo++) {
                for (int j = 0; j < modelProduto.length; j++) {
                    if (modelProduto[j] != null) {
                        if (modelProduto[j].getCodigoProduto() == codigo) {
                            break;
                        }
                    }
                    if (j == modelProduto.length - 1) {
                        modelProduto[p].setCodigoProduto(codigo);
                        entrei = true;
                        break;
                    }
                }
                if (entrei) {
                    break;
                }
            }
        }

        viewProduto.iniciandoInsercao();
        viewProduto.imprimeCodigoInserção(modelProduto[p].getCodigoProduto());

        // INSERI NOME E VERIFICA SE O NOME JÁ FOI USADO
        modelProduto[p].setNome(viewProduto.nomeProduto());
        for (int j = 0; j < modelProduto.length; j++) {
            if (this.modelProduto[j] != null) {
                if ((modelProduto[p].getNome().equalsIgnoreCase(modelProduto[j].getNome())) && (p != j)) {
                    viewProduto.operacaoImpossível();
                    throw new HaviaProdutoComMesmoDadoException("Havia um produto como o mesmo dado");
                }
            }
        }

        modelProduto[p].setMarca(viewProduto.marcaProduto());

        String aux = viewProduto.precoProduto();
        modelProduto[p].setPreco(Double.valueOf(aux));

        aux = viewProduto.qntEstoqueProduto();
        if (Double.valueOf(aux) < 0) {
            viewProduto.operacaoImpossível();
            throw new QuantidadeEstocadaInvalidaException("Quantidade em estoque invalida");
        }
        modelProduto[p].setQnt_estoque(Double.valueOf(aux));
    }

    // troca dois produtos de posição
    private void inserir(int codigoOrigem, int codigoDestino) {
        int indiceOrigem = 0, indiceDestino = 0;
        boolean origem = false;
        boolean destino = false;
        // encontra o indice do produto origem e destino
        for (int i = 0; i < modelProduto.length; i++) {
            if (modelProduto[i] != null) {
                if (modelProduto[i].getCodigoProduto() == codigoOrigem) {
                    indiceOrigem = i;
                    origem = !origem;
                } else if (modelProduto[i].getCodigoProduto() == codigoDestino) {
                    indiceDestino = i;
                    destino = !destino;
                }
            }
        }
        // caso produto origem e destino existam
        if (origem && destino) {
            ProdutoM aux = modelProduto[indiceOrigem];
            modelProduto[indiceOrigem] = modelProduto[indiceDestino];
            modelProduto[indiceDestino] = aux;
        } else if (origem && !destino) {
            modelProduto[indiceOrigem].setCodigoProduto(codigoDestino);
        }

    }

    // exclui produtos
    @Override
    public void excluirProduto() {
        viewProduto.iniciandoExclusao();
        int aux = Integer.valueOf(viewProduto.codigoProduto());
        viewProduto.caracteristicasDoProd(buscarPorCodigo(aux));

        for (int i = 0; i < modelProduto.length; i++) {
            if (modelProduto[i] != null) {
                if (modelProduto[i].getCodigoProduto() == aux) {
                    if (modelProduto[i].getHaVendas()) {
                        modelProduto[i].setListavel(false);
                    } else {
                        modelProduto[i] = null;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public void excluirProduto(int codigo) {
        
        for (int i = 0; i < modelProduto.length; i++) {
            if (modelProduto[i] != null) {
                if (modelProduto[i].getCodigoProduto() == codigo) {
                    if (modelProduto[i].getHaVendas()) {
                        modelProduto[i].setListavel(false);
                    } else {
                        modelProduto[i] = null;
                    }
                    break;
                }
            }
        }
    }

    // altera dados de produtos
    @Override
    public void alterar() throws HaviaProdutoComMesmoDadoException, NaoHaProdutodoComCodigoInseridoException,
            QuantidadeEstocadaInvalidaException, OpcaoInseridaInvalidaException{

        String saux01 = viewProduto.iniciandoAlteração();
        int aux01 = Integer.valueOf(saux01);

        ProdutoM modelproduto = buscarPorCodigo(aux01);
        // verifica se tem produto com o código inserido
        if (modelproduto == null) {
            throw new NaoHaProdutodoComCodigoInseridoException("Nao ha produto com o código Inserido");
        } else {
            viewProduto.caracteristicasDoProd(buscarPorCodigo(aux01));
            String saux02 = viewProduto.SelecionarDado();
            int aux02 = Integer.valueOf(saux02);

            switch (aux02) {
                case 1:
                    alterarCodigo(aux01);
                    break;
                case 2:
                    String saux03;
                    alterarNome(aux01);
                    break;
                case 3:
                    modelProduto[aux01].setMarca(viewProduto.novaMarca());
                    break;
                case 4:
                    saux02 = viewProduto.novoPreco();
                    modelProduto[aux01].setPreco(Double.valueOf(saux02));
                    break;
                case 5:
                    saux02 = viewProduto.novaQnt_emEstoque();
                    if (Double.valueOf(saux02) < 0) {
                        throw new QuantidadeEstocadaInvalidaException("Quantidade em estoque inserida invalida");
                    } else {
                        modelProduto[aux01].setQnt_estoque(Double.valueOf(saux02));
                    }
                    break;
                default:
                    throw new OpcaoInseridaInvalidaException("Opcao inserida não corresponde a uma acao");
            }

        }
    }

    @Override
    public void alterarCodigo(int codigo) throws HaviaProdutoComMesmoDadoException{

        String saux02 = viewProduto.novoCodigo();
        int novoCodigo = Integer.valueOf(saux02);

        // procura se há produto com codigo novoCodigo
        ProdutoM novomodelProduto = buscarPorCodigo(novoCodigo);

        // decide acão execultada caso novomodelProduto != null
        if (novomodelProduto != null) {
            viewProduto.caracteristicasDoProd(novomodelProduto);
            String saux03 = viewProduto.haProdutoComMesmoCodigo(novoCodigo);
            int aux3 = Integer.valueOf(saux03);
            if (aux3 == 1) {
                // exclui o produto que tinha
                novomodelProduto = null;
                inserir(codigo, novoCodigo);
            } else {
                throw new HaviaProdutoComMesmoDadoException("Há produtos com o mesmo dado");
            }
        } else {
            inserir(codigo, novoCodigo);
        }
    }

    @Override
    public void alterarNome(int codigo) throws HaviaProdutoComMesmoDadoException{
        String saux03 = viewProduto.novoNome();

        int indice = 0;
        boolean existe = false;
        // procura se há produto com nome saux3
        for (int i = 0; i < modelProduto.length; i++) {
            if (modelProduto[i] != null) {
                if (modelProduto[i].getNome().equalsIgnoreCase(saux03)) {
                    indice = i;
                    existe = true;
                    break;
                }
            }
        }

        // caso tenha produto com o mesmo nome
        if (existe) {
            viewProduto.caracteristicasDoProd(modelProduto[indice]);
            String saux04 = viewProduto.haProdutoComMesmoNome(saux03);
            int aux4 = Integer.valueOf(saux04);
            if (aux4 == 1) {
                modelProduto[indice] = null;
                // inseri o novo nome no produto
                for (int i = 0; i < modelProduto.length; i++) {
                    if (modelProduto[i] != null) {
                        if (modelProduto[i].getCodigoProduto() == codigo) {
                            modelProduto[i].setNome(saux03);
                        }
                    }
                }
            } else if (aux4 == 2) {
                throw new HaviaProdutoComMesmoDadoException("Há produtos com o mesmo dado");
            }
        } else {
            // inseri o novo nome no produto
            for (int i = 0; i < modelProduto.length; i++) {
                if (modelProduto[i] != null) {
                    if (modelProduto[i].getCodigoProduto() == codigo) {
                        modelProduto[i].setNome(saux03);
                    }
                }
            }
        }
    }

    // lista produtos
    @Override
    public ProdutoM[] listarProdutosPorCodigo() {

        ProdutoM[] listarPorCodigo = getModelProduto();

        // Apesar desse metodo de ordenação ser mais lento ele vai servir bem aos propositos do programa, 
        // visto q os vetores são relativamente pequenos
        for (int i = 0; i < listarPorCodigo.length; i++) {
            if (listarPorCodigo[i] != null) {
                for (int j = 0; j < listarPorCodigo.length; j++) {
                    if (listarPorCodigo[j] != null) {
                        if (listarPorCodigo[i].getCodigoProduto() < listarPorCodigo[j].getCodigoProduto()) {
                            ProdutoM aux = listarPorCodigo[i];
                            listarPorCodigo[i] = listarPorCodigo[j];
                            listarPorCodigo[j] = aux;
                        }
                    }
                }
            }
        }

        return listarPorCodigo;
    }

    @Override
    public ProdutoM[] listarProdutosPorNome() {

        ProdutoM[] prodListaNome = getModelProduto();
        
        for (int i = 0; i < prodListaNome.length; i++) {
            if (prodListaNome[i] != null) {
                if (prodListaNome[i].getNome() != null) {
                    for (int j = 0; j < prodListaNome.length; j++) {
                        if (prodListaNome[j] != null) {
                            if (prodListaNome[j].getNome() != null) {
                                if (prodListaNome[i].getNome().compareTo(prodListaNome[j].getNome()) < 0) {
                                    ProdutoM aux = prodListaNome[i];
                                    prodListaNome[i] = prodListaNome[j];
                                    prodListaNome[j] = aux;
                                }
                            }
                        }
                    }
                }
            }
        }
        return prodListaNome;
    }

}
