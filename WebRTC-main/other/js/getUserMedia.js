navigator.getUserMedia = navigator.getUserMedia
 || navigator.webkitGetUserMedia
 || navigator.mozGetUserMedia;
var constraints = {audio: false, video: true};
var vgaConstraints = { video: { mandatory: { maxWidth: 640, maxHeight: 360 } } };
constraints = vgaConstraints;
var video = document.querySelector("video");
function successCallback(stream) {
window.stream = stream;
if (window.URL) {
 video.src = window.URL.createObjectURL(stream);
} else {
 video.src = stream;
}
video.play();
}
function errorCallback(error){
console.log("navigator.getUserMedia error: ",
error);
}
navigator.getUserMedia(constraints, successCallback,
errorCallback);
