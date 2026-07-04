package org.restaurante.controller;

import org.restaurante.dao.*;
import org.restaurante.model.*;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Metodos {

    ClienteDAO clienteDAO = new ClienteDAO();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    PagamentoDAO pagamentoDAO = new PagamentoDAO();
    PedidoDAO pedidoDAO = new PedidoDAO();
    PratoDAO pratoDAO = new PratoDAO();

    public Cliente cadastrarCliente(String nomeCliente){
        Cliente cliente = new Cliente(nomeCliente);
        clienteDAO.insertCliente(cliente);
        return cliente;
    }

    public void mostrarPratos(){
        ArrayList<Prato> listaPratos = pratoDAO.listarPratos();
        String cardapio = "Cardapio:\n\n";

        for (int i = 0; i < listaPratos.size(); i++){
            Prato prato = listaPratos.get(i);
            cardapio = cardapio + prato.getIdPrato() + " - " + prato.getNomePrato() + " - R$ " + prato.getValorPrato() + "\n";
        }

        JOptionPane.showMessageDialog(null, cardapio, "RESTAURANTE DO NALDO", JOptionPane.PLAIN_MESSAGE);
    }

    public ArrayList<ItemPedido> montarItensPedido(){
        ArrayList<ItemPedido> itens = new ArrayList<>();
        int idPratoEscolhido = -1;

        while (idPratoEscolhido != 0){
            try {
                String entrada = JOptionPane.showInputDialog(null, "Digite o ID do prato que deseja pedir (Caso tenha terminado, 0 para finalizar):", "RESTAURANTE DO NALDO", JOptionPane.PLAIN_MESSAGE);

                if (entrada == null){
                    break;
                }

                if (entrada.isBlank()){
                    JOptionPane.showMessageDialog(null, "Nenhum prato selecionado. Seu item não foi inserido.","RESTAURANTE DO NALDO", JOptionPane.INFORMATION_MESSAGE);
                    continue;
                }


                idPratoEscolhido = Integer.parseInt(entrada.trim());

                if (idPratoEscolhido != 0){
                    Prato prato = pratoDAO.buscarPratoPorId(idPratoEscolhido);

                    if (prato == null){
                        JOptionPane.showMessageDialog(null, "Prato nao encontrado!", "RESTAURANTE DO NALDO", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String entradaQtd = JOptionPane.showInputDialog(null, "Quantidade de " + prato.getNomePrato() + ":", "RESTAURANTE DO NALDO", JOptionPane.PLAIN_MESSAGE);
                        if (entradaQtd == null) continue;
                        if (entradaQtd.isBlank()){
                            JOptionPane.showMessageDialog(null, "Nenhuma quantidade selecionada. Seu item não foi inserido.","RESTAURANTE DO NALDO", JOptionPane.INFORMATION_MESSAGE);
                            continue;
                        }
                        int quantidade = Integer.parseInt(entradaQtd.trim());
                        if (quantidade < 1){ JOptionPane.showMessageDialog(null,"Insira um valor maior ou igual a 1!", "RESTAURANTE DO NALDO", JOptionPane.INFORMATION_MESSAGE); continue;}

                        int i;
                        for (i = 0; i < itens.size(); i++){
                            if (itens.get(i).getIdPrato() == idPratoEscolhido){
                                break;
                            }
                        }

                        if (i == itens.size()) {
                            ItemPedido item = new ItemPedido(0, idPratoEscolhido, quantidade);
                            itens.add(item);
                        } else {
                            itens.get(i).setQuantidade(itens.get(i).getQuantidade() + quantidade);
                        }
                    }
                 }
             } catch (Exception e){
                JOptionPane.showMessageDialog(null,"Ocorreu um erro no sistema, seu item não foi inserido: " + e.getMessage(),"ERRO",JOptionPane.ERROR_MESSAGE);
            }
        }

        return itens;
    }

    public double calcularTotal(ArrayList<ItemPedido> itens){
        double total = 0;

        for (int i = 0; i < itens.size(); i++){
            ItemPedido item = itens.get(i);
            Prato prato = pratoDAO.buscarPratoPorId(item.getIdPrato());
            total = total + (prato.getValorPrato() * item.getQuantidade());
        }

        return total;
    }

    public Pedido fecharPedido(Cliente cliente, ArrayList<ItemPedido> itens){
        double total = calcularTotal(itens);
        Funcionario funcionario = funcionarioDAO.sortearFuncionario();

        Pedido pedido = new Pedido(LocalDateTime.now(), total, "Em preparacao...", cliente.getIdCliente(), funcionario.getMatriculaFuncionario());
        pedidoDAO.inserirPedido(pedido);

        for (int i = 0; i < itens.size(); i++){
            ItemPedido item = itens.get(i);
            item.setIdPedido(pedido.getIdPedido());
            pedidoDAO.inserirItemPedido(item);
        }

        Pagamento pagamento = new Pagamento(total, "Aguardando pagamento...", pedido.getIdPedido());
        pagamentoDAO.inserirPagamento(pagamento);

        String resumo = "Pedido número: " + pedido.getIdPedido() + "\n";
        resumo = resumo + "Atendido por: " + funcionario.getNomeFuncionario() + "\n";
        resumo = resumo + "Total: R$ " + String.format("%.2f", total) + "\n";
        resumo = resumo + "Status do pagamento: " + pagamento.getStatusPagamento();

        JOptionPane.showMessageDialog(null, resumo, "RESTAURANTE DO NALDO", JOptionPane.PLAIN_MESSAGE);

        return pedido;
    }

    public void confirmarPagamento(Pedido pedido){
        ArrayList<Pagamento> pagamentos = pagamentoDAO.listarPagamentos();
        Pagamento pagamentoDoPedido = null;

        for (int i = 0; i < pagamentos.size(); i++){
            Pagamento pagamento = pagamentos.get(i);
            if (pagamento.getIdPedido() == pedido.getIdPedido()){
                pagamentoDoPedido = pagamento;
            }
        }

        if (pagamentoDoPedido != null){
            pagamentoDAO.atualizarStatusPagamento(pagamentoDoPedido.getIdPagamento(), "Pagamento concluido!");
            pedidoDAO.atualizarStatusPedido(pedido.getIdPedido(), "Concluido!");
            JOptionPane.showMessageDialog(null, "Pagamento confirmado! Obrigado pela preferencia, volte sempre!", "RESTAURANTE DO NALDO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void cancelarPedido(Pedido pedido){
        pedidoDAO.deletarPedido(pedido.getIdPedido());
        JOptionPane.showMessageDialog(null, "Pedido cancelado.", "RESTAURANTE DO NALDO", JOptionPane.INFORMATION_MESSAGE);
    }
}
