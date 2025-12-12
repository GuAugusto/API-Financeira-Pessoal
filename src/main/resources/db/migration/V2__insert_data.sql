-- V2__insert_data.sql
-- Inserção de dados iniciais (seed)

-- Inserir contas exemplo
INSERT INTO accounts (name, balance, created_at) VALUES 
('Carteira', 150.00, CURRENT_TIMESTAMP),
('Nubank', 2500.50, CURRENT_TIMESTAMP),
('Itaú', 5000.00, CURRENT_TIMESTAMP),
('Investimentos', 10000.00, CURRENT_TIMESTAMP);
