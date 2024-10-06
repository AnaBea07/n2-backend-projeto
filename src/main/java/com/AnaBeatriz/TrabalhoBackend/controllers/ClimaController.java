package com.AnaBeatriz.TrabalhoBackend.controllers;


import com.AnaBeatriz.TrabalhoBackend.estadosBrasileiros.EstadosBrasileiros;
import com.AnaBeatriz.TrabalhoBackend.services.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/clima")
public class ClimaController {


    private final ClimaService climaService;

    @Autowired
    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }


    @GetMapping("/previsao")
    public ResponseEntity<String> getPrevisao(
            @RequestParam String estado,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFinal) {

        String previsao = climaService.getPrevisao(estado, dataInicio, dataFinal);
        return ResponseEntity.ok(previsao);
    }
        @PostMapping("/previsao")
        public ResponseEntity<String> postPrevisao(@RequestBody PrevisaoRequest previsaoRequest) {
            String previsao = climaService.getPrevisao(
                    previsaoRequest.getEstado(),
                    previsaoRequest.getDataInicio(),
                    previsaoRequest.getDataFinal()
            );
            return ResponseEntity.ok(previsao);
        }

    @GetMapping("/sobre")
    public ResponseEntity<Map<String, String>> sobre() {
        HashMap<String, String> trabalho = new HashMap<>();
        trabalho.put("estudante", "Ana Beatriz Meller Mendes Silva");
        trabalho.put("projeto", "Serviço de meteorologia");

        return ResponseEntity.ok(trabalho);


    }

}





