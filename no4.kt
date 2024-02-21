fun main() {
    print("Input Kalimat: ")
    var string: String = readLine()!!.lowercase()

    var resultString = string
    val vocal = "aeiou"

    for (char in string) {
        if (char !in vocal && char != ' ') {
            resultString = resultString.replace(char.toString(), "")
        }
    }

    println("Hasil: $resultString")
}