package org.restaurante.dao;

import org.restaurante.model.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PagamentoDAO {

    public void inserirPagamento(Pagamento pagamento){
        String sql = "INSERT INTO pagamento (valor, status_pagamento, id_pedido) VALUES (?, ?, ?)";
        String ultimoPagamento = "SELECT LAST_INSERT_ID()";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, pagamento.getValorPagamento());
            stmt.setString(2, pagamento.getStatusPagamento());
            stmt.setInt(3, pagamento.getIdPedido());
            stmt.executeUpdate();

            PreparedStatement stmtId = conn.prepareStatement(ultimoPagamento);
            ResultSet rs = stmtId.executeQuery();

            if (rs.next()){
                pagamento.setIdPagamento(rs.getInt(1));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o pagamento: " + e.getMessage());
        }
    }

    public ArrayList<Pagamento> listarPagamentos(){
        String sql = "SELECT * FROM pagamento";
        ArrayList<Pagamento> pagamentoLista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPagamento = rs.getInt("id");
                double valorPagamento = rs.getDouble("valor");
                String statusPagamento = rs.getString("status_pagamento");
                int idPedido = rs.getInt("id_pedido");

                Pagamento pagamento = new Pagamento(idPagamento, valorPagamento, statusPagamento, idPedido);
                pagamentoLista.add(pagamento);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os pagamentos: " + e.getMessage());
        }

        return pagamentoLista;
    }

    public void atualizarStatusPagamento(int idPagamento, String statusPagamento){
        String sql = "UPDATE pagamento SET status_pagamento = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, statusPagamento);
            stmt.setInt(2, idPagamento);
            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o pagamento: " + e.getMessage());
        }
    }
}
