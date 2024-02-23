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
    val menu = readln().toInt()

    when (menu) {
        1 -> {
            print("Username: ")
            val username = readln()

            print("Password: ")
            val password = readln()

            if (login(username, password)) println("Login Berhasil")
            else exceptionHandler()
        }
        2 -> totalHarga()
        3 -> gajiKaryawan()
        else -> println("Menu tidak tersedia")
    }
}

fun login(username: String, password: String): Boolean {
    return validateUsername(username) && validatePassword(password)
}

fun validateUsername(username: String): Boolean {
    var flag = 1
    if (username.isEmpty()) {
        println("Username cannot be empty")
        flag = 0
    } else {
        if ((username.length < 6) || (username.length > 15)) {
            println("Username must be at least 6 characters long and at most 15 characters long")
            flag = 0
        } else {
            if (username.onlyLetters() || username.onlyNumbers()) {
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
        if ((password.length < 8) || (password.length > 20)) {
            println("Password must be at least 8 characters long and at most 20 characters long")
            flag = 0
        } else {
            if (password.onlyLetters() || password.onlyNumbers() || password.onlySpecialCharacters()) {
                println("Password must contain letters, numbers, and special characters")
                flag = 0
            }
        }
    }
    return flag == 1
}

fun String.onlyLetters(): Boolean {
    return this.all { it.isLetter() }
}

fun String.onlyNumbers(): Boolean {
    return this.all { it.isDigit() }
}

fun String.onlySpecialCharacters(): Boolean {
    return this.all { it.isLetterOrDigit() }
}

fun exceptionHandler() {
    throw Exception("Login Gagal")
}

fun totalHarga() {
    val namaProduk = "Aqua"
    println("Nama Produk: $namaProduk")

    val stokProduk = 100
    println("Stok Produk: ${stokProduk}pcs")

    val hargaProduk = 5000
    println("Harga Produk: Rp ${convertToRupiah(hargaProduk)}\n")

    val (quantityProduk, nominalBayar) = getInput()

    println("\n=====================================")

    if (quantityProduk > stokProduk) print("Stok yang Anda beli kurang dari ${quantityProduk}pcs")
    else transaction(quantityProduk, nominalBayar, hargaProduk)
}

fun transaction(quantityProduk: Int, nominalBayar: Int, hargaProduk: Int) {
    val totalHarga = quantityProduk * hargaProduk
    println("Total Harga Produk: Rp ${convertToRupiah(totalHarga)}")

    val totalBayar = validateDiskon(totalHarga)
    println("Total yang harus Anda bayar sebesar Rp ${convertToRupiah(totalBayar)}")

    println("Uang pembayaran Anda sebesar Rp ${convertToRupiah(nominalBayar)}")
    validatePembayaran(nominalBayar, totalBayar)
}

fun getInput(): Pair<Int, Int> {
    print("Quantity Pembelian: ")
    val getInputQuantity = readln()
    val quantityProduk = validateInput(getInputQuantity, "Quantity")

    print("Masukkan Nominal : ")
    val getNominalBayar = readln()
    val nominalBayar = validateInput(getNominalBayar, "Nominal Pembayaran")

    return Pair(quantityProduk, nominalBayar)
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
        println("Anda mendapat diskon sebesar Rp ${convertToRupiah(potonganHarga.toInt())}")
    }

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

fun validateInput(input: String, msg: String): Int {
    if (input.isEmpty()) println("$msg tidak boleh kosong")
    else {
        if (input.contains("-")) println("$msg tidak boleh negatif")
    }

    val finalInput = input.filter { it.isDigit() }.toInt()

    if (finalInput == 0) println("$msg tidak boleh 0")

    return finalInput
}

fun validatePembayaran(totalBayar: Int, totalHarga: Int) {
    if (totalBayar < totalHarga) println("Uang yang Anda bayarkan kurang, harap ubah nominal pembayaran")
    else {
        if (totalBayar % 100000 != 0) println("Uang yang Anda bayarkan bukan kelipatan Rp 100.000, harap ubah nominal pembayaran")
        else println("Uang kembalian Anda sebesar Rp ${convertToRupiah(totalBayar - totalHarga)}")
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
    if (validateInputGaji(namaKaryawan, grade)) calculateGaji(namaKaryawan, grade, jumlahJamKerja)
}

fun validateInputGaji(namaKaryawan: String, grade: String): Boolean {
    var flag = 1
    if (namaKaryawan.isEmpty()) {
        println("Nama Karyawan tidak boleh kosong")
        flag = 0
    }

    if (grade.isEmpty()) {
        println("Grade tidak boleh kosong")
        flag = 0
    }

    val validateGrade = "ABCD"
    if (!validateGrade.contains(grade)) {
        println("Grade tidak valid")
        flag = 0
    }

    return flag == 1
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
    var gajiTotal = 0.0

    if (jumlahJamKerja > jamKerjaPerMinggu) {
        val lembur = jumlahJamKerja - jamKerjaPerMinggu
        val gajiLembur = lembur * gajiPerJamLembur
        gajiTotal = (gajiPerJam * jamKerjaPerMinggu) + gajiLembur

        println("Gaji Anda minggu ini sebesar Rp ${convertToRupiah(gajiTotal.toInt())}")
        println("Anda melakukan lembur sebanyak $lembur jam")
        println("Upah lembur Anda sebesar Rp ${convertToRupiah(gajiLembur.toInt())}")
    } else if (jumlahJamKerja < jamKerjaPerMinggu) {
        val potonganGaji = (jamKerjaPerMinggu - jumlahJamKerja) * gajiPerJam * potonganPerJam
        gajiTotal = (gajiPerJam * jamKerjaPerMinggu) - potonganGaji

        println("Total jam kerja Anda minggu ini kurang dari 40 jam")
        println("Anda dipotong gaji sebesar Rp ${convertToRupiah(potonganGaji.toInt())}")
    } else {
        gajiTotal = gajiPerJam * jamKerjaPerMinggu
    }

    println("Total Gaji yang Anda dapatkan minggu ini sebesar Rp ${convertToRupiah(gajiTotal.toInt())}")
}