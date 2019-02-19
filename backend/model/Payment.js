function Payment(cardNumber, expirationDate, cvvCode, total, tax, date, cartItems) {
    this.cardNumber = cardNumber 
    this.expirationDate = expirationDate
    this.cvvCode = cvvCode
    this.total = total
    this.tax = tax
    this.date = date
    this.cartItems = cartItems
}

module.exports = Payment