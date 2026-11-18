package br.com.bairroemdia.dto;
import jakarta.validation.constraints.*; import br.com.bairroemdia.domain.StatusOcorrencia; import java.time.LocalDateTime;
public class ApiDtos {
 public record BairroRequest(@NotBlank String nome,@NotBlank String cidade){}
 public record CategoriaRequest(@NotBlank String nome,String descricao){}
 public record OcorrenciaRequest(@NotBlank @Size(max=160) String titulo,String descricao,@NotBlank String endereco,Double latitude,Double longitude,@NotNull Long bairroId,@NotNull Long categoriaId,@NotBlank String moradorId){}
 public record EditarOcorrenciaRequest(@NotBlank @Size(max=160) String titulo,String descricao,@NotBlank String endereco){}
 public record StatusRequest(@NotNull StatusOcorrencia status,@Size(max=500) String comentario){}
 public record ConfirmacaoRequest(@NotBlank String moradorId){}
 public record BairroResponse(Long id,String nome,String cidade){}
 public record CategoriaResponse(Long id,String nome,String descricao){}
 public record OcorrenciaResponse(Long id,String titulo,String descricao,String endereco,Double latitude,Double longitude,StatusOcorrencia status,LocalDateTime dataCriacao,Long bairroId,String bairro,Long categoriaId,String categoria,String moradorId,long confirmacoes){}
 public record AtualizacaoResponse(Long id,StatusOcorrencia status,String comentario,LocalDateTime dataAtualizacao){}
 public record PrevisaoResponse(String status,String mensagem,Double probabilidadeAtraso){}
}
