import java.util.Scanner

fun main() {
    val s = Scanner(System.`in`)

    val (quantityProduk, hargaProduk) = getInput(s)

    val totalHarga = quantityProduk * hargaProduk

    println("Total Harga Produk: Rp ${convertToRupiah(totalHarga)}")

    val totalBayar = validateDiskon(totalHarga)

    println("Total Bayar: Rp ${convertToRupiah(totalBayar)}")
}

fun getInput(s: Scanner): Pair<Int, Int>{
    print("Masukkan Nama Produk: ")
    val namaProduk = s.nextLine()

    if (namaProduk.isEmpty()) {
        throw Exception("Nama Produk tidak boleh kosong")
    }

    print("Masukkan Quantity Produk: ")
    val getInputQuantity = s.nextLine()

    if (getInputQuantity.isEmpty()) throw Exception("Quantity tidak boleh kosong")
    else if (getInputQuantity.contains("-")) throw Exception("Quantity tidak boleh negatif")

    val quantityProduk = getInputQuantity.filter { it.isDigit() }.toInt()

    if (quantityProduk == 0) throw Exception("Quantity tidak boleh 0")

    print("Masukkan Harga Satuan Produk: ")
    val getInputHarga = s.nextLine()

    if (getInputHarga.isEmpty()) throw Exception("Harga tidak boleh kosong")
    else if (getInputHarga.contains("-")) throw Exception("Harga tidak boleh negatif")

    val hargaProduk = getInputHarga.filter { it.isDigit() }.toInt()

    if (hargaProduk == 0) throw Exception("Harga tidak boleh 0")

    return Pair(quantityProduk, hargaProduk)
}

fun validateDiskon(totalHarga: Int): Int {
    var diskon = 0.0

    when {
        totalHarga >= 100000 -> diskon = 0.1
        totalHarga >= 50000 -> diskon = 0.05
        else -> println("Anda tidak mendapatkan diskon")
    }

    val potonganHarga = totalHarga * diskon
    println("Diskon: Rp ${convertToRupiah(potonganHarga.toInt())}")

    return (totalHarga - potonganHarga).toInt()
}

fun convertToRupiah(value: Int): String {
    val valueString = value.toString()
    val valueLength = valueString.length

    val rupiah = StringBuilder()
    var counter = 0

    for (i in valueLength - 1 downTo 0) {
        rupiah.append(valueString[i])
        counter++

        if (counter == 3 && i != 0) {
            rupiah.append(".")
            counter = 0
        }
    }

    return rupiah.reverse().toString()
}
