package org.restaurante.dao;

import org.restaurante.model.Prato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PratoDAO {

    public ArrayList<Prato> listarPratos(){
        String sql = "SELECT * FROM pratos";
        ArrayList<Prato> pratoLista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPrato = rs.getInt("id");
                String nomePrato = rs.getString("nome");
                double valorPrato = rs.getDouble("valor");

                Prato prato = new Prato(idPrato, nomePrato, valorPrato);
                pratoLista.add(prato);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os pratos: " + e.getMessage());
        }

        return pratoLista;
    }

    public Prato buscarPratoPorId(int idPrato){
        String sql = "SELECT * FROM pratos WHERE id = ?";
        Prato prato = null;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPrato);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                String nomePrato = rs.getString("nome");
                double valorPrato = rs.getDouble("valor");
                prato = new Prato(idPrato, nomePrato, valorPrato);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o prato: " + e.getMessage());
        }

        return prato;
    }
}
