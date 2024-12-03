package com.WebAgenda.WebAgenda.Controllers;

import com.WebAgenda.WebAgenda.Model.Situacao;
import com.WebAgenda.WebAgenda.Model.Usuario;
import com.WebAgenda.WebAgenda.Model.Vacina;
import jakarta.persistence.*;

import java.sql.Date;


public class AgendaRequest {
    @Column(nullable = false)
    private Date data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Situacao situacao;

    @Column
    private Date dataSituacao;

    @Column
    private Integer usuario_id;

    @Column
    private Integer vacina_id;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Date getDataSituacao() {
        return dataSituacao;
    }

    public void setDataSituacao(Date dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Integer getVacina_id() {
        return vacina_id;
    }

    public void setVacina_id(Integer vacina_id) {
        this.vacina_id = vacina_id;
    }
}
