const CartItem = require('./../model/Payment')
const ProductRepository = require('./PaymentRepository')

function PaymentRepository () {
	this.payCart = function (payment) {
		return payment
	}
}

module.exports = PaymentRepository

