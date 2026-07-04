package org.restaurante.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = "";
    private static final String USUARIO = "";
    private static final String SENHA = "";

    public static Connection conectar() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
