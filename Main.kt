fun main() {
    do {
        menu()
    } while (true)
}

fun menu() {
    println("Menu")
    println("1. Login")
    println("2. Total Harga")
    println("3. Gaji Karyawan")
    print("Pilih Menu: ")
    val input = readln().toInt()

    when (input) {
        1 -> {
            if (login()) println("Login Berhasil")
            else exceptionHandler("Login Gagal")
        }
        2 -> totalHarga()
        3 -> gajiKaryawan()
        else -> println("Menu tidak tersedia")
    }
}

fun login(): Boolean {
    print("Username: ")
    val username = readln()

    print("Password: ")
    val password = readln()

    return validateUsername(username) && validatePassword(password)
}

fun validateUsername(username: String): Boolean {
    // tujuan pake flag dan ga pake return 1 1, biar semua error bisa ditampilkan
    var flag = 1
    if (username.isEmpty()) {
        println("Username cannot be empty")
        flag = 0
    } else {
        // requirement dari soal
        if ((username.length < 6) || (username.length > 15)) {
            println("Username must be at least 6 characters long and at most 15 characters long")
            flag = 0
        } else {
            if (!username.containsLetterAndDigit()) {
                println("Username must contain letters and numbers")
                flag = 0
            }
        }
    }
    return flag == 1
}

fun validatePassword(password: String): Boolean {
    var flag = 1
    if (password.isEmpty()) {
        println("Password cannot be empty")
        flag = 0
    } else {
        // requirement dari soal
        if ((password.length < 8) || (password.length > 20)) {
            println("Password must be at least 8 characters long and at most 20 characters long")
            flag = 0
        } else {
            if (password.onlyLetterAndDigit()) {
                println("Password must contain letters, numbers, and special characters")
                flag = 0
            }
        }
    }
    return flag == 1
}

fun String.containsLetterAndDigit(): Boolean {
    // return true kalau ada at least 1 huruf dan 1 angka
    return this.any { it.isLetter() } && this.any { it.isDigit() }
}

fun String.onlyLetterAndDigit(): Boolean {
    // return true kalau semua karakter adalah huruf atau angka
    return this.all { it.isLetterOrDigit() }
}

fun exceptionHandler(msg: String) {
    throw Exception(msg)
}

fun totalHarga() {
    val namaProduk = "Aqua"
    println("Nama Produk: $namaProduk")

    val stokProduk = 100
    println("Stok Produk: ${stokProduk}pcs")

    val hargaProduk = 5000
    println("Harga Produk: Rp ${hargaProduk}\n")

    val (quantityProduk, nominalBayar) = getQuantityAndNominal()

    println("\n=====================================")

    if (quantityProduk > stokProduk) exceptionHandler("Stok yang Anda beli kurang dari ${quantityProduk}pcs")
    else transaction(quantityProduk, nominalBayar, hargaProduk)
}

fun getQuantityAndNominal(): Pair<Int, Int> {
    print("Quantity Pembelian: ")
    val quantityProduk = validateQuantityAndNominal(readln(), "Quantity Pembelian")

    print("Masukkan Nominal : ")
    val nominalBayar = validateQuantityAndNominal(readln(), "Nominal Pembayaran")

    return Pair(quantityProduk, nominalBayar)
}

fun validateQuantityAndNominal(input: String, msg: String): Int {
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

fun transaction(quantityProduk: Int, nominalBayar: Int, hargaProduk: Int) {
    val totalHarga = quantityProduk * hargaProduk
    println("Total Harga Produk: Rp $totalHarga")

    val totalBayar = validateDiskon(totalHarga)
    println("Total yang harus Anda bayar sebesar Rp $totalBayar")

    println("Uang pembayaran Anda sebesar Rp $nominalBayar")
    validatePembayaran(nominalBayar, totalBayar)
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

fun validatePembayaran(nominalBayar: Int, totalBayar: Int) {
    if (nominalBayar < totalBayar) exceptionHandler("Uang yang Anda bayarkan kurang, harap ubah nominal pembayaran")
    else {
        if (nominalBayar % 100000 != 0) exceptionHandler("Uang yang Anda bayarkan bukan kelipatan Rp 100.000, harap ubah nominal pembayaran")
        else println("Uang kembalian Anda sebesar Rp ${nominalBayar - totalBayar}")
    }
}

fun gajiKaryawan() {
    print("Nama Karyawan: ")
    val namaKaryawan = readln()

    print("Grade: ")
    val grade = readln().uppercase()

    print("Jumlah Jam Kerja Per Minggu: ")
    val jumlahJamKerja = readln().toInt()

    println("Total Gaji")
    println("=====================================")
    validateInputGaji(namaKaryawan, grade, jumlahJamKerja)
}

fun validateInputGaji(namaKaryawan: String, grade: String, jumlahJamKerja: Int) {
    if (namaKaryawan.isEmpty()) exceptionHandler("Nama Karyawan tidak boleh kosong")

    val validateGrade = "ABCD"
    if (grade.isEmpty()) exceptionHandler("Grade tidak boleh kosong")
    else {
        if (grade.length > 1) exceptionHandler("Grade tidak valid")
        else {
            if (!validateGrade.contains(grade)) exceptionHandler("Grade tidak valid")
        }
    }

    calculateGaji(namaKaryawan, grade, jumlahJamKerja)
}

fun calculateGaji(namaKaryawan: String, grade: String, jumlahJamKerja: Int){
    println("Hai, $namaKaryawan")

    val gajiPerJam = when(grade){
        "A" -> 500000.0 / 8
        "B" -> 300000.0 / 8
        "C" -> 200000.0 / 8
        "D" -> 100000.0 / 8
        else -> 0.0
    }

    val gajiPerJamLembur = when(grade){
        "A" -> 50000.0
        "B" -> 30000.0
        "C" -> 20000.0
        "D" -> 10000.0
        else -> 0.0
    }

    val potonganPerJam = when(grade){
        "A" -> 0.8
        "B" -> 0.6
        "C" -> 0.5
        "D" -> 0.3
        else -> 0.0
    }

    val jamKerjaPerMinggu = 40
    val gajiTotal: Double

    if (jumlahJamKerja > jamKerjaPerMinggu) {
        val lembur = jumlahJamKerja - jamKerjaPerMinggu
        val gajiLembur = lembur * gajiPerJamLembur
        gajiTotal = (gajiPerJam * jamKerjaPerMinggu) + gajiLembur

        println("Gaji Anda minggu ini sebesar Rp ${gajiTotal.toInt()}")
        println("Anda melakukan lembur sebanyak $lembur jam")
        println("Upah lembur Anda sebesar Rp ${gajiLembur.toInt()}")
    } else if (jumlahJamKerja < jamKerjaPerMinggu) {
        val potonganGaji = (jamKerjaPerMinggu - jumlahJamKerja) * (gajiPerJam * potonganPerJam)
        gajiTotal = (gajiPerJam * jamKerjaPerMinggu) - potonganGaji

        println("Total jam kerja Anda minggu ini kurang dari 40 jam")
        println("Anda dipotong gaji sebesar Rp ${potonganGaji.toInt()}")
    } else {
        gajiTotal = gajiPerJam * jamKerjaPerMinggu
    }

    println("Total Gaji yang Anda dapatkan minggu ini sebesar Rp ${gajiTotal.toInt()}")
}