package br.com.bairroemdia.repository;
import br.com.bairroemdia.domain.Confirmacao; import org.springframework.data.jpa.repository.JpaRepository;
public interface ConfirmacaoRepository extends JpaRepository<Confirmacao,Long> { boolean existsByOcorrenciaIdAndMoradorId(Long ocorrenciaId,String moradorId); long countByOcorrenciaId(Long ocorrenciaId); }