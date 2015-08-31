# Nasa Server - Marte
API Rest para controlar Sondas em Marte pela NASA.

Exemplo de consumo via put:
URL:
http://ip:porta/nasaserver/sonda/put/mover/lista
Body:
[{"planalto":{"xMaximo":{"valor":5},"yMaximo":{"valor":5}},"xInicialSonda":1,"yInicialSonda":3,"direcaoInicial":"NORTH","comandos":["M"]},
{"planalto":{"xMaximo":{"valor":5},"yMaximo":{"valor":5}},"xInicialSonda":1,"yInicialSonda":3,"direcaoInicial":"EAST","comandos":["M"]},
{"planalto":{"xMaximo":{"valor":5},"yMaximo":{"valor":5}},"xInicialSonda":1,"yInicialSonda":3,"direcaoInicial":"WEST","comandos":["M"]},
{"planalto":{"xMaximo":{"valor":5},"yMaximo":{"valor":5}},"xInicialSonda":1,"yInicialSonda":3,"direcaoInicial":"SOUTH","comandos":["M"]}]
Header:
Content-Type=application/json

Exemplo de consumo via get:
URL:
http://ip:porta/nasaserver/sonda/get/mover/unica?xMaximoPlanalto=5&yMaximoPlanalto=5&xInicialSonda=1&yInicialSonda=3&direcao=NORTH&comandos=M&comandos=M
