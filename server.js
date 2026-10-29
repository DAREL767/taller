// ============================================================
//  TALLER: Chat en tiempo real con Socket.IO
//  ARCHIVO: server.js  (lado servidor)
// ============================================================
//  Busca los comentarios "TODO" y complétalos.
//  No borres los comentarios: son tu guía.
// ============================================================

const express = require('express');
const http = require('http');
const { Server } = require('socket.io');

const app = express();
const server = http.createServer(app);

// Socket.IO necesita "montarse" sobre el servidor HTTP,
// porque el WebSocket nace de una petición HTTP que se "actualiza" (upgrade).
const io = new Server(server);

// Servimos la carpeta public como archivos estáticos (el frontend).
app.use(express.static('public'));

// Lista en memoria de usuarios conectados: socket.id -> nombre
const usuarios = new Map();

// ------------------------------------------------------------
// io.on('connection') se dispara UNA VEZ por cada cliente que entra.
// El objeto `socket` representa a ESE cliente en particular.
// ------------------------------------------------------------
io.on('connection', (socket) => {
  console.log('Se conectó:', socket.id);

  // TODO 1 — Escuchar el evento 'entrar' que manda el cliente con su nombre.
  socket.on('entrar', (nombre) => {
    usuarios.set(socket.id, nombre);
    io.emit('sistema', `${nombre} se unió al chat`);
    io.emit('usuarios', [...usuarios.values()]);
  });


  // TODO 2 — Escuchar el evento 'mensaje' que manda el cliente.
  socket.on('mensaje', (texto) => {
    const autor = usuarios.get(socket.id) || 'Anónimo';
    const hora = new Date().toISOString();
    io.emit('mensaje', { autor, texto, hora });
  });


  // TODO 3 — Escuchar 'escribiendo' y avisar SOLO a los demás
  socket.on('escribiendo', (nombre) => {
    socket.broadcast.emit('escribiendo', nombre);
  });


  // TODO 4 — Manejar la desconexión.
  socket.on('disconnect', () => {
    console.log('Se desconectó:', socket.id);
    const nombre = usuarios.get(socket.id);
    if (nombre) {
      usuarios.delete(socket.id);
      io.emit('sistema', `${nombre} abandonó el chat`);
      io.emit('usuarios', [...usuarios.values()]);
    }
  });
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Servidor escuchando en http://localhost:${PORT}`);
});
