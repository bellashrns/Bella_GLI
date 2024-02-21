import kotlin.random.Random

fun main() {
    val randomValues = MutableList(11) { Random.nextInt(1, 100) }
    println(randomValues)

    randomValues.sort()

    println(randomValues)

    // val largest = randomValues.max()
    // val smallest = randomValues.min()

    val largest = randomValues.last()
    val middle = randomValues[randomValues.size / 2]
    val smallest = randomValues.first()

    println("Largest: $largest")
    println("Middle: $middle")
    println("Smallest: $smallest")

    val average = (largest + middle + smallest) / 3

    println("Average: $average")
}