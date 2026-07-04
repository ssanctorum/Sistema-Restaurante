package org.restaurante.model;

import java.time.LocalDateTime;

public class Pedido {

    private int idPedido;
    private LocalDateTime dataHoraPedido;
    private double precoPedido;
    private String statusPedido;
    private int idCliente;
    private int idFuncionario;

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getDataHoraPedido() {return dataHoraPedido;}
    public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public double getPrecoPedido() {return precoPedido;}
    public void setPrecoPedido(double precoPedido) {
        this.precoPedido = precoPedido;
    }

    public String getStatusPedido() {return statusPedido;}
    public void setStatusPedido(String statusPedido) {this.statusPedido = statusPedido;}

    public int getIdCliente() {return idCliente;}
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {return idFuncionario;}
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }


    public Pedido(LocalDateTime dataHoraPedido, double precoPedido, String statusPedido, int idCliente, int idFuncionario) {
        this.dataHoraPedido = dataHoraPedido;
        this.precoPedido = precoPedido;
        this.statusPedido = statusPedido;
        this.idCliente = idCliente;
        this.idFuncionario = idFuncionario;
    }
}