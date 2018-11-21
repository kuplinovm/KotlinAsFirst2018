fun powint(n: Int, i: Int): Int {
    var m = 1
    return if (i == 0) 1
    else {
        for (j in 1..i) {
            m *= n
        }
        m
    }
}

fun powdouble(n: Double, i: Double): Double {
    var m = 1.0
    return if (i == 0.0) 1.0
    else {
        for (j in 1..i.toInt()) {
            m *= n
        }
        m
    }
}

fun sum(list: List<Double>): Double {
    var sum = 0.0
    for (element in list) {
        sum += element
    }
    return sum
}