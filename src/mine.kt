fun powint(n: Int, i: Int): Int {
    var m = 1
    if (i == 0) return 1
    else {
        for (j in 1..i) {
            m *= n
        }
        return m
    }
}