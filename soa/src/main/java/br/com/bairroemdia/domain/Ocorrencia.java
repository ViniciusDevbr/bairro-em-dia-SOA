package br.com.bairroemdia.domain;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="ocorrencias")
public class Ocorrencia {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=160) private String titulo;
 @Column(columnDefinition="TEXT") private String descricao;
 @Column(nullable=false) private String endereco;
 private Double latitude; private Double longitude;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private StatusOcorrencia status;
 @Column(name="data_criacao",nullable=false) private LocalDateTime dataCriacao;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="bairro_id",nullable=false) private Bairro bairro;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="categoria_id",nullable=false) private Categoria categoria;
 @Column(name="morador_id",nullable=false,length=100) private String moradorId;
 protected Ocorrencia() {}
 public Ocorrencia(String titulo,String descricao,String endereco,Double latitude,Double longitude,Bairro bairro,Categoria categoria,String moradorId){
  this.titulo=titulo;this.descricao=descricao;this.endereco=endereco;this.latitude=latitude;this.longitude=longitude;
  this.bairro=bairro;this.categoria=categoria;this.moradorId=moradorId;this.status=StatusOcorrencia.ABERTA;this.dataCriacao=LocalDateTime.now();
 }
 public void editar(String titulo,String descricao,String endereco){this.titulo=titulo;this.descricao=descricao;this.endereco=endereco;}
 public void atualizarStatus(StatusOcorrencia novo){this.status=novo;}
 public Long getId(){return id;} public String getTitulo(){return titulo;} public String getDescricao(){return descricao;}
 public String getEndereco(){return endereco;} public Double getLatitude(){return latitude;} public Double getLongitude(){return longitude;}
 public StatusOcorrencia getStatus(){return status;} public LocalDateTime getDataCriacao(){return dataCriacao;}
 public Bairro getBairro(){return bairro;} public Categoria getCategoria(){return categoria;} public String getMoradorId(){return moradorId;}
}
