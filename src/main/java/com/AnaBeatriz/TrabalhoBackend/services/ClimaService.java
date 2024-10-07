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

    public String getPrevisao(String estado, LocalDate data) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataStr = data.format(formatter);
        double[] coordenadas = EstadosBrasileiros.getCoordenadas(estado);
        if (coordenadas == null) {
            throw new IllegalArgumentException("Estado inválido");
        }

        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&start_date=%s&end_date=%s&daily=temperature_2m_max,temperature_2m_min",
                coordenadas[0], coordenadas[1], dataStr, dataStr

        );

        String jsonResponse = restTemplate.getForObject(url, String.class);
        return converterPrevisaoParaMensagem(jsonResponse);
    }

    public String gerarRelatorio(String estado, LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataStr = data.format(formatter);
        double[] coordenadas = EstadosBrasileiros.getCoordenadas(estado);
        if (coordenadas == null) {
            throw new IllegalArgumentException("Estado inválido");
        }

        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&start_date=%s&end_date=%s&daily=temperature_2m_max,temperature_2m_min,weather_code,sunrise,sunset,uv_index_max,precipitation_sum,precipitation_hours,precipitation_probability_max",
                coordenadas[0], coordenadas[1], dataStr, dataStr


        );

        String jsonRelatorio = restTemplate.getForObject(url, String.class);
        return converterRelatorioParaMensagem(jsonRelatorio);

    }


    private String traduzirWeatherCode(int weatherCode) {
        switch (weatherCode) {
            case 0:
                return "Céu limpo";
            case 1:
                return "Principalmente claro";
            case 2:
                return "Parcialmente nublado";
            case 3:
                return "Nublado";
            case 45:
            case 48:
                return "Neblina e neblina com deposição de gelo";
            case 51:
                return "Chuva leve";
            case 53:
                return "Chuva moderada";
            case 55:
                return "Chuva densa";
            case 56:
                return "Chuva leve congelante";
            case 57:
                return "Chuva densa congelante";
            case 61:
                return "Chuva leve";
            case 63:
                return "Chuva moderada";
            case 65:
                return "Chuva forte";
            case 66:
                return "Chuva leve congelante";
            case 67:
                return "Chuva forte congelante";
            case 71:
                return "Neve leve";
            case 73:
                return "Neve moderada";
            case 75:
                return "Neve forte";
            case 77:
                return "Grãos de neve";
            case 80:
                return "Chuva leve";
            case 81:
                return "Chuva moderada";
            case 82:
                return "Chuva forte";
            case 85:
                return "Neve leve";
            case 86:
                return "Neve forte";
            case 95:
                return "Tempestade: leve ou moderada";
            case 96:
                return "Tempestade com granizo leve";
            case 99:
                return "Tempestade com granizo forte";
            default:
                return "Código do tempo desconhecido";
        }
    }

    private String converterPrevisaoParaMensagem(String jsonPrevisao) {

        JSONObject previsao = new JSONObject(jsonPrevisao);
        JSONObject daily = previsao.getJSONObject("daily");
        double temperaturaMin = daily.getJSONArray("temperature_2m_min").getDouble(0);
        double temperaturaMax = daily.getJSONArray("temperature_2m_max").getDouble(0);


        String mensagem = String.format(
                "A previsão para o estado selecionado indica temperaturas entre %.1f°C e %.1f°C, ",
                temperaturaMin, temperaturaMax
        );

        return mensagem;
    }

    public String converterRelatorioParaMensagem(String jsonRelatorio) {
        JSONObject relatorio = new JSONObject(jsonRelatorio);
        JSONObject daily = relatorio.getJSONObject("daily");
        double temperaturaMax = daily.getJSONArray("temperature_2m_max").getDouble(0);
        double temperaturaMin = daily.getJSONArray("temperature_2m_min").getDouble(0);
        int weatherCode = daily.getJSONArray("weather_code").getInt(0);
        String weatherDescription = traduzirWeatherCode(weatherCode);
        String sunrise = daily.getJSONArray("sunrise").getString(0);
        String sunset = daily.getJSONArray("sunset").getString(0);
        double uvIndexMax = daily.getJSONArray("uv_index_max").getDouble(0);
        double precipitationSum = daily.getJSONArray("precipitation_sum").getDouble(0);
        double precipitationHours = daily.getJSONArray("precipitation_hours").getDouble(0);
        double precipitationProbabilityMax = daily.getJSONArray("precipitation_probability_max").getDouble(0);

        String mensagemRelatorio = String.format(
                "Relatório Climático:\n" +
                        "Temperatura máxima: %.1f°C\n" +
                        "Temperatura minima: %.1f°C\n" +
                        "Condição: %s\n" +
                        "Nascer do sol: %s\n" +
                        "Pôr do sol: %s\n" +
                        "Indice max UV: %.1f\n" +
                        "Soma da precipitação: %.1f mm\n" +
                        "Horas de precipitação: %.1f\n" +
                        "Probabilidade máxima de precipitação: %.1f%%",
                temperaturaMin, temperaturaMax, weatherDescription, sunrise, sunset, uvIndexMax, precipitationSum, precipitationHours, precipitationProbabilityMax
        );

        return mensagemRelatorio;
    }
}
