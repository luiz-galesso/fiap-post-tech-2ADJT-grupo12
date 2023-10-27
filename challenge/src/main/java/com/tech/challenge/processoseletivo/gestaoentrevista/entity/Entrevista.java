package com.tech.challenge.processoseletivo.gestaoentrevista.entity;

import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;


@Entity
@Table(name = "tb_entrevista")
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataEntrevista;

    @NotBlank(message = "")
    private String local;

    @OneToOne
    @NotBlank(message = "")
    private Usuario entrevistador;

    public Entrevista() {
    }

    public Entrevista(Long id, Date dataEntrevista, String local, Usuario entrevistador) {
        this.id = id;
        this.dataEntrevista = dataEntrevista;
        this.local = local;
        this.entrevistador = entrevistador;
    }

    public Long getId() {
        return id;
    }

    public Usuario getEntrevistador() {
        return entrevistador;
    }

    public void setEntrevistador(Usuario entrevistador) {
        this.entrevistador = entrevistador;
    }

    public Date getDataEntrevista() {
        return dataEntrevista;
    }

    public void setDataEntrevista(Date dataEntrevista) {
        this.dataEntrevista = dataEntrevista;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
