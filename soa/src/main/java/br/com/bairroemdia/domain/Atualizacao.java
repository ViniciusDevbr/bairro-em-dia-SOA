package br.com.bairroemdia.domain;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="atualizacoes")
public class Atualizacao {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="ocorrencia_id",nullable=false) private Ocorrencia ocorrencia;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private StatusOcorrencia status;
 @Column(length=500) private String comentario;
 @Column(name="data_atualizacao",nullable=false) private LocalDateTime dataAtualizacao;
 protected Atualizacao(){}
 public Atualizacao(Ocorrencia o,StatusOcorrencia s,String c){ocorrencia=o;status=s;comentario=c;dataAtualizacao=LocalDateTime.now();}
 public Long getId(){return id;} public StatusOcorrencia getStatus(){return status;} public String getComentario(){return comentario;} public LocalDateTime getDataAtualizacao(){return dataAtualizacao;}
}
