import kotlin.random.Random

/*Написать консольную игру “угадай число”. Рандомно генерируем число от 0 до 10.
Пользователю дается 3 попытки угадать его. При каждой попытке компьютер должен сообщить, больше или меньше загаданное число.
После победы /проигрыша выводится запрос: “Повторить игру еще раз? 1 - да/0 - нет” (1 - повторить, 0 - нет)
* Сделать несколько уровней сложности игры, например, диапазон значений от 0 до 100 и попыток 5-7
или кастомные настройки от пользователя (запрашиваем у него желаемый диапазон и количество попыток)*/

fun main() {


    do {
        menu()
        val numberToGuess = generateRandomNumber(range)

        for (i in 1..numberOfTrails) {
            var trialNumber: String
            do {
                print("Попытка $i, введите число: ")
                trialNumber = readln()
            } while (!trialNumber.matches(Regex("[0-9]+")))

            val result = checkNumber(numberToGuess, trialNumber.toInt())
            println(result)
            if (result == "Вы угадали!!!")
                break
            if (i == numberOfTrails)
                println("Число попыток истекло. Вы не угадали число $numberToGuess")
        }

    } while (repeatGame() == 1)
    println("GAME OVER!!!")
}

var range: Int = 0
var numberOfTrails: Int = 0

fun repeatGame(): Int {
    var repeater: String
    do {
        println("Повторить игру еще раз? 1 - да/0 - нет")
        repeater = readln()
    } while (!repeater.matches(Regex("[0-1]")))
    return repeater.toInt()
}

fun menu() {
    var gameVariant: String
    do {
        println("Выберите вариант игры:")
        println("1 - Базовый - интервал чисел от 0 до 10, 3 попытки")
        println("2 - Индивидуальный - интервал чисел и число попыток выбирает пользователь")
        println("3 - Профи - интервал чисел от 0 до 100, 7 попыток")
        gameVariant = readln()
    } while (!gameVariant.matches(Regex("[1-3]")))

    when (gameVariant.toInt()) {
        1 -> {
            range = 10; numberOfTrails = 3
        }

        2 -> {
            println("Выберите правую границу интервала")
            range = readln().toInt()
            println("Выберите число попыток")
            numberOfTrails = readln().toInt()
        }

        3 -> {
            range = 100; numberOfTrails = 7
        }
    }
}

fun generateRandomNumber(range: Int): Int {

    return Random.nextInt(0, range + 1)
}

fun checkNumber(numberToGuess: Int, trialNumber: Int): String {
    return if (numberToGuess < trialNumber)
        "Загаданное число меньше"
    else if (numberToGuess > trialNumber)
        "Загаданное число больше"
    else "Вы угадали!!!"
}