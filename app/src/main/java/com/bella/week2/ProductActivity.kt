package com.bella.week2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bella.week2.databinding.ActivityProductBinding
import com.bella.week2.model.Product
import java.text.DecimalFormat

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(false)

        products = Product.getAllProducts() as ArrayList<Product>
        val adapter = ProductAdapter(products)
        recyclerView.adapter = adapter

        binding.saveBtn.setOnClickListener {
            val name = binding.productName.text.toString()
            val stock = binding.productStock.text.toString()
            val price = binding.productPrice.text.toString()

            val nameValidation = name.isNotEmpty()
            val stockValidation = validateInput(stock)
            val priceValidation = validateInput(price)

            if (nameValidation && stockValidation && priceValidation) {
                Product.addProduct(Product(name, "$stock pcs", "Rp " + formatter(price.toInt())))
                adapter.notifyItemChanged(products.size - 1)
            }

            binding.productName.text.clear()
            binding.productStock.text.clear()
            binding.productPrice.text.clear()
        }
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