package br.com.bairroemdia.domain;
import jakarta.persistence.*;
@Entity @Table(name="bairros", uniqueConstraints=@UniqueConstraint(columnNames={"nome","cidade"}))
public class Bairro {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String nome;
 @Column(nullable=false,length=120) private String cidade;
 protected Bairro() {}
 public Bairro(String nome,String cidade){this.nome=nome;this.cidade=cidade;}
 public Long getId(){return id;} public String getNome(){return nome;} public String getCidade(){return cidade;}
}
