package com.tech.challenge.inscricao.gestaocandidatura.entity;

import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import jakarta.persistence.*;
import com.tech.challenge.inscricao.gestaocandidatura.dto.CandidaturaRequestDTO;

@Entity
@Table(name="tb_candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vaga vaga;

    @ManyToOne
    private Usuario candidato;

    private boolean ativo;

    public Candidatura(){
    }

    public Candidatura(Vaga vaga, Usuario usuario){
        setVaga(vaga);
        setUsuario(usuario);
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

    public boolean getAtivo(){
        return this.ativo;
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }


}
