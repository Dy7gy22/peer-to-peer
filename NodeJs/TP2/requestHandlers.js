// requestHandlers.js
var exec = require("child_process").exec;
var fs = require("fs");
var formidable = require("formidable");
var path = require("path");
var bcrypt = require("bcrypt");
function start(socket) {
console.log("Request handler 'tart' was called.");
setTimeout(function() {
socket.emit('start', "<html><body><h1>Bienvenue</h1></body></html>");
}, 60000);
}

function start(socket) {
    console.log("Request handler 'tart' was called.");
    setTimeout(function() {
    socket.emit('start', "<html><body><h1>Bienvenue</h1></body></html>");
    }, 60000);
    }

function upload(data, socket) {
console.log("Request handler 'upload' was called.");
var form = new formidable.IncomingForm();
form.parse(data, function(err, fields, files) {
if (err) {
console.error(err);
socket.emit('error', err.message);
} else {
if (files.uploadedFile) {
var fileName = files.uploadedFile.name;
var filePath = path.join(__dirname, "/uploads/", fileName);
fs.writeFile(filePath, files.uploadedFile.data, function(err) {
if (err) {
console.error(err);
socket.emit('error', err.message);
} else {
socket.emit('upload', "File uploaded successfully: " + fileName);
}
});
} else {
socket.emit('error', "No file uploaded");
}
}
});
}
function find(socket) {
console.log("Request handler 'find' was called.");
exec("find /", { timeout: 10000, maxBuffer: 20000 * 1024 }, function(error, stdout, stderr) {
if (error) {
console.error(error);
socket.emit('error', error.message);
} else {
socket.emit('find', stdout);
}
});
}
function show(socket) {
console.log("Request handler 'how' was called.");
var imagePath = path.join(__dirname, "/uploads/latest.jpg");
if (!fs.existsSync(imagePath)) {
imagePath = path.join(__dirname, "/images/default.png");
}
fs.readFile(imagePath, function(err, data) {
if (err) {
console.error(err);
socket.emit('error', err.message);
} else {
socket.emit('show', data);
}
});
}
function login(data, socket) {
    console.log("Request handler 'login' was called.");
    var isLoggedIn = true;
    if (isLoggedIn) {
    socket.emit('login', "Login successful!");
    } else {
    socket.emit('error', "Login failed. Please check your credentials.");
    }
    }
    function logout(socket) {
    console.log("Request handler 'logout' was called.");
    var isLoggedOut = true;
    if (isLoggedOut) {
    socket.emit('logout', "Logout successful!");
    } else {
    socket.emit('error', "Logout failed. Please try again.");
    }
    }
    module.exports = {
    start: start,
    upload: upload,
    find: find,
    show: show,
    login: login,
    logout: logout
    };

    function startAngular(req, res) {
        console.log("Request handler 'tartAngular' was called.");
        res.send("<html><body><h1>Bienvenue</h1></body></html>");
        }

        module.exports = {
            start: start,
            upload: upload,
            find: find,
            show: show,
            login: login,
            logout: logout,
            startAngular: startAngular
            };