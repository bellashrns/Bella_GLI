import java.util.Scanner

fun main() {
    val s = Scanner(System.`in`)
    var ipk: Double = 0.0

    for (i in 1..8) {

        print("Input nilai IP semester $i: ")
        var ip: Double = s.nextDouble()

        if (ip < 0 || ip > 4) {
            println("Nilai yang Anda masukkan tidak valid!")
            return
        }
        
        ipk += ip
    }

    ipk /= 8

    println("IPK: $ipk")
    println("Predikat: ${validateIP(ipk)}")
}

fun validateIP(ipk: Double): String {
    return when {
        ipk > 3.75 -> "Dengan Pujian"
        ipk > 3.5 -> "Sangat Memuaskan"
        ipk > 2.99 -> "Memuaskan"
        ipk > 1.99 -> "Cukup"
        ipk > 0 -> "Kurang"
        else -> "Nilai yang Anda masukkan tidak valid!"
    }
}