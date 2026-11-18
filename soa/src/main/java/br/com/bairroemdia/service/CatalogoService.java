package br.com.bairroemdia.service;
import br.com.bairroemdia.domain.*; import br.com.bairroemdia.dto.ApiDtos.*; import br.com.bairroemdia.repository.*; import org.springframework.stereotype.Service; import java.util.*;
@Service
public class CatalogoService {
 private final BairroRepository bairros; private final CategoriaRepository categorias;
 public CatalogoService(BairroRepository b,CategoriaRepository c){bairros=b;categorias=c;}
 public BairroResponse criarBairro(BairroRequest r){Bairro b=bairros.save(new Bairro(r.nome(),r.cidade()));return new BairroResponse(b.getId(),b.getNome(),b.getCidade());}
 public List<BairroResponse> bairros(){return bairros.findAll().stream().map(b->new BairroResponse(b.getId(),b.getNome(),b.getCidade())).toList();}
 public CategoriaResponse criarCategoria(CategoriaRequest r){Categoria c=categorias.save(new Categoria(r.nome().toUpperCase(),r.descricao()));return new CategoriaResponse(c.getId(),c.getNome(),c.getDescricao());}
 public List<CategoriaResponse> categorias(){return categorias.findAll().stream().map(c->new CategoriaResponse(c.getId(),c.getNome(),c.getDescricao())).toList();}
}
