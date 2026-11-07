# Taller de Proxy y Trunk-Based Development

Proyecto en Java 17 con JUnit 5. Para ejecutar las pruebas, usa `mvn clean test`.

El envío cuesta 2,50 por kilo y suma 5 fuera de Bogotá. El proxy registra el identificador del pedido y el costo. Las pruebas de ambos estados de `smart-shipping` están en el historial de `main`, hasta el commit que activa el flag. La versión final elimina el flag y el calculador anterior.

## Reflexión

1. Tres semanas en una rama separada habrían acumulado diferencias con `main`. Los conflictos y los errores de integración se habrían descubierto más tarde.
2. Durante la migración, el Proxy elegía a qué calculador delegar a través de la misma interfaz. Un Decorator añade comportamiento alrededor de un delegado, como el registro que queda al final. Un Adapter traduce una interfaz a la que espera el cliente.
3. Borrar el flag elimina una ruta que ya cumplió su propósito. Con 200 flags olvidados, resulta más difícil saber qué configuración está activa y qué rutas hay que probar o investigar.
