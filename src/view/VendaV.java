package view;

import java.util.Scanner;
import modelo.ItemM;
import modelo.VendaM;

public class VendaV implements IVendaV{
    
    private static Scanner ler = new Scanner(System.in);
    
    private VendaV() {
    }
    
    public static VendaV getInstace() {
        return new VendaV();
    }
    
    @Override
    public void ImprimirTodasVendas(VendaM[] vendas) {
        for (VendaM vend : vendas) {
            if (vend != null) {
                for (int i = 0; i < 100; i++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.printf("%-9S%-5d%20S%S%n", "Código: ", vend.getCodigo(), "Data ", vend.getFormato().format(vend.getData()));
                System.out.printf("%-20S%-20S%-20S%-20S%n", "Nome", "Preço uni.", "Quantidade", "total");
                for (ItemM itens : vend.getItensVendidos()) {
                    if (itens != null) {
                        System.out.printf("%-20S%-20S%-20.2f%-20.2f%n", itens.getModelProduto().getNome(), itens.getModelProduto().getPreco(), itens.getQuantidade(), itens.getPrecoItem());
                    }
                }

            } else {
                continue;
            }

            for (int i = 0; i < 100; i++) {
                System.out.print("-");
            }
            System.out.println("");
        }
    }
    
    @Override
    public void caracteristicasDaVenda(VendaM venda) {

        if (venda != null) {
            System.out.printf("%-9S%d%20S%S%n", "Código: ", venda.getCodigo(), "Data ", venda.getFormato().format(venda.getData()));
            System.out.printf("%-20S%-20S%-20S%-20S%n", "Nome", "Preço uni.", "Quantidade", "total");
            for (ItemM itens : venda.getItensVendidos()) {
                if (itens != null) {
                    System.out.printf("%-20S%-20S%-20.2f%-20.2f%n", itens.getModelProduto().getNome(), itens.getModelProduto().getPreco(), itens.getQuantidade(), itens.getPrecoItem());
                }
            }
            for (int i = 0; i < 100; i++) {
                System.out.print("-");
            }
            System.out.println("");

        }
    }

    @Override
    public boolean exitoOperacional(boolean operacao) {
        if (operacao) {
            System.out.println("EXITO");
            return operacao;
        } else {
            System.out.println("INEXITO");
            return operacao;
        }
    }

    @Override
    public String insiraData() {
        System.out.println("Insira a data no formato (dd/mm/aaaa): ");
        return ler.nextLine();
    }

    @Override
    public int insiraCodigo() {
        System.out.println("Inisira o codigo: ");
        String aux = ler.nextLine();
        return Integer.valueOf(aux);
    }
    
    @Override
    public String inserirProduto() {
        System.out.println("Insira o codigo do produto q será inserido: ");
        return ler.nextLine();
    }

    @Override
    public String quantidadeProduto() {
        System.out.println("Insira a quantidade do produto q será inserido: ");
        return ler.nextLine();   
    }

    @Override
    public String inserirMaisProdutos() {
        System.out.printf("%S%S", "Deseja adicionar mais um item?", "S/N");
        return ler.nextLine();
    }
}
