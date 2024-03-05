package com.bella.week2

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bella.week2.databinding.ActivityProductBinding
import com.bella.week2.model.Product
import java.text.DecimalFormat

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private var products = Product.getAllProducts() as ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayProductOnList()

        binding.saveBtn.setOnClickListener {
            val name = binding.productName.text.toString()
            val stock = binding.productStock.text.toString()
            val price = binding.productPrice.text.toString()

            val nameValidation = name.isNotEmpty()
            val stockValidation = validateInput(stock)
            val priceValidation = validateInput(price)

            if (nameValidation && stockValidation && priceValidation) {
                Product.addProduct(Product(name, "$stock pcs", "Rp " + formatter(price.toInt())))
                displayProductOnList()
            }

            binding.productName.text.clear()
            binding.productStock.text.clear()
            binding.productPrice.text.clear()
        }
    }

    private fun displayProductOnList() {
        val productNameTV = binding.productNameTv
        val productStockTV = binding.productStockTv
        val productPriceTV = binding.productPriceTv

        var productName = "Nama Produk\n"
        var productStock = "Stok\n"
        var productPrice = "Harga Satuan\n"

        for (product in products) {
            productName += (product.name + "\n")
            productStock += (product.stock + "\n")
            productPrice += (product.price + "\n")
        }

        productNameTV.text = productName
        productStockTV.text = productStock
        productPriceTV.text = productPrice
    }

    private fun validateInput(input: String): Boolean {
        if (input.notContainDigit()) return false

        val finalInput = input.filter { it.isDigit() }.toInt()

        return finalInput > 0
    }

    private fun String.notContainDigit(): Boolean {
        return !this.any { it.isDigit() }
    }

    private fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")
}