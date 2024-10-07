package com.AnaBeatriz.TrabalhoBackend.estadosBrasileiros;


import java.util.HashMap;
import java.util.Map;

public class EstadosBrasileiros {

    private static final Map<String, double[]> estadoMap = new HashMap<>();

    static {
        estadoMap.put("AC", new double[]{-9.974, -67.807});  // Acre
        estadoMap.put("AL", new double[]{-9.5713, -36.782}); // Alagoas
        estadoMap.put("AM", new double[]{-3.4168, -65.8561}); // Amazonas
        estadoMap.put("BA", new double[]{-12.9714, -38.5014}); // Bahia
        estadoMap.put("CE", new double[]{-3.7172, -38.5434});  // Ceará
        estadoMap.put("DF", new double[]{-15.8267, -47.9218}); // Distrito Federal
        estadoMap.put("ES", new double[]{-20.3155, -40.3128}); // Espírito Santo
        estadoMap.put("GO", new double[]{-16.6869, -49.2648}); // Goiás
        estadoMap.put("MA", new double[]{-2.5297, -44.3028});  // Maranhão
        estadoMap.put("MG", new double[]{-19.9245, -43.9352}); // Minas Gerais
        estadoMap.put("MS", new double[]{-20.4697, -54.6201}); // Mato Grosso do Sul
        estadoMap.put("MT", new double[]{-15.6014, -56.0979}); // Mato Grosso
        estadoMap.put("PA", new double[]{-1.4550, -48.5044});  // Pará
        estadoMap.put("PB", new double[]{-7.1153, -34.861});   // Paraíba
        estadoMap.put("PE", new double[]{-8.0476, -34.8770});  // Pernambuco
        estadoMap.put("PI", new double[]{-5.092, -42.8034});   // Piauí
        estadoMap.put("PR", new double[]{-25.4284, -49.2733}); // Paraná
        estadoMap.put("RJ", new double[]{-22.9068, -43.1729}); // Rio de Janeiro
        estadoMap.put("RN", new double[]{-5.7945, -35.211});   // Rio Grande do Norte
        estadoMap.put("RO", new double[]{-8.7608, -63.8999});  // Rondônia
        estadoMap.put("RR", new double[]{2.8235, -60.6758});   // Roraima
        estadoMap.put("RS", new double[]{-30.0346, -51.2177}); // Rio Grande do Sul
        estadoMap.put("SC", new double[]{-27.5954, -48.548});  // Santa Catarina
        estadoMap.put("SE", new double[]{-10.9472, -37.0731}); // Sergipe
        estadoMap.put("SP", new double[]{-23.5505, -46.6333}); // São Paulo
        estadoMap.put("TO", new double[]{-10.1753, -48.2982}); // Tocantins
    }

    public static double[] getCoordenadas(String estado) {
        return estadoMap.get(estado.toUpperCase());
    }

}
