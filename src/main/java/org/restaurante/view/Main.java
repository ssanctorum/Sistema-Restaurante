package org.restaurante.view;

import org.restaurante.controller.Metodos;
import org.restaurante.model.Cliente;
import org.restaurante.model.ItemPedido;
import org.restaurante.model.Pedido;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Metodos metodos = new Metodos();
        int loop = -1;

        do {
            try{
                String nomeCliente = JOptionPane.showInputDialog(null, "Bem vindo ao restaurante do Naldo!\n\nPara comecarmos, insira o seu nome:", "RESTAURANTE DO NALDO", JOptionPane.PLAIN_MESSAGE);

                if (nomeCliente == null){
                    break;
                }

                if (nomeCliente.isBlank()){
                    JOptionPane.showMessageDialog(null,"O nome não pode ficar em branco!", "RESTAURANTE DO NALDO",JOptionPane.INFORMATION_MESSAGE);
                    continue;
                }

                if (nomeCliente.length()>255){
                    JOptionPane.showMessageDialog(null,"Não é permitido mais de 255 caracteres.", "RESTAURANTE DO NALDO",JOptionPane.INFORMATION_MESSAGE);
                    continue;
                }

                Cliente cliente = metodos.cadastrarCliente(nomeCliente);

                metodos.mostrarPratos();

                ArrayList<ItemPedido> itens = metodos.montarItensPedido();

                if (itens.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nenhum prato foi escolhido. Encerrando o sistema.", "RESTAURANTE DO NALDO", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Pedido pedido = metodos.fecharPedido(cliente, itens);

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja confirmar o pagamento do pedido agora?", "RESTAURANTE DO NALDO", JOptionPane.YES_NO_OPTION);

                if (opcao == JOptionPane.YES_OPTION){
                    metodos.confirmarPagamento(pedido);
                } else {
                    metodos.cancelarPedido(pedido);
                }

            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro no sistema: " + e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } while (loop != 0);
    }
}
