import java.util.Scanner

fun main() {
    val s = Scanner(System.`in`)

    while(true) {
        menu()
        print("Kembali ke menu? (y/n): ")
        val back = s.next().lowercase()
        if (back == "n") break
    }
}

fun menu() {
    val s = Scanner(System.`in`)

    println("=================== MENU ===================")
    println("1. Hitung IPK")
    println("2. Random Number")
    println("3. Hapus Huruf Vokal")
    println("4. Hapus Huruf Konsonan")
    println("5. Hitung Jumlah Huruf Vokal dan Konsonan")
    print("Pilih Menu: ")
    val menu = s.nextInt()

    when (menu) {
        1 -> hitungIPK()
        2 -> randomNumber()
        3 -> hapusHurufVokal()
        4 -> hapusHurufKonsonan()
        5 -> hitungHuruf()
        else -> println("Menu tidak tersedia!")
    }
}

fun hitungIPK() {
    val s = Scanner(System.`in`)

    var ipk = 0.0

    for (i in 1..8) {
        print("Input nilai IP semester ${i}: ")
        val ip = s.nextDouble()

        if (ip < 0 || ip > 4) {
            println("Nilai yang Anda masukkan tidak valid!")
            return
        }

        ipk += ip
    }

    ipk /= 8

    println("IPK: $ipk")
    println("Predikat: ${validateIPK(ipk)}")
}

fun validateIPK(ipk: Double): String {
    return when {
        ipk > 3.75 -> "Dengan Pujian"
        ipk > 3.5 -> "Sangat Memuaskan"
        ipk > 2.99 -> "Memuaskan"
        ipk > 1.99 -> "Cukup"
        ipk > 0 -> "Kurang"
        else -> "Nilai yang Anda masukkan tidak valid!"
    }
}

fun randomNumber() {
    val s = Scanner(System.`in`)

    print("Input Max Number: ")
    val max = s.nextInt()

    val randomValues = MutableList(11) { (1..max).random() }
    println(randomValues)

    randomValues.sort()

    println(randomValues)

    val largest = randomValues.last()
    val middle = randomValues[randomValues.size / 2]
    val smallest = randomValues.first()

    println("Largest: $largest")
    println("Middle: $middle")
    println("Smallest: $smallest")

    val average: Double = (largest.toDouble() + middle.toDouble() + smallest.toDouble()) / 3

    println("Average: $average")
}

fun hapusHurufVokal() {
    val string = getInputKalimat()

    val vocal = "aiueo"

    var newString = ""
    for (char in string) {
        if (char !in vocal) {
            newString += char
        }
    }

    println("Kalimat Baru: $newString")

}

fun hapusHurufKonsonan() {
    val string = getInputKalimat()

    val vocal = "aiueo"

    var newString = ""
    for (char in string) {
        if (char in vocal) {
            newString += char
        }
    }

    println("Kalimat Baru: $newString")
}

fun hitungHuruf() {
    val string = getInputKalimat()

    val vocal = "aiueo"

    var vocalCount = 0
    var consonantCount = 0

    for (char in string) {
        if (char in vocal && char != ' ') {
            vocalCount++
        } else if (char != ' ') {
            consonantCount++
        }
    }

    println("Jumlah Huruf Vokal: $vocalCount")
    println("Jumlah Huruf Konsonan: $consonantCount")
}

fun getInputKalimat(): String {
    val s = Scanner(System.`in`)

    print("Input Kalimat: ")
    return s.nextLine()!!.lowercase()
}