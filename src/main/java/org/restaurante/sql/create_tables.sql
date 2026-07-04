CREATE TABLE clientes (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE funcionarios (
    id INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pratos (
    id INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pedidos (
    id INT NOT NULL AUTO_INCREMENT,
    data_hora DATETIME NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    status_pedido VARCHAR(23) NOT NULL,
    id_cliente INT NOT NULL,
    id_funcionario INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_cliente) REFERENCES clientes (id),
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios (id)
);

CREATE TABLE pedido_prato(
    id_pedido INT NOT NULL,
    id_prato INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_pedido, id_prato),
    FOREIGN KEY (id_pedido) REFERENCES pedidos (id),
    FOREIGN KEY (id_prato) REFERENCES pratos (id)
);

CREATE TABLE pagamento(
    id INT NOT NULL AUTO_INCREMENT,
    valor DECIMAL(10, 2) NOT NULL,
    status_pagamento VARCHAR(23) NOT NULL,
    id_pedido INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_pedido) REFERENCES pedidos (id)
);

--os funcionarios e os pratos serao predefinidos, ja que é so pro cliente
INSERT INTO funcionarios (id, nome) VALUES
(1, 'Shaolin'),
(2, 'Ryu'),
(3, 'Carmem Miranda'),
(4, 'Hermione'),
(5, 'Tuba');

INSERT INTO pratos (id, nome, valor) VALUES
(1, 'X-Burguer', 18.90),
(2, 'X-Salada', 21.90),
(3, 'X-Bacon', 24.90),
(4, 'Coxinha', 8.50),
(5, 'Pastel de Frango', 9.00),
(6, 'Batata Frita', 15.00),
(7, 'ALA PK (Hamburguer especial)', 45.00),
(8, 'Pizza de Calabresa', 48.00),
(9, 'Espaguete', 32.00),
(10, 'Feijoada', 42.00),
(11, 'Frango a Parmegiana', 38.00),
(12, 'Salada', 26.00),
(13, 'Lata de Refrigerante', 6.00),
(14, 'Suco', 9.50),
(15, 'Pudim', 12.00);
