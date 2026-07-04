package org.restaurante.dao;

import org.restaurante.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    public void insertCliente(Cliente cliente){
        String sql = "INSERT INTO clientes (nome) VALUES (?)";
        String ultimoAdd = "SELECT LAST_INSERT_ID()";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNomeCliente());
            stmt.executeUpdate();

            PreparedStatement stmtId = conn.prepareStatement(ultimoAdd);
            ResultSet rs = stmtId.executeQuery();

            if (rs.next()){
                cliente.setIdCliente(rs.getInt(1));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir o cliente: " + e.getMessage());
        }
    }
}
