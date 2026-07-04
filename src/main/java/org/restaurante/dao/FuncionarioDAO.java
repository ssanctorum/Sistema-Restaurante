package org.restaurante.dao;

import org.restaurante.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class FuncionarioDAO {

    public ArrayList<Funcionario> listarFuncionarios(){
        String sql = "SELECT * FROM funcionarios";
        ArrayList<Funcionario> funcionarioLista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int matriculaFuncionario = rs.getInt("id");
                String nomeFuncionario = rs.getString("nome");

                Funcionario funcionario = new Funcionario(matriculaFuncionario, nomeFuncionario);
                funcionarioLista.add(funcionario);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os funcionarios: " + e.getMessage());
        }

        return funcionarioLista;
    }

    public Funcionario sortearFuncionario(){
        ArrayList<Funcionario> funcionarioLista = listarFuncionarios();

        Random sorteio = new Random();
        int indiceSorteado = sorteio.nextInt(funcionarioLista.size());

        return funcionarioLista.get(indiceSorteado);
    }
}
