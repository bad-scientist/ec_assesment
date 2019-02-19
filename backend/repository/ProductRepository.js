const Product = require('./../model/Product')

function ProductRepository () {
	this.products = [
		new Product(1, "Product1", "This is Product 1", 50),
		new Product(2, "Product2", "This is Product 2", 100),
		new Product(3, "Product3", "This is Product 3", 150)
	]
	
	this.getProduct = function getProduct(id) {
		for (var i in this.products) {
			if (id == this.products[i].id) {
				return this.products[i];
			}
		}
		return "product not found";
	}
	
	this.getProductIndex = function getProductIndex(id) {
		for (var i in this.products) {
			if (id == this.products[i].id) {
				return i;
			}
		}
		return -1;
	}

	this.getProducts = function getProducts() {
		return this.products
	}
	
}



module.exports = ProductRepository

