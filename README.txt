
Objetivo
========

Aplicação desenvolvida para criar/atualizar e buscar motoristas de acordo com a geolocalização do usuário e dos motoristas da área.
A busca de motoristas será ordenada por relevancia. A relevancia do motorista considera o menor tempo de chegada até o usuário ou a menor distancia.
O tempo de chegada é obtido através da API Distance Matrix do Google Map.

Criar Motorista
===============

POST http://146.148.44.89:8080/taxis/drivers
{
  "name": "joao",
  "carPlate": "ZZZ-9090"  
}


Atualizar Posição e Status do Motorista
=======================================

POST http://146.148.44.89:8080/taxis/drivers/1/status
{
  "driverId": "1",
  "latitude": "30.0",
  "longitude": "30.0",
  "driverAvailable": "true"
}


Recuperar Posição e Status do Motorista
=======================================

GET http://146.148.44.89:8080/taxis/drivers/1/status


Buscar Motoristas Disponiveis na Área
=====================================

GET http://146.148.44.89:8080/taxis/drivers/inArea?sw=1.0,1.0&ne=40.0,40.0
