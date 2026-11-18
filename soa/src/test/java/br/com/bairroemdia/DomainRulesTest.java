package br.com.bairroemdia;
import br.com.bairroemdia.domain.*; import br.com.bairroemdia.dto.ApiDtos.*; import br.com.bairroemdia.repository.*; import br.com.bairroemdia.service.OcorrenciaService; import br.com.bairroemdia.exception.ConflictException;
import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.*; import org.mockito.junit.jupiter.MockitoExtension; import static org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class DomainRulesTest {
 @Mock OcorrenciaRepository ocorrencias; @Mock BairroRepository bairros; @Mock CategoriaRepository categorias; @Mock ConfirmacaoRepository confirmacoes; @Mock AtualizacaoRepository atualizacoes;
 @Test void deveRejeitarConfirmacaoDuplicada(){
  OcorrenciaService s=new OcorrenciaService(ocorrencias,bairros,categorias,confirmacoes,atualizacoes);
  Bairro b=new Bairro("Centro","São Paulo"); Categoria c=new Categoria("LIXO",""); Ocorrencia o=new Ocorrencia("Lixo","Descrição","Rua A",null,null,b,c,"m1");
  when(ocorrencias.findById(7L)).thenReturn(java.util.Optional.of(o)); when(confirmacoes.existsByOcorrenciaIdAndMoradorId(7L,"m2")).thenReturn(true);
  assertThrows(ConflictException.class,()->s.confirmar(7L,new ConfirmacaoRequest("m2")));
 }
 @Test void deveRejeitarPuloDeStatus(){
  OcorrenciaService s=new OcorrenciaService(ocorrencias,bairros,categorias,confirmacoes,atualizacoes);
  Bairro b=new Bairro("Centro","São Paulo"); Categoria c=new Categoria("LIXO",""); Ocorrencia o=new Ocorrencia("Lixo","Descrição","Rua A",null,null,b,c,"m1");
  when(ocorrencias.findById(7L)).thenReturn(java.util.Optional.of(o));
  assertThrows(ConflictException.class,()->s.status(7L,new StatusRequest(StatusOcorrencia.EM_ANDAMENTO,"pulo")));
 }
}
