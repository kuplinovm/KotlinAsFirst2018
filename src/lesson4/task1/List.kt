@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.digitNumber
import lesson3.task1.minDivisor
import java.lang.StringBuilder
import kotlin.math.pow
import kotlin.math.sqrt
import powint

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (element in v) {
        sum += element * element
    }
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum = 0.0
    if (list.isEmpty()) return 0.0
    else
        for (i in 0 until list.size) {
            val element = list[i]
            sum += element
        }
    return (sum / list.size)
}


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var times = 0.0
    if (a.isEmpty() || b.isEmpty()) return 0.0
    else
        for (i in 0 until a.size) {
            val aa = a[i]
            val bb = b[i]
            times += aa * bb
        }
    return times
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) return 0.0
    else {
        var polynom = p[0]
        for (i in 1 until p.size) {
            val element = p[i]
            polynom += element * x.pow(i)
        }
        return polynom
    }

}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size)
        list[i] += list[i - 1]
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */

fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var m = n
    while (m > 1) {
        list.add(minDivisor(m))
        m /= minDivisor(m)
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var list = listOf<Int>()
    var m = n
    while (m > 1) {
        list += minDivisor(m)
        m /= minDivisor(m)
    }
    return list.joinToString(separator = "*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var list = listOf<Int>()
    var m = n
    while (m >= base) {
        list += (m % base)
        m /= base
    }
    list += m
    return list.asReversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun numberTranslate(n: Int): String {
    if (n < 10)
        return n.toString()
    else
        return (n + 87).toChar().toString()
}

fun convertToString(n: Int, base: Int): String {
    val sb = StringBuilder()
    var m = n
    while (m >= base) {
        sb.append(numberTranslate(m % base))
        m /= base
    }
    sb.append(numberTranslate(m))
    return sb.toString().reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0
    for (i in 0..(digits.size - 1)) {
        number += powint(base, digits.size - 1 - i) * digits[i]
    }
    return number
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */

fun decimalFromString(str: String, base: Int): Int {
    var res = 0
    var letter = ""
    var number = 0
    if (str.length > 1) {
        for (i in 0 until str.length - 1) {
            letter += str[i]
            when (letter) {
                "a" -> number = 10
                "b" -> number = 11
                "c" -> number = 12
                "d" -> number = 13
                "e" -> number = 14
                "f" -> number = 15
                "g" -> number = 16
                "h" -> number = 17
                "i" -> number = 18
                "j" -> number = 19
                "k" -> number = 20
                "l" -> number = 21
                "m" -> number = 22
                "n" -> number = 23
                "o" -> number = 24
                "p" -> number = 25
                "q" -> number = 26
                "r" -> number = 27
                "s" -> number = 28
                "t" -> number = 29
                "u" -> number = 30
                "v" -> number = 31
                "w" -> number = 32
                "x" -> number = 33
                "y" -> number = 34
                "z" -> number = 35
                else -> number += letter.toInt()
            }
            res += number
            res *= base
            letter = ""
            number = 0
        }
        letter += str[str.length - 1]
        when (letter) {
            "a" -> number = 10
            "b" -> number = 11
            "c" -> number = 12
            "d" -> number = 13
            "e" -> number = 14
            "f" -> number = 15
            "g" -> number = 16
            "h" -> number = 17
            "i" -> number = 18
            "j" -> number = 19
            "k" -> number = 20
            "l" -> number = 21
            "m" -> number = 22
            "n" -> number = 23
            "o" -> number = 24
            "p" -> number = 25
            "q" -> number = 26
            "r" -> number = 27
            "s" -> number = 28
            "t" -> number = 29
            "u" -> number = 30
            "v" -> number = 31
            "w" -> number = 32
            "x" -> number = 33
            "y" -> number = 34
            "z" -> number = 35
            else -> number += letter.toInt()
        }
        res += number
    } else {
        letter += str
        when (letter) {
            "a" -> number = 10
            "b" -> number = 11
            "c" -> number = 12
            "d" -> number = 13
            "e" -> number = 14
            "f" -> number = 15
            "g" -> number = 16
            "h" -> number = 17
            "i" -> number = 18
            "j" -> number = 19
            "k" -> number = 20
            "l" -> number = 21
            "m" -> number = 22
            "n" -> number = 23
            "o" -> number = 24
            "p" -> number = 25
            "q" -> number = 26
            "r" -> number = 27
            "s" -> number = 28
            "t" -> number = 29
            "u" -> number = 30
            "v" -> number = 31
            "w" -> number = 32
            "x" -> number = 33
            "y" -> number = 34
            "z" -> number = 35
            else -> number += letter.toInt()
        }
        res += number
    }
    return res
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val str = StringBuilder()
    when (n / 1000) {
        1 -> str.append("M")
        2 -> str.append("MM")
        3 -> str.append("MMM")
    }
    when (n / 100 % 10) {
        1 -> str.append("C")
        2 -> str.append("CC")
        3 -> str.append("CCC")
        4 -> str.append("CD")
        5 -> str.append("D")
        6 -> str.append("DC")
        7 -> str.append("DCC")
        8 -> str.append("DCCC")
        9 -> str.append("CM")
    }
    when (n / 10 % 10) {
        1 -> str.append("X")
        2 -> str.append("XX")
        3 -> str.append("XXX")
        4 -> str.append("XL")
        5 -> str.append("L")
        6 -> str.append("LX")
        7 -> str.append("LXX")
        8 -> str.append("LXXX")
        9 -> str.append("XC")
    }
    when (n % 10) {
        1 -> str.append("I")
        2 -> str.append("II")
        3 -> str.append("III")
        4 -> str.append("IV")
        5 -> str.append("V")
        6 -> str.append("VI")
        7 -> str.append("VII")
        8 -> str.append("VIII")
        9 -> str.append("IX")
    }
    return str.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var str1 = ""
    var str2 = ""
    val n1 = n / 1000
    val n2 = n % 1000
    when (n1 / 100) {
        1 -> str1 += "сто "
        2 -> str1 += "двести "
        3 -> str1 += "триста "
        4 -> str1 += "четыреста "
        5 -> str1 += "пятьсот "
        6 -> str1 += "шестьсот "
        7 -> str1 += "семьсот "
        8 -> str1 += "восемьсот "
        9 -> str1 += "девятьсот "
    }
    when {
        n1 % 100 == 10 -> str1 += "десять "
        n1 % 100 == 11 -> str1 += "одиннадцать "
        n1 % 100 == 12 -> str1 += "двенадцать "
        n1 % 100 == 13 -> str1 += "тринадцать "
        n1 % 100 == 14 -> str1 += "четырнадцать "
        n1 % 100 == 15 -> str1 += "пятнадцать "
        n1 % 100 == 16 -> str1 += "шестнадцать "
        n1 % 100 == 17 -> str1 += "семнадцать "
        n1 % 100 == 18 -> str1 += "восемнадцать "
        n1 % 100 == 19 -> str1 += "девятнадцать "
        n1 % 100 / 10 == 2 -> str1 += "двадцать "
        n1 % 100 / 10 == 3 -> str1 += "тридцать "
        n1 % 100 / 10 == 4 -> str1 += "сорок "
        n1 % 100 / 10 == 5 -> str1 += "пятьдесят "
        n1 % 100 / 10 == 6 -> str1 += "шестьдесят "
        n1 % 100 / 10 == 7 -> str1 += "семьдесят "
        n1 % 100 / 10 == 8 -> str1 += "восемьдесят "
        n1 % 100 / 10 == 9 -> str1 += "девяносто "
    }
    when {
        (n1 % 10 == 1) && (n1 % 100 != 11) -> str1 += "одна "
        (n1 % 10 == 2) && (n1 % 100 != 12) -> str1 += "две "
        (n1 % 10 == 3) && (n1 % 100 != 13) -> str1 += "три "
        (n1 % 10 == 4) && (n1 % 100 != 14) -> str1 += "четыре "
        (n1 % 10 == 5) && (n1 % 100 != 15) -> str1 += "пять "
        (n1 % 10 == 6) && (n1 % 100 != 16) -> str1 += "шесть "
        (n1 % 10 == 7) && (n1 % 100 != 17) -> str1 += "семь "
        (n1 % 10 == 8) && (n1 % 100 != 18) -> str1 += "восемь "
        (n1 % 10 == 9) && (n1 % 100 != 19) -> str1 += "девять "
    }
    if (n1 % 10 == 1) str1 += "тысяча "
    if (((n1 % 10 == 2) && (n1 % 100 != 12)) || ((n1 % 10 == 3) && (n1 % 100 != 13)) || ((n1 % 10 == 4) && (n1 % 100 != 14))) str1 += "тысячи "
    else {
        if (((n1 > 0) && (n1 % 10 != 1) && (n1 % 10 != 2) && (n1 % 10 != 3) && (n1 % 10 != 4)) || (n1 % 100 in 5..20)) str1 += "тысяч "
    }
    when (n2 / 100) {
        1 -> str2 += "сто "
        2 -> str2 += "двести "
        3 -> str2 += "триста "
        4 -> str2 += "четыреста "
        5 -> str2 += "пятьсот "
        6 -> str2 += "шестьсот "
        7 -> str2 += "семьсот "
        8 -> str2 += "восемьсот "
        9 -> str2 += "девятьсот "
    }
    when {
        n2 % 100 == 10 -> str2 += "десять "
        n2 % 100 == 11 -> str2 += "одиннадцать "
        n2 % 100 == 12 -> str2 += "двенадцать "
        n2 % 100 == 13 -> str2 += "тринадцать "
        n2 % 100 == 14 -> str2 += "четырнадцать "
        n2 % 100 == 15 -> str2 += "пятнадцать "
        n2 % 100 == 16 -> str2 += "шестнадцать "
        n2 % 100 == 17 -> str2 += "семнадцать "
        n2 % 100 == 18 -> str2 += "восемнадцать "
        n2 % 100 == 19 -> str2 += "девятнадцать "
        n2 % 100 / 10 == 2 -> str2 += "двадцать "
        n2 % 100 / 10 == 3 -> str2 += "тридцать "
        n2 % 100 / 10 == 4 -> str2 += "сорок "
        n2 % 100 / 10 == 5 -> str2 += "пятьдесят "
        n2 % 100 / 10 == 6 -> str2 += "шестьдесят "
        n2 % 100 / 10 == 7 -> str2 += "семьдесят "
        n2 % 100 / 10 == 8 -> str2 += "восемьдесят "
        n2 % 100 / 10 == 9 -> str2 += "девяносто "
    }
    when {
        (n2 % 10 == 1) && (n2 % 100 != 11) -> str2 += "один "
        (n2 % 10 == 2) && (n2 % 100 != 12) -> str2 += "два "
        (n2 % 10 == 3) && (n2 % 100 != 13) -> str2 += "три "
        (n2 % 10 == 4) && (n2 % 100 != 14) -> str2 += "четыре "
        (n2 % 10 == 5) && (n2 % 100 != 15) -> str2 += "пять "
        (n2 % 10 == 6) && (n2 % 100 != 16) -> str2 += "шесть "
        (n2 % 10 == 7) && (n2 % 100 != 17) -> str2 += "семь "
        (n2 % 10 == 8) && (n2 % 100 != 18) -> str2 += "восемь "
        (n2 % 10 == 9) && (n2 % 100 != 19) -> str2 += "девять "
    }
    if (n2 > 0) {
        str2 = str2.substring(0, str2.length - 1)
    } else {
        str1 = str1.substring(0, str1.length - 1)
    }
    return str1 + str2
}