package br.com.bairroemdia.repository;
import br.com.bairroemdia.domain.Bairro; import org.springframework.data.jpa.repository.JpaRepository;
public interface BairroRepository extends JpaRepository<Bairro,Long> {}