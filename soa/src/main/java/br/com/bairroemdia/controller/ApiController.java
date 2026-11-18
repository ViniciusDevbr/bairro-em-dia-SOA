package br.com.bairroemdia.controller;
import br.com.bairroemdia.domain.*; import br.com.bairroemdia.dto.ApiDtos.*; import br.com.bairroemdia.service.*; import br.com.bairroemdia.repository.OcorrenciaRepository; import br.com.bairroemdia.exception.NotFoundException;
import jakarta.validation.Valid; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import java.util.*;
import io.swagger.v3.oas.annotations.Operation;
@RestController @RequestMapping("/api")
public class ApiController {
 private final CatalogoService catalogo; private final OcorrenciaService service; private final OcorrenciaRepository repo; private final MlClient ml;
 public ApiController(CatalogoService c,OcorrenciaService s,OcorrenciaRepository r,MlClient m){catalogo=c;service=s;repo=r;ml=m;}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","service","bairro-em-dia-soa");}
 @GetMapping("/bairros") public List<BairroResponse> bairros(){return catalogo.bairros();}
 @PostMapping("/bairros") @ResponseStatus(HttpStatus.CREATED) public BairroResponse bairro(@Valid @RequestBody BairroRequest r){return catalogo.criarBairro(r);}
 @GetMapping("/categorias") public List<CategoriaResponse> categorias(){return catalogo.categorias();}
 @PostMapping("/categorias") @ResponseStatus(HttpStatus.CREATED) public CategoriaResponse categoria(@Valid @RequestBody CategoriaRequest r){return catalogo.criarCategoria(r);}
 @GetMapping("/ocorrencias") @Operation(summary="Lista ocorrências com filtros opcionais") public List<OcorrenciaResponse> listar(@RequestParam(required=false) Long bairroId,@RequestParam(required=false) StatusOcorrencia status,@RequestParam(required=false) Long categoriaId){return service.listar(bairroId,status,categoriaId);}
 @PostMapping("/ocorrencias") @ResponseStatus(HttpStatus.CREATED) public OcorrenciaResponse criar(@Valid @RequestBody OcorrenciaRequest r){return service.criar(r);}
 @GetMapping("/ocorrencias/{id}") public OcorrenciaResponse buscar(@PathVariable Long id){return service.buscar(id);}
 @PutMapping("/ocorrencias/{id}") public OcorrenciaResponse editar(@PathVariable Long id,@Valid @RequestBody EditarOcorrenciaRequest r){return service.editar(id,r);}
 @DeleteMapping("/ocorrencias/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void excluir(@PathVariable Long id){service.excluir(id);}
 @PatchMapping("/ocorrencias/{id}/status") public OcorrenciaResponse status(@PathVariable Long id,@Valid @RequestBody StatusRequest r){return service.status(id,r);}
 @PostMapping("/ocorrencias/{id}/confirmacoes") @ResponseStatus(HttpStatus.CREATED) public Map<String,String> confirmar(@PathVariable Long id,@Valid @RequestBody ConfirmacaoRequest r){service.confirmar(id,r);return Map.of("mensagem","Confirmação registrada");}
 @GetMapping("/ocorrencias/{id}/atualizacoes") public List<AtualizacaoResponse> historico(@PathVariable Long id){return service.historico(id);}
 @GetMapping("/ocorrencias/{id}/previsao") public PrevisaoResponse previsao(@PathVariable Long id){Ocorrencia o=repo.findById(id).orElseThrow(()->new NotFoundException("Ocorrência não encontrada: "+id));return ml.prever(o);}
}
