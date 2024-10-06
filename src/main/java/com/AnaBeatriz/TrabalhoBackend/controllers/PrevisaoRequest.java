package com.AnaBeatriz.TrabalhoBackend.controllers;

import java.time.LocalDate;

public class PrevisaoRequest {

    private String estado;
    private LocalDate dataInicio;
    private LocalDate dataFinal;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
