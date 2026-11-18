-- Prioridade é calculada pela quantidade de confirmações, sem duplicar dado derivado.
CREATE INDEX idx_confirmacoes_ocorrencia ON confirmacoes(ocorrencia_id);
