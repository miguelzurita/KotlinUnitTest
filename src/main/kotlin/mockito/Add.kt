package mockito

class Add(var validNumber: ValidNumber) {
    fun add(a: Any, b: Any): Int {
        return if (validNumber.check(a) && validNumber.check(b)) {
            a as Int + b as Int
        } else {
            0
        }
    }

    fun addInt(a: Any): Int {
        return validNumber.doubleToInt(a) * 2
    }

}