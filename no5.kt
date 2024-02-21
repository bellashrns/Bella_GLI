fun main() {
    print("Input Kalimat: ")
    var string: String = readLine()!!.lowercase()

    val vocal = "aeiou"

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