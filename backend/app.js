const express = require('express')
const app = express()
const port = 3000

const ProductRepository = require('./repository/ProductRepository')
const PaymentRepository = require('./repository/PaymentRepository')
const Response = require('./model/Response')

const productRepository = new ProductRepository()
const paymentRepository = new PaymentRepository()

app.get('/product/:id', function(req, res){
	res.json(new Response(200, "Success", productRepository.getProduct(req.params.id)));
})

app.get('/product/', function(req, res){
	res.json(new Response(200, "Success", productRepository.getProducts()));
})

app.put('/payment/', function(req, res){
	res.json(new Response(200, "Success", paymentRepository.payCart(req.body)));
})

app.listen(port, () => console.log(`listening ${port}`))