package br.com.bairroemdia.domain;
import jakarta.persistence.*;
@Entity @Table(name="categorias")
public class Categoria {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=80) private String nome;
 @Column(length=255) private String descricao;
 protected Categoria() {}
 public Categoria(String nome,String descricao){this.nome=nome;this.descricao=descricao;}
 public Long getId(){return id;} public String getNome(){return nome;} public String getDescricao(){return descricao;}
}
