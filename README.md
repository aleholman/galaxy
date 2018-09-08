## Enunciado
En una galaxia lejana, existen tres civilizaciones: Vulcanos, Ferengis y Betasoides. 

Dominan la predicción del clima mediante un complejo sistema informático con las siguientes premisas:
* El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
horario. Su distancia con respecto al sol es de 500Km.
* El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
horario. Su distancia con respecto al sol es de 2000Km.
* El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
anti­horario, su distancia con respecto al sol es de 1000Km.
* Todas las órbitas son circulares.
* Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
sistema solar experimenta un período de sequía.
* Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el
momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un
período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en
su máximo.
* Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están
alineados entre sí pero no están alineados con el sol.

Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá?
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?

Bonus:
Para poder utilizar el sistema como un servicio a las otras civilizaciones, los Vulcanos requieren
tener una base de datos con las condiciones meteorológicas de todos los días y brindar una API
REST de consulta sobre las condiciones de un día en particular.
1) Generar un modelo de datos con las condiciones de todos los días hasta 10 años en adelante
utilizando un job para calcularlas.
2) Generar una API REST la cual devuelve en formato JSON la condición climática del día
consultado.
3) Hostear el modelo de datos y la API REST en un cloud computing libre (como APP Engine o
Cloudfoudry) y enviar la URL para consulta:
Ej: GET → http://....../clima?dia=566 → Respuesta: {“dia”:566, “clima”:”lluvia”}


## Solución ##
* La solución al los primeros 3 puntos la pueden ver ejecutando la clase Main y pueden ejecutar los tests con el comando 'mvn test'
* En cuanto a los puntos de bonus:
  * la solución fue realizada con SpringBoot. Pueden levantar la aplicación ejecutando la clase GalaxyApplication
  * Una vez levantada el servicio responde en http://localhost:8080/api/clima/dia/566

## Consideraciones ##
* El sistema solar es un sistema de coordenadas (x,y) donde la posición del sol es (0,0)
* Asumo que el día cero, los planetas inician alineados, todos sobre el eje X.
* Los años son de 360 días, que es lo que tarda Ferengi (el planeta más lento) en dar una vuelta completa al Sol
* Si no se cumplen ninguna de las condiciones mencionadas, la predicción del clima es "Desconocido"
* Cuando el sol está en uno de los lados del triángulo formado por los planetas, considero que está dentro => Llueve

