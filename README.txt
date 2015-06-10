
Objetivo
========

Aplica��o desenvolvida para criar/atualizar e buscar motoristas de acordo com a geolocaliza��o do usu�rio e dos motoristas da �rea.
A busca de motoristas ser� ordenada por relevancia. A relevancia do motorista considera o menor tempo de chegada at� o usu�rio ou a menor distancia.
O tempo de chegada � obtido atrav�s da API Distance Matrix do Google Map.

Criar Motorista
===============

POST http://146.148.44.89:8080/taxis/drivers
{
  "name": "joao",
  "carPlate": "ZZZ-9090"  
}


Atualizar Posi��o e Status do Motorista
=======================================

POST http://146.148.44.89:8080/taxis/drivers/1/status
{
  "driverId": "1",
  "latitude": "30.0",
  "longitude": "30.0",
  "driverAvailable": "true"
}


Recuperar Posi��o e Status do Motorista
=======================================

GET http://146.148.44.89:8080/taxis/drivers/1/status


Buscar Motoristas Disponiveis na �rea
=====================================

GET http://146.148.44.89:8080/taxis/drivers/inArea?sw=1.0,1.0&ne=40.0,40.0
