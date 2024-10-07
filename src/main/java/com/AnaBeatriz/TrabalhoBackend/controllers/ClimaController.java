package com.AnaBeatriz.TrabalhoBackend.controllers;


import com.AnaBeatriz.TrabalhoBackend.services.ClimaService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
            @RequestParam LocalDate data) {

        String previsao = climaService.getPrevisao(estado, data);
        return ResponseEntity.ok(previsao);
    }

    @PostMapping("/relatorio")
    public ResponseEntity<String> gerarRelatorio(@RequestBody PrevisaoRequest previsaoRequest) {
        try {
            String relatorio = climaService.gerarRelatorio(
                    previsaoRequest.getEstado(),
                    previsaoRequest.getData()
            );
            return ResponseEntity.ok(relatorio);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (JSONException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o JSON: " + e.getMessage());
        }
    }

    @GetMapping("/sobre")
    public ResponseEntity<Map<String, String>> sobre() {
        HashMap<String, String> trabalho = new HashMap<>();
        trabalho.put("estudante", "Ana Beatriz Meller Mendes Silva");
        trabalho.put("projeto", "Serviço de meteorologia");

        return ResponseEntity.ok(trabalho);


    }

}





