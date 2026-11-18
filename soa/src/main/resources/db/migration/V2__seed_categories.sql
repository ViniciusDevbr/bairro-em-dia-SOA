INSERT INTO categorias(nome,descricao) VALUES
('ILUMINACAO','Iluminação pública apagada ou danificada'),
('LIXO','Descarte irregular ou lixo acumulado'),
('BURACO','Buracos em vias públicas'),
('CALCADA','Problemas de acessibilidade e conservação de calçadas')
ON CONFLICT (nome) DO NOTHING;
INSERT INTO bairros(nome,cidade) VALUES
('Bairro de Demonstração','São Paulo')
ON CONFLICT (nome,cidade) DO NOTHING;
