# n2-backend-projeto

Este projeto é um serviço de meteorologia que utiliza a API [Open Meteo](https://open-meteo.com/) para obter previsões climáticas com base no estado e data fornecidos pelo usuário. Ele converte os dados de previsão, como temperatura, precipitação e horários de nascer/pôr do sol, em mensagens e também pode gerar um relatório (para mais informações). <br>

# Tecnologias utilizadas <br>
- Java <br>
- Spring Boot <br>
- Maven <br>
- Docker <br>

# Exemplo <br>
## POST <br>
/clima/relatorio <br>

Solicitação <br>

```
{
  "estado": "SC",
  "data": "2024-10-16"
}
```

Resposta <br>

```
Relatório Climático:
Temperatura máxima: 19.6°C
Temperatura minima: 26.0°C
Condição: Chuva leve
Nascer do sol: 2024-10-16T08:36
Pôr do sol: 2024-10-16T21:22
Indice max UV: 7.0
Soma da precipitação: 1.5 mm
Horas de precipitação: 9.0
Probabilidade máxima de precipitação: 88.0%
```

## GET

Solicitação <br>

```
http://localhost:8080/clima/previsao?estado=SC&data=2024-10-16&daily=temperature_2m_max,temperature_2m_min

```

Resposta <br>
```
A previsão para o estado selecionado indica temperaturas entre 19.6°C e 26.0°C.

```



