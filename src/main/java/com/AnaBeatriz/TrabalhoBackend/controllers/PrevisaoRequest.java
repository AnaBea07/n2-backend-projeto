package com.AnaBeatriz.TrabalhoBackend.controllers;

import java.time.LocalDate;

public class PrevisaoRequest {

    private String estado;
    private LocalDate data;


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

