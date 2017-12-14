function log(message) {
	console.log("%c ===[ " + message + " ]===", 'background: #00b9ff; color: #000000');
}
var TeslaModelS = function () {
	this.numberWheels = 4;
	this.manufacturer = 'Tesla';
	this.make = 'Model S';
};
TeslaModelS.prototype.go = function () {
	console.log('Rotate wheels');
};
TeslaModelS.prototype.stop = function () {
	console.log('Apply brake pads');
};
log(TeslaModelS.manufacturer);