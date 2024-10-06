package com.AnaBeatriz.TrabalhoBackend.services;

import com.AnaBeatriz.TrabalhoBackend.estadosBrasileiros.EstadosBrasileiros;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ClimaService {

    private final RestTemplate restTemplate;

    @Autowired
    public ClimaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getPrevisao(String estado, LocalDate dataInicio, LocalDate dataFinal) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataInicioStr = dataInicio.format(formatter);
        String dataFinalStr = dataFinal.format(formatter);
        double[] coordenadas = EstadosBrasileiros.getCoordenadas(estado);
        if (coordenadas == null) {
            throw new IllegalArgumentException("Estado inválido");
        }

        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&start_date=%s&end_date=%s&hourly=temperature_2m",
                coordenadas[0], coordenadas[1], dataInicioStr, dataFinalStr

        );

        String jsonResponse = restTemplate.getForObject(url, String.class);
        return converterPrevisaoParaMensagem(jsonResponse);
    }

    private String converterPrevisaoParaMensagem(String jsonPrevisao) {

        JSONObject previsao = new JSONObject(jsonPrevisao);
        JSONObject hourly = previsao.getJSONObject("hourly");
        double temperaturaMin = hourly.getJSONArray("temperature_2m").getDouble(0);
        double temperaturaMax = hourly.getJSONArray("temperature_2m").getDouble(23);
        double chanceDeChuva = hourly.getJSONArray("precipitation").getDouble(0);
        double umidade = hourly.getJSONArray("humidity_2m").getDouble(0);
        double velocidadeVento = hourly.getJSONArray("windspeed_10m").getDouble(0);

        String mensagem = String.format(
                "A previsão para o estado selecionado indica temperaturas entre %.1f°C e %.1f°C, " +
                        "com umidade de %.1f%%, velocidade do vento de %.1f m/s e uma chance de chuva de %.1f mm.",
                temperaturaMin, temperaturaMax, umidade, velocidadeVento, chanceDeChuva
        );

        return mensagem;
    }
}
