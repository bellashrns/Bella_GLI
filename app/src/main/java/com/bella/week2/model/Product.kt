package com.bella.week2.model

data class Product(val name: String, val stock: String, val price: String) {
    companion object {
        private val products = mutableListOf<Product>().apply {
            add(Product("Nama Produk", "Stok", "Harga Satuan"))
        }

        fun addProduct(product: Product) {
            products.add(product)
        }

        fun getAllProducts(): List<Product> {
            return products
        }
    }
}
