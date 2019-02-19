const CartItem = require('./../model/Cart')
const ProductRepository = require('./ProductRepository')

function CartRepository () {
	this.cartItems = [
	]
	
	this.productRepository = new ProductRepository()
	
	this.getProductIndex = function (id) {
		for (var i in this.cartItems) {
			if (id == this.cartItems[i].product.id) {
				return i;
			}
		}
		return -1;
	}
	
	
	this.getCartItems = function () {
		return this.cartItems
	}
	
	this.getTotal = function () {
		var total = 0
		for (var i in this.cart) {
			total += cart[i].total
		}
		
		return total
	}
	
	this.putProduct = function (id, count) {
		var index = this.getProductIndex(id)
		
		if (index == -1) {
			this.cart.push = new CartItem(this.productRepository.getProduct(id), count)
			return
		} 
		
		if (count > 0) {
			this.cart[id].count = count
			this.cart[id].total = this.cart[id].product.price * count
		} else if (count == 0){
			this.cart.splice( index, 1 );
		}
	}
	
	this.clearCart = function() {
		this.cartItems = []
	}	
}

module.exports = CartRepository

