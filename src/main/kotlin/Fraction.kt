import kotlin.math.absoluteValue

class Fraction(nominator: Int, denominator: Int = 1) {

    var nominator: Int = 0
    var denominator: Int = 0

    init {
        this.nominator = nominator
        this.denominator = denominator
        reduce()
    }

    private fun gCD(firstNumber: Int, secondNumber: Int): Int {
        if (secondNumber == 0) {
            throw IllegalArgumentException("Деление на 0")
        }
        var first = firstNumber.absoluteValue
        var second = secondNumber.absoluteValue
        while (first != second) {
            if (first > second) {
                first -= second
            } else {
                second -= first
            }
        }
        return first
    }

    private fun reduce() {
        if (nominator == 0) {
            denominator = 1
            return
        }
        val gcd = gCD(nominator, denominator)
        nominator /= gcd
        denominator /= gcd
    }

    fun plus(second: Fraction): Fraction {
        val gcd = gCD(denominator, second.denominator)
        val firstTmp = Fraction(nominator * (denominator / gcd), denominator * (denominator / gcd))
        val secondTmp =
            Fraction(second.nominator * (second.denominator / gcd), second.denominator * (second.denominator / gcd))
        val result = Fraction(firstTmp.nominator + secondTmp.nominator, firstTmp.denominator)
        result.reduce()
        return result
    }

    fun minus(second: Fraction): Fraction {
        val gcd = gCD(denominator, second.denominator)
        val firstTmp = Fraction(nominator * (denominator / gcd), denominator * (denominator / gcd))
        val secondTmp =
            Fraction(second.nominator * (second.denominator / gcd), second.denominator * (second.denominator / gcd))
        val result = Fraction(firstTmp.nominator - secondTmp.nominator, firstTmp.denominator)
        result.reduce()
        return result
    }

    fun multiply(second: Fraction): Fraction {
        val result = Fraction(nominator * second.nominator, denominator * second.denominator)
        result.reduce()
        return result
    }

    fun divide(second: Fraction): Fraction {
        if (second.nominator == 0) {
            throw IllegalArgumentException("Деление на 0")
        }
        val result = Fraction(nominator * second.denominator, denominator * second.nominator)
        result.reduce()
        return result
    }

    override fun toString(): String = if (denominator != 1) "$nominator/$denominator" else "$nominator"
}

fun MutableList<Fraction>.minValue(): Pair<Fraction, Int> {
    val fractionList = this.map { it.nominator / it.denominator }.drop(1)
    return Pair(this[fractionList.binarySearch(fractionList.minOrNull()!!)], fractionList.binarySearch(fractionList.minOrNull()!!))
}

fun MutableList<Fraction>.isContainsMinus(): Boolean {
    val fractionList = this.map { it.nominator / it.denominator }.drop(1)
    fractionList.forEach {
        if (it < 0) {
            return true
        }
    }
    return false
}