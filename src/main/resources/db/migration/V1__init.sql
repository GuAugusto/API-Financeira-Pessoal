-- V1__init.sql
-- Criação inicial das tabelas do banco de dados

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    CONSTRAINT chk_role CHECK (role IN ('ADMIN', 'USER'))
);

-- Tabela de contas financeiras
CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_balance CHECK (balance >= 0)
);

-- Índices para melhor performance
CREATE INDEX idx_username ON users(username);
CREATE INDEX idx_account_name ON accounts(name);
CREATE INDEX idx_created_at ON accounts(created_at);
