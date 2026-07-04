package org.restaurante.dao;

import org.restaurante.model.ItemPedido;
import org.restaurante.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class PedidoDAO {

    public void inserirPedido(Pedido pedido){
        String sql = "INSERT INTO pedidos (data_hora, preco, status_pedido, id_cliente, id_funcionario) VALUES (?, ?, ?, ?, ?)";
        String ultimoPedido = "SELECT LAST_INSERT_ID()";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(pedido.getDataHoraPedido()));
            stmt.setDouble(2, pedido.getPrecoPedido());
            stmt.setString(3, pedido.getStatusPedido());
            stmt.setInt(4, pedido.getIdCliente());
            stmt.setInt(5, pedido.getIdFuncionario());
            stmt.executeUpdate();

            PreparedStatement stmtId = conn.prepareStatement(ultimoPedido);
            ResultSet rs = stmtId.executeQuery();

            if (rs.next()){
                pedido.setIdPedido(rs.getInt(1));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o pedido: " + e.getMessage());
        }
    }

    public void atualizarStatusPedido(int idPedido, String statusPedido){
        String sql = "UPDATE pedidos SET status_pedido = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, statusPedido);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o pedido: " + e.getMessage());
        }
    }

    public void deletarPedido(int idPedido){
        String sqlItens = "DELETE FROM pedido_prato WHERE id_pedido = ?";
        String sqlPagamento = "DELETE FROM pagamento WHERE id_pedido = ?";
        String sqlPedido = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = Conexao.conectar()) {

            PreparedStatement stmtItens = conn.prepareStatement(sqlItens);
            stmtItens.setInt(1, idPedido);
            stmtItens.executeUpdate();

            PreparedStatement stmtPagamento = conn.prepareStatement(sqlPagamento);
            stmtPagamento.setInt(1, idPedido);
            stmtPagamento.executeUpdate();

            PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido);
            stmtPedido.setInt(1, idPedido);
            stmtPedido.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o pedido: " + e.getMessage());
        }
    }

    public void inserirItemPedido(ItemPedido item){
        String sql = "INSERT INTO pedido_prato (id_pedido, id_prato, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getIdPedido());
            stmt.setInt(2, item.getIdPrato());
            stmt.setInt(3, item.getQuantidade());
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o item do pedido: " + e.getMessage());
        }
    }
}
