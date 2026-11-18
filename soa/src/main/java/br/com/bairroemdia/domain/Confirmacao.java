package br.com.bairroemdia.domain;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="confirmacoes",uniqueConstraints=@UniqueConstraint(columnNames={"ocorrencia_id","morador_id"}))
public class Confirmacao {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="ocorrencia_id",nullable=false) private Ocorrencia ocorrencia;
 @Column(name="morador_id",nullable=false) private String moradorId;
 @Column(name="data_confirmacao",nullable=false) private LocalDateTime dataConfirmacao;
 protected Confirmacao(){}
 public Confirmacao(Ocorrencia o,String m){ocorrencia=o;moradorId=m;dataConfirmacao=LocalDateTime.now();}
}
