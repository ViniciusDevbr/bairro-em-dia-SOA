package br.com.bairroemdia.repository;
import br.com.bairroemdia.domain.Atualizacao; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface AtualizacaoRepository extends JpaRepository<Atualizacao,Long> { List<Atualizacao> findByOcorrenciaIdOrderByDataAtualizacaoAsc(Long id); }