package mockito

class ValidNumber {
    fun check(o: Any?): Boolean {
        return if (o is Int) {
            o in 0..9
        } else {
            false
        }
    }
}