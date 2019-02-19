function Response(status_code, message, body) {
	this.status_code = status_code
	this.message = message
	this.body = body
}

module.exports = Response