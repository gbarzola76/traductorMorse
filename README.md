# traductorMorse

Este proyecto traduce una secuencia de bits a código morse, también traduce código morse a texto.

#**Dependencias y deploy**#

##Se utilizaron los siguientes módulos de Spring:
* spring-boot-starter-web
* spring-boot-starter-test

## Para levantar el proyecto:
1. git clone https://github.com/gbarzola76/traductorMorse.git
2. mvn spring-boot:run

##Requests

En el Postman ponemos la siguiente URL: http://localhost:8080/translate/decodeBits2Morse. Se configura para que se envie por POST, y en los parametros poner key: "code" y value, la secuencia de bits.


