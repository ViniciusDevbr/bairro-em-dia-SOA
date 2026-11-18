package br.com.bairroemdia.service;
import br.com.bairroemdia.domain.*; import br.com.bairroemdia.dto.ApiDtos.*; import br.com.bairroemdia.exception.*; import br.com.bairroemdia.repository.*;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service
public class OcorrenciaService {
 private final OcorrenciaRepository ocorrencias; private final BairroRepository bairros; private final CategoriaRepository categorias;
 private final ConfirmacaoRepository confirmacoes; private final AtualizacaoRepository atualizacoes;
 public OcorrenciaService(OcorrenciaRepository o,BairroRepository b,CategoriaRepository c,ConfirmacaoRepository f,AtualizacaoRepository a){ocorrencias=o;bairros=b;categorias=c;confirmacoes=f;atualizacoes=a;}
 private Bairro bairro(Long id){return bairros.findById(id).orElseThrow(()->new NotFoundException("Bairro não encontrado: "+id));}
 private Categoria categoria(Long id){return categorias.findById(id).orElseThrow(()->new NotFoundException("Categoria não encontrada: "+id));}
 private Ocorrencia get(Long id){return ocorrencias.findById(id).orElseThrow(()->new NotFoundException("Ocorrência não encontrada: "+id));}
 private OcorrenciaResponse dto(Ocorrencia o){return new OcorrenciaResponse(o.getId(),o.getTitulo(),o.getDescricao(),o.getEndereco(),o.getLatitude(),o.getLongitude(),o.getStatus(),o.getDataCriacao(),o.getBairro().getId(),o.getBairro().getNome(),o.getCategoria().getId(),o.getCategoria().getNome(),o.getMoradorId(),confirmacoes.countByOcorrenciaId(o.getId()));}
 @Transactional public OcorrenciaResponse criar(OcorrenciaRequest r){Ocorrencia o=ocorrencias.save(new Ocorrencia(r.titulo(),r.descricao(),r.endereco(),r.latitude(),r.longitude(),bairro(r.bairroId()),categoria(r.categoriaId()),r.moradorId()));atualizacoes.save(new Atualizacao(o,StatusOcorrencia.ABERTA,"Ocorrência registrada"));return dto(o);}
 @Transactional(readOnly=true) public OcorrenciaResponse buscar(Long id){return dto(get(id));}
 @Transactional(readOnly=true) public List<OcorrenciaResponse> listar(Long bairroId,StatusOcorrencia status,Long categoriaId){
  return ocorrencias.findAll().stream().filter(o->bairroId==null||o.getBairro().getId().equals(bairroId)).filter(o->status==null||o.getStatus()==status).filter(o->categoriaId==null||o.getCategoria().getId().equals(categoriaId)).map(this::dto).toList();
 }
 @Transactional public OcorrenciaResponse editar(Long id,EditarOcorrenciaRequest r){Ocorrencia o=get(id);if(o.getStatus()==StatusOcorrencia.RESOLVIDA)throw new ConflictException("Ocorrência resolvida não pode ser editada");o.editar(r.titulo(),r.descricao(),r.endereco());return dto(o);}
 @Transactional public void excluir(Long id){Ocorrencia o=get(id);ocorrencias.delete(o);}
 @Transactional public OcorrenciaResponse status(Long id,StatusRequest r){Ocorrencia o=get(id);int atual=o.getStatus().ordinal(),novo=r.status().ordinal();if(novo!=atual+1)throw new ConflictException("Transição inválida. O status deve avançar uma etapa por vez.");o.atualizarStatus(r.status());atualizacoes.save(new Atualizacao(o,r.status(),r.comentario()));return dto(o);}
 @Transactional public void confirmar(Long id,ConfirmacaoRequest r){Ocorrencia o=get(id);if(confirmacoes.existsByOcorrenciaIdAndMoradorId(id,r.moradorId()))throw new ConflictException("Morador já confirmou esta ocorrência");confirmacoes.save(new Confirmacao(o,r.moradorId()));}
 @Transactional(readOnly=true) public List<AtualizacaoResponse> historico(Long id){get(id);return atualizacoes.findByOcorrenciaIdOrderByDataAtualizacaoAsc(id).stream().map(a->new AtualizacaoResponse(a.getId(),a.getStatus(),a.getComentario(),a.getDataAtualizacao())).toList();}
}
