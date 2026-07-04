package org.restaurante.model;

public class Pagamento {

    private int idPagamento;
    private double valorPagamento;
    private String statusPagamento;
    private int idPedido;

    public int getIdPagamento() {return idPagamento;}
    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public double getValorPagamento() {return valorPagamento;}
    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public String getStatusPagamento() {return statusPagamento;}
    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public int getIdPedido() {return idPedido;}
    public void setIdPedido(int idPedido) {this.idPedido = idPedido;}


    public Pagamento(double valorPagamento, String statusPagamento, int idPedido) {
        this.valorPagamento = valorPagamento;
        this.statusPagamento = statusPagamento;
        this.idPedido = idPedido;
    }

    public Pagamento(int idPagamento, double valorPagamento, String statusPagamento, int idPedido) {
        this.idPagamento = idPagamento;
        this.valorPagamento = valorPagamento;
        this.statusPagamento = statusPagamento;
        this.idPedido = idPedido;
    }
}