package br.com.bairroemdia.repository;
import br.com.bairroemdia.domain.Categoria; import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {}