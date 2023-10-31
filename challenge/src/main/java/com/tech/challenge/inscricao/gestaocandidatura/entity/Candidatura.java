package com.tech.challenge.inscricao.gestaocandidatura.entity;

import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import jakarta.persistence.*;

@Entity
@Table(name="tb_candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="candidatura_generator")
    @SequenceGenerator(name="candidatura_generator", sequenceName="candidatura_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Vaga vaga;

    @ManyToOne
    private Usuario candidato;

    public Candidatura(){
    }

    public Candidatura(Vaga vaga, Usuario usuario){
        setVaga(vaga);
        setUsuario(usuario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vaga getVaga(){
        return this.vaga;
    }

    public void setVaga(Vaga vaga){
        this.vaga = vaga;
    }

    public Usuario getUsuario(){
        return this.candidato;
    }

    public void setUsuario(Usuario candidato){
        this.candidato = candidato;
    }

}
