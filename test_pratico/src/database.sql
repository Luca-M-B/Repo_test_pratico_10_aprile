CREATE DATABASE gestionale_vendite;

USE gestionale_vendite;

CREATE TABLE
    utenti (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100),
        tipo VARCHAR(20),
        livello_admin INT
    );

CREATE TABLE prodotti (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    categoria VARCHAR(100),
    prezzo DOUBLE
);

CREATE TABLE vendite (
    id INT AUTO_INCREMENT PRIMARY KEY,
    totale DOUBLE,
    tipo_spedizione VARCHAR(20)
);

CREATE TABLE dettaglio_vendita (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_vendita INT,
    id_prodotto INT,
    quantita INT
);

