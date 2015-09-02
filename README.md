# Nasa Server - Marte
API Rest para controlar Sondas em Marte pela NASA.

Exemplo de consumo via <b>put</b>
URL: <a href="http://localhost:8080/nasaserver/sonda/put/mover/lista" target="_blank">http://ip:porta/nasaserver/sonda/put/mover/lista</a><br />
Header:
Content-Type=application/json<br />
Body Request:
```json
{"planalto":
	{"xMaximo": {"valor":5},
	 "yMaximo": {"valor":5}
	},
"sondas":
[ 	{ "pontoAtual": 
		{"x":{"valor":1},
		"y":{"valor":3},
	  "direcaoAtual":"NORTH"},
	  "comandos":["M"]
	},
	{ "pontoAtual":
		{"x":{"valor":1},
		"y":{"valor":3},
	  "direcaoAtual":"EAST"},
	  "comandos":["M"]
	},
	{  "pontoAtual":
		{"x":{"valor":1},
		"y":{"valor":3},
	  "direcaoAtual":"SOUTH"},
	  "comandos":["M"]
	},
	{ "pontoAtual":
		{"x":{"valor":1},
		"y":{"valor":3},
	  "direcaoAtual":"WEST"},
	  "comandos":["M"]
	}
]}
```
Exemplo de consumo via get (apenas <b>uma</b> sonda por vez):
URL:
<a href="http://localhost:8080/nasaserver/sonda/get/mover/unica?xMaximoPlanalto=5&yMaximoPlanalto=5&xInicialSonda=1&yInicialSonda=3&direcao=NORTH&comandos=M&comandos=M">http://ip:porta/nasaserver/sonda/get/mover/unica?xMaximoPlanalto=5&yMaximoPlanalto=5&xInicialSonda=1&yInicialSonda=3&direcao=NORTH&comandos=M&comandos=M</a>
