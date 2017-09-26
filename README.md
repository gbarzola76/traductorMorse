# traductorMorse

Este proyecto traduce una secuencia de bits a código morse, también traduce código morse a texto y texto a codigo morse.

#**Dependencias y deploy**#

##Se utilizaron los siguientes módulos de Spring:
* spring-boot-starter-web
* spring-boot-starter-test

## Para levantar el proyecto:
1. git clone https://github.com/gbarzola76/traductorMorse.git
2. mvn spring-boot:run

##Requests

Para la decodificación de la secuencia de bits a código morse en el Postman ponemos la siguiente URL: 

https://morse-meli.herokuapp.com/translate/decodeBits2Morse 

Se configura para que se envie por POST, y en los parametros por Body poner key: "code" y value, la secuencia de bits.



Para la decodificación del código morse a texto en el Postman ponemos la siguiente URL:

https://morse-meli.herokuapp.com/translate/translate2Human 

Se configura para que se envie por POST, y en los parametros por Body poner key: "morseCode" y value, el código morse.




Bonus:

Para la codificación de texto a morse en el Postman ponemos la siguiente URL:

https://morse-meli.herokuapp.com/translate/2morse

Se pasa por POST por Body raw JSON(application/json) {"text":"HOLA MELI"}

También puede probarse con curl:

$ curl -H "Content-Type: application/json" -X POST https://morse-meli.herokuapp.com/translate/2morse -d '{"text":"HOLA MELI"}'




Para la decodificación del código morse a texto en el Postman ponemos la siguiente URL:

https://morse-meli.herokuapp.com/translate/2text

Se pasa por POST por Body raw JSON(application/json) {"text": ".... --- .-.. .- -- . .-.. .."}

También puede probarse con curl:

$ curl -H "Content-Type: application/json" -X POST https://morse-meli.herokuapp.com/translate/2text -d '{"text": ".... --- .-.. .- -- . .-.. .."}'



Link del Postman https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop




