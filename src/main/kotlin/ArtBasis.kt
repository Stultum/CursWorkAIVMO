class ArtBasis(
    a: Fraction,
    b: Fraction,
    c: Fraction,
    a1: Fraction,
    b1: Fraction,
    c1: Fraction,
    a2: Fraction,
    b2: Fraction,
    c2: Fraction,
    p1: Fraction,
    p2: Fraction,
) {

    private val aList = mutableListOf<Fraction>()
    private val bList = mutableListOf<Fraction>()
    private val cList = mutableListOf<Fraction>()
    private val zList = mutableListOf<Fraction>()
    private val mList = mutableListOf<Fraction>()
    private val basisList = mutableListOf(6, 7, 8)
    private val alreadyUsedBasisList = mutableListOf<Int>()

    init {
        with(aList) {
            add(a)
            add(a1)
            add(a2)
            add(Fraction(-1, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(1, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
        }
        with(bList) {
            add(b)
            add(b1)
            add(b2)
            add(Fraction(0, 1))
            add(Fraction(-1, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(1, 1))
            add(Fraction(0, 1))
        }

        with(cList) {
            add(c)
            add(c1)
            add(c2)
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(-1, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(1, 1))
        }

        with(zList) {
            add(Fraction(0, 1))
            add(p1)
            add(p2)
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
        }

        with(mList) {
            for (i in 0 until 6) {
                val firstCopy = aList[i]
                val secondCopy = bList[i]
                val thirdCopy = cList[i]
                val result = firstCopy.plus(secondCopy).plus(thirdCopy)
                if (isEmpty()) {
                    add(result.multiply(Fraction(-1)))
                } else {
                    add(result)
                }
            }
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
        }

    }

    fun printSimplexTable() {
        val leftAlignFormat = "|%-5s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|"
        System.out.format("---------------------------------------------------------------------------------------")
        print("\n")
        System.out.format("| Б.П |   1   |   x1  |   x2  |   x3  |   x4  |   x5  |   x6  |   x7  |  x8   |   СО  |")
        print("\n")
        System.out.format(
            leftAlignFormat,
            "x" + basisList[0].toString(),
            aList[0].toString(),
            aList[1].toString(),
            aList[2].toString(),
            aList[3].toString(),
            aList[4].toString(),
            aList[5].toString(),
            aList[6].toString(),
            aList[7].toString(),
            aList[8].toString(),
            "0"
        )
        print("\n")
        System.out.format(
            leftAlignFormat,
            "x" + basisList[1].toString(),
            bList[0].toString(),
            bList[1].toString(),
            bList[2].toString(),
            bList[3].toString(),
            bList[4].toString(),
            bList[5].toString(),
            bList[6].toString(),
            bList[7].toString(),
            bList[8].toString(),
            "0"
        )
        print("\n")
        System.out.format(
            leftAlignFormat,
            "x" + basisList[2].toString(),
            cList[0].toString(),
            cList[1].toString(),
            cList[2].toString(),
            cList[3].toString(),
            cList[4].toString(),
            cList[5].toString(),
            cList[6].toString(),
            cList[7].toString(),
            cList[8].toString(),
            "0"
        )
        print("\n")
        System.out.format(
            leftAlignFormat,
            "Z",
            zList[0].toString(),
            zList[1].toString(),
            zList[2].toString(),
            zList[3].toString(),
            zList[4].toString(),
            zList[5].toString(),
            zList[6].toString(),
            zList[7].toString(),
            zList[8].toString(),
            "-"
        )
        print("\n")
        System.out.format(
            leftAlignFormat,
            "M",
            mList[0].toString(),
            mList[1].toString(),
            mList[2].toString(),
            mList[3].toString(),
            mList[4].toString(),
            mList[5].toString(),
            mList[6].toString(),
            mList[7].toString(),
            mList[8].toString(),
            "-"
        )
        print("\n")
        System.out.format("---------------------------------------------------------------------------------------")
        print("\n")
        println(zList.isContainsMinus().toString())
    }

    fun countNewTable() {
        val newAList = mutableListOf<Fraction>()
        val newBList = mutableListOf<Fraction>()
        val newCList = mutableListOf<Fraction>()
        val newZList = mutableListOf<Fraction>()
        val newMList = mutableListOf<Fraction>()
    }
}