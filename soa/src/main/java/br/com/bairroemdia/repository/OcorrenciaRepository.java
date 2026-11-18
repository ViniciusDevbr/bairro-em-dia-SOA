package br.com.bairroemdia.repository;
import br.com.bairroemdia.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia,Long>, JpaSpecificationExecutor<Ocorrencia> {}