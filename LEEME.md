<!--
  ╔════════════════════════════════════════════════════════════════╗
  ║  PLANTILLA DE ENTREGA — Taller de chat con Socket.IO           ║
  ╠════════════════════════════════════════════════════════════════╣
  ║  1. Renombra este archivo a README.md (reemplaza el que viene  ║
  ║     con el taller: ese es el enunciado, no tu entrega).        ║
  ║  2. Llena TODO lo que diga [...] y borra los comentarios como  ║
  ║     este, que van entre <!-- y -->                             ║
  ║  3. Marca con [x] lo que hiciste. No marques lo que no sirve:  ║
  ║     se revisa corriendo el código.                             ║
  ╚════════════════════════════════════════════════════════════════╝
-->

# Chat en tiempo real con Socket.IO

**Estudiante:** Jamed Alejandro Sanchez  
**Código:** 2220242030  
**Materia / grupo:** Ingenieria de Software  
**Fecha de entrega:** 29/10/2026  
**Repositorio:** https://github.com/JuanBonilla1305/taller-chat-socketio  

---

## 1. Cómo correr el proyecto

```bash
git clone https://github.com/JuanBonilla1305/taller-chat-socketio.git
cd taller-chat-socketio
npm install
npm start
```

Luego abre `http://localhost:3000` en **dos ventanas** del navegador (una en modo incógnito) para chatear contigo mismo.

**Versión de Node usada:** v20.11.0

---

## 2. Eventos de Socket.IO que implementé

| Evento | Lo emite | Lo escucha | Qué datos lleva | Quién lo recibe |
|---|---|---|---|---|
| `entrar` | Cliente | Servidor | `nombre` (string) | Solo el servidor |
| `sistema` | Servidor | Cliente | `texto` (string) | Todos (`io.emit`) |
| `mensaje` | Cliente / Servidor | Servidor / Cliente | `texto` (al servidor) / `{autor, texto, hora}` (al cliente) | Todos (`io.emit`) |
| `usuarios` | Servidor | Cliente | Array de nombres (`[...usuarios.values()]`) | Todos (`io.emit`) |
| `escribiendo` | Cliente / Servidor | Servidor / Cliente | `nombre` (string) | Todos menos el emisor (`socket.broadcast.emit`) |

---

## 3. Retos obligatorios

- [x] **Reto 1** — Entrar con un nombre y avisar al resto
- [x] **Reto 2** — Mandar y recibir mensajes en tiempo real
- [x] **Reto 3** — Lista de conectados que se actualiza
- [x] **Reto 4** — Indicador "está escribiendo"
- [x] **Reto 5** — Desconexión limpia (sale de la lista y avisa)

**¿Algo quedó a medias o no funciona?**
Todos los retos requeridos fueron completados e integrados exitosamente.

---

## 4. Retos extra que hice

| Reto extra | ¿Cómo lo resolví? |
|---|---|
| Ninguno | N/A |

---

## 5. Capturas

![Dos usuarios chateando](capturas/chat.png)

![Lista de conectados](capturas/usuarios.png)

---

## 6. Lo que más me costó

El manejo adecuado del indicador de escritura para evitar que el usuario se viera a sí mismo escribiendo. Se resolvió utilizando `socket.broadcast.emit` en lugar de `io.emit` en el servidor, e implementando un `clearTimeout` en el cliente para reiniciar el temporizador de 1.5 segundos en cada pulsación de tecla.

---

## 7. Preguntas de sustentación

**1. ¿En qué se diferencian `socket.emit`, `socket.broadcast.emit` e `io.emit`?**  
* `socket.emit`: Envía el evento únicamente al cliente correspondiente a ese conector específico.
* `socket.broadcast.emit`: Envía el evento a todos los clientes conectados, excluyendo al emisor del mensaje.
* `io.emit`: Envía el evento de manera global a absolutamente todos los clientes conectados al servidor.

**2. ¿Por qué el servidor no puede confiar en el nombre que manda el cliente?**  
Porque el cliente es un entorno no seguro. Un usuario podría alterar el código en la consola del navegador o emitir eventos con nombres arbitrarios para hacerse pasar por otro usuario. Por seguridad, la identidad se asocia internamente en el servidor usando el identificador único de la conexión (`socket.id`).

**3. ¿Qué pasa con el `Map` de usuarios si el servidor se reinicia? ¿Cómo lo arreglarías?**  
Dado que el `Map` almacena la información en la memoria RAM del proceso, un reinicio del servidor borra por completo los usuarios registrados. Para evitar la pérdida de estado, se podría integrar una base de datos o un sistema de almacenamiento en caché clave-valor como Redis.

**4. ¿Qué es el "fallback a long-polling" y cuándo entra en acción?**  
Es el mecanismo alternativo de Socket.IO que entra en funcionamiento cuando la red, un proxy o el navegador del cliente bloquean el protocolo WebSocket nativo. Recurre a peticiones HTTP repetitivas para simular una conexión continua sin interrumpir el servicio.

**5. Si hubiera 10.000 usuarios, ¿por qué `io.emit` en cada tecla presionada sería un problema?**  
Porque provocaría un consumo masivo e innecesario de ancho de banda y capacidad de procesamiento. Emitir un evento global a 10.000 clientes por cada carácter escrito por un usuario saturaría la red y generaría cuellos de botella en el servidor.

---

## 8. Antes de entregar — revisa esto

- [x] `node_modules/` **no** está en el repo (revisa tu `.gitignore`)
- [x] El repo es **público** y el link de arriba abre
- [x] Tengo **6 commits o más**, con mensajes que dicen qué hice
- [x] Clonando el repo en limpio, `npm install && npm start` funciona
- [x] Las capturas se ven en GitHub (no son links rotos)
- [x] Llené todos los `[...]` de este archivo
- [x] Borré los comentarios de la plantilla

---

## Fuentes y ayuda

- [Documentación oficial de Socket.IO](https://socket.io/docs/v4/)