fun main() {
    val aList = mutableListOf(
        Fraction(13),
        Fraction(2),
        Fraction(3),
        Fraction(-1),
        Fraction(0),
        Fraction(0)
    )
    val bList = mutableListOf(
        Fraction(12),
        Fraction(1),
        Fraction(7),
        Fraction(0),
        Fraction(-1),
        Fraction(0),
    )
    val cList = mutableListOf(
        Fraction(11),
        Fraction(4),
        Fraction(1),
        Fraction(0),
        Fraction(0),
        Fraction(-1),
    )
    val zList = mutableListOf(
        Fraction(-1),
        Fraction(-11),
        Fraction(0),
        Fraction(0),
        Fraction(0)
    )
    val limits = mutableListOf(aList, bList, cList)
    val artBasis = ArtBasis(
        zList,
        5,
        3,
        limits
    )
    artBasis.addBasisVariables()
    artBasis.printSimplexTable()
    artBasis.countNewTable()
    artBasis.printSimplexTable()
}