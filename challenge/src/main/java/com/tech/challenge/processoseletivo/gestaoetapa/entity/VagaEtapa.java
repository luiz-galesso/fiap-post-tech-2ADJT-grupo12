package com.tech.challenge.processoseletivo.gestaoetapa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vaga_etapa")
public class VagaEtapa {


   @EmbeddedId
   private VagaEtapaID vagaEtapaID;

   public VagaEtapa(){}
   public VagaEtapa(VagaEtapaID vagaEtapaID) {
       this.vagaEtapaID = vagaEtapaID;
   }

   public VagaEtapaID getEtapaVagaID() {
        return vagaEtapaID;
    }

   public void setEtapaVagaID(VagaEtapaID vagaEtapaID) {
        this.vagaEtapaID = vagaEtapaID;
    }
}
