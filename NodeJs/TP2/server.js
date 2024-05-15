// server.js
const express = require('express'); // Import express

var app = express();
var server = require('http').createServer(app);
var io = require('socket.io')(server);
var requestHandlers = require("./requestHandlers");
app.use(express.static(__dirname + '/public'));
io.on('connection', function(socket) {
console.log('a user connected');
// Handle socket events
socket.on('start', function() {
requestHandlers.start(socket);
});
socket.on('upload', function(data) {
requestHandlers.upload(data, socket);
});
socket.on('find', function() {
requestHandlers.find(socket);
});
socket.on('show', function() {
requestHandlers.show(socket);
});
socket.on('login', function(data) {
requestHandlers.login(data, socket);
});
socket.on('logout', function() {
requestHandlers.logout(socket);
});
socket.on('disconnect', function() {
console.log('a user disconnected');
});
});
server.listen(8888, function() {
console.log('Server has started on port 8888');
});

app.get('/start', function(req, res) {
    requestHandlers.startAngular(req, res);
    });