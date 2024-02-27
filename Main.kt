fun main() {
//    totalHarga()
    gajiKaryawan()
}

fun exceptionHandler(msg: String) {
    throw Exception(msg)
}

data class Produk(val nama: String, val stok: Int, val harga: Int)

fun totalHarga() {
    val produk1 = Produk("Aqua", 10, 5000)
    val produk2 = Produk("Indomie", 20, 3000)
    val produk3 = Produk("Kopi", 50, 1000)

    val daftarProduk = listOf(produk1, produk2, produk3)

    printProduk(daftarProduk)

    val pembelianProduk = getProdukYangDibeli(daftarProduk)

    transaction(pembelianProduk, daftarProduk)
}

fun printProduk(daftarProduk: List<Produk>) {
    println("Stok Produk:")
    println("=====================================")

    for (produk in daftarProduk) {
        println("Nama Produk: ${produk.nama}")
        println("Stok Produk: ${produk.stok}pcs")
        println("Harga Satuan: Rp ${produk.harga}\n")
    }

    println("=====================================\n")
}

fun getProdukYangDibeli(daftarProduk: List<Produk>): List<Pair<String, Int>> {
    println("Produk yang dibeli: ")
    println("=====================================")

    val pembelianProduk = mutableListOf<Pair<String, Int>>()

    pembelianProduk.add(Pair("Aqua", validateInput("10pcs", "Quantity")))
    pembelianProduk.add(Pair("Kopi", validateInput("10pcs", "Quantity")))

    for (produk in pembelianProduk) {
        println("Nama Produk: ${produk.first}")
        println("Quantity: ${produk.second}pcs\n")
    }

    for (produk in pembelianProduk) {
        cekProduk(pembelianProduk, daftarProduk)
    }

    println("=====================================")

    return pembelianProduk
}

fun validateInput(input: String, msg: String): Int {
    var finalInput = 0

    if (input.isEmpty()) exceptionHandler("$msg tidak boleh kosong")
    else {
        if (input.contains("-")) exceptionHandler("$msg tidak boleh negatif")
        else {
            finalInput = input.filter { it.isDigit() }.toInt()

            if (finalInput == 0) exceptionHandler("$msg tidak boleh 0")
        }
    }

    return finalInput
}

fun cekProduk(pembelianProduk: List<Pair<String, Int>>, daftarProduk: List<Produk>) {
    for (produkDibeli in pembelianProduk) {
        val cariProduk = daftarProduk.find { it.nama.equals(produkDibeli.first, true) }

        if (cariProduk == null) exceptionHandler("Produk yang Anda beli tidak ada di daftar produk")
        else {
            if (cariProduk.stok < produkDibeli.second) {
                println("Terdapat produk yang stoknya kurang dari yang Anda beli:")
                println("=====================================")
                println("Nama Produk: ${cariProduk.nama}")
                println("Stok Tersedia: ${cariProduk.stok}")
                println("Quantity yang Anda beli: ${produkDibeli.second}pcs")
                exceptionHandler("Harap ubah quantity Anda")
            }
        }
    }
}

fun transaction(pembelianProduk: List<Pair<String, Int>>, daftarProduk: List<Produk>) {
    println("Total Harga Produk:")
    println("=====================================")

    var subtotal = 0
    for (produk in pembelianProduk) {
        val cariProduk = daftarProduk.find { it.nama.equals(produk.first, true) }
        val totalHarga = cariProduk!!.harga * produk.second

        println("Nama Produk: ${cariProduk.nama}")
        println("Quantity Pembelian: ${produk.second}pcs * Rp ${cariProduk.harga}")
        println("Total Harga: Rp $totalHarga\n")

        subtotal += totalHarga
    }

    subtotal = validateDiskon(subtotal)

    println("Total yang harus Anda bayar sebesar Rp $subtotal")
}

fun validateDiskon(totalHarga: Int): Int {
    var diskon = 0.0

    when {
        totalHarga >= 100000 -> diskon = 0.1
        totalHarga >= 50000 -> diskon = 0.05
        else -> println("Anda tidak mendapatkan diskon")
    }

    var potonganHarga = 0.0
    if (diskon != 0.0) {
        potonganHarga = totalHarga * diskon
        println("Anda mendapat diskon sebesar Rp ${potonganHarga.toInt()}")
    }

    return (totalHarga - potonganHarga).toInt()
}

data class Karyawan(val nama: String, val grade: String, val jamKerjaPerMinggu: Int, var gaji: Int = 0)

fun gajiKaryawan() {
    val karyawan1 = Karyawan("Abraham", "A", 40)
    val karyawan2 = Karyawan("Bella", "B", 45)
    val karyawan3 = Karyawan("Kelsha", "C", 30)
    val karyawan4 = Karyawan("Dedi", "D", 50)
    val karyawan5 = Karyawan("Erik", "B", 40)

    val daftarKaryawan = listOf(karyawan1, karyawan2, karyawan3, karyawan4, karyawan5)

    println("Gaji Karyawan:")
    println("=====================================")
    calculateGaji(daftarKaryawan)
}

fun calculateGaji(daftarKaryawan: List<Karyawan>) {
    for (karyawan in daftarKaryawan) {
        println("Nama Karyawan: ${karyawan.nama}")
        println("Grade: ${karyawan.grade}")
        println("Jam Kerja: ${karyawan.jamKerjaPerMinggu} jam")

        val gajiPerJam = when (karyawan.grade) {
            "A" -> 500000 / 8
            "B" -> 300000 / 8
            "C" -> 200000 / 8
            "D" -> 100000 / 8
            else -> 0
        }

        val gajiPerJamLembur = when(karyawan.grade){
            "A" -> 50000.0
            "B" -> 30000.0
            "C" -> 20000.0
            "D" -> 10000.0
            else -> 0.0
        }

        val potonganPerJam = when(karyawan.grade){
            "A" -> 0.8
            "B" -> 0.6
            "C" -> 0.5
            "D" -> 0.3
            else -> 0.0
        }

        karyawan.gaji = calculateGajiPerMinggu(gajiPerJam, gajiPerJamLembur, potonganPerJam, karyawan.jamKerjaPerMinggu)
    }

    println("\nKaryawan dengan gaji tertinggi : ${daftarKaryawan.maxByOrNull { it.gaji }!!.nama}")
    println("Karyawan dengan gaji terendah : ${daftarKaryawan.minByOrNull { it.gaji }!!.nama}")
    println("Rata-rata gaji karyawan : ${daftarKaryawan.map { it.gaji }.average()}")
    println("=====================================")
}

fun calculateGajiPerMinggu(gajiPerJam: Int, gajiPerJamLembur: Double, potonganPerJam: Double, jamKerjaPerMinggu: Int): Int {
    val gajiPerMinggu = gajiPerJam * 40
    println("Gaji Per Minggu: Rp $gajiPerMinggu")

    val totalGaji: Int

    when {
        jamKerjaPerMinggu < 40 -> {
            val potongan = (gajiPerJam * potonganPerJam * (40 - jamKerjaPerMinggu)).toInt()
            totalGaji = gajiPerMinggu - potongan

            println("Potongan: Rp $potongan")
        }
        jamKerjaPerMinggu > 40 -> {
            val jamLembur = jamKerjaPerMinggu - 40
            val gajiLembur = (gajiPerJamLembur * jamLembur).toInt()
            totalGaji = gajiPerMinggu + gajiLembur

            println("Gaji Lembur: $jamLembur jam * Rp $gajiPerJamLembur = Rp $gajiLembur")
        }
        else -> {
            totalGaji = gajiPerMinggu
        }
    }

    println("Total Gaji: Rp $totalGaji")
    println("=====================================")

    return totalGaji
}