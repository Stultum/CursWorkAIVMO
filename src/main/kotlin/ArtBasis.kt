class ArtBasis(
    val zConstructorList: MutableList<Fraction>,
    xConstructorCount: Int,
    limitConstructorCount: Int,
    limitsConstructorList: MutableList<MutableList<Fraction>>
) {

    private val zList = mutableListOf<Fraction>()
    private val mList = mutableListOf<Fraction>()
    private val basisList = mutableListOf(6, 7, 8)
    private val simplexRelativeList = mutableListOf<Fraction>()
    private val alreadyUsedBasisList = mutableListOf<Int>()
    private var xCount = 0
    private var limitCount = 0
    private var limitsList = mutableListOf<MutableList<Fraction>>()
    private var currentMin = Triple(0, 0, Fraction(0))

    init {
        limitsList = limitsConstructorList
        limitCount = limitConstructorCount
        xCount = xConstructorCount

        zConstructorList.forEachIndexed { index, fraction ->
            zConstructorList[index] = zConstructorList[index].multiply(Fraction(-1))
        }
        with(zList) {
            add(Fraction(0, 1))
            addAll(zConstructorList)
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
        }

        with(mList) {
            for (i in 0..xCount) {
                var res = Fraction(0)
                for (j in 0 until limitCount) {
                    res = res.plus(limitsList[j][i])
                }
                add(res.multiply(Fraction(-1)))


            }
            add(Fraction(0, 1))
            add(Fraction(0, 1))
            add(Fraction(0, 1))
        }

        countSimplexRelatives()
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
            limitsList[0][0].toString(),
            limitsList[0][1].toString(),
            limitsList[0][2].toString(),
            limitsList[0][3].toString(),
            limitsList[0][4].toString(),
            limitsList[0][5].toString(),
            limitsList[0][6].toString(),
            limitsList[0][7].toString(),
            limitsList[0][8].toString(),
            if (simplexRelativeList[0].nominator != 99999) {
                simplexRelativeList[0].toString()
            } else {
                "---"
            }
        )
        print("\n")
        System.out.format(
            leftAlignFormat,
            "x" + basisList[1].toString(),
            limitsList[1][0].toString(),
            limitsList[1][1].toString(),
            limitsList[1][2].toString(),
            limitsList[1][3].toString(),
            limitsList[1][4].toString(),
            limitsList[1][5].toString(),
            limitsList[1][6].toString(),
            limitsList[1][7].toString(),
            limitsList[1][8].toString(),
            if (simplexRelativeList[1].nominator != 99999) {
                simplexRelativeList[1].toString()
            } else {
                "---"
            }
        )
        print("\n")
        System.out.format(
            leftAlignFormat,
            "x" + basisList[2].toString(),
            limitsList[2][0].toString(),
            limitsList[2][1].toString(),
            limitsList[2][2].toString(),
            limitsList[2][3].toString(),
            limitsList[2][4].toString(),
            limitsList[2][5].toString(),
            limitsList[2][6].toString(),
            limitsList[2][7].toString(),
            limitsList[2][8].toString(),
            if (simplexRelativeList[2].nominator != 99999) {
                simplexRelativeList[2].toString()
            } else {
                "---"
            }
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
        getMinElemParams()
    }

    fun countNewTable() {
        val tmp = basisList[currentMin.second]
        val list = mutableListOf<Fraction>()
        list.addAll(limitsList[currentMin.second])
        alreadyUsedBasisList.add(tmp)
        basisList[currentMin.second] = currentMin.first
        val newSimplexTable = mutableListOf<MutableList<Fraction>>()
        newSimplexTable.addAll(limitsList)
        for (i in 0 until limitCount) {
            val currentCoefficient =
                if (i == currentMin.second) currentMin.third else limitsList[i][currentMin.first].divide((currentMin.third))
            for (j in 0 until xCount + 3) {
                if (i == currentMin.second) {
                    newSimplexTable[i][j] = limitsList[i][j].divide(currentCoefficient)
                } else {
                    val minusValue = currentCoefficient.multiply(list[j])
                    newSimplexTable[i][j] = limitsList[i][j].minus(minusValue)
                }
            }
        }

        val newZList = mutableListOf<Fraction>()
        newZList.addAll(zList)
        val zListCoefficient = zList[currentMin.first].divide(currentMin.third)
        for (i in 0 until xCount + 3) {
            val minusValue = zListCoefficient.multiply(list[i])
            newZList[i] = zList[i].minus(minusValue)
        }

        val newMList = mutableListOf<Fraction>()
        newMList.addAll(mList)
        val mListCoefficient = mList[currentMin.first].divide(currentMin.third)
        for (i in 0 until xCount + 3) {
            val minusValue = mListCoefficient.multiply(list[i])
            newMList[i] = mList[i].minus(minusValue)
        }

        zList.clear()
        zList.addAll(newZList)
        newZList.clear()

        limitsList.clear()
        limitsList.addAll(newSimplexTable)
        newSimplexTable.clear()

        mList.clear()
        mList.addAll(newMList)
        val listToCheck = mutableListOf<Fraction>()
        listToCheck.addAll(newMList)
        newMList.clear()

        countSimplexRelatives()
        printSimplexTable()
        if (listToCheck.isContainsMinus()) {
            countNewTable()
        } else {
            val resultsPairs = mutableListOf<Pair<Int, Fraction>>()
            basisList.forEachIndexed { index, i ->
                resultsPairs.add(Pair(basisList[index], limitsList[index][0]))
            }
            val resultList = mutableListOf<Fraction>()
            for (i in 0 until xCount) {
                var isAdded = false
                resultsPairs.forEachIndexed { index, pair ->
                    if (i + 1 == pair.first) {
                        resultList.add(pair.second)
                        isAdded = true
                    }
                }
                if (!isAdded) {
                    resultList.add(Fraction(0))
                }
            }
            print("Zmin(")
            resultList.forEach {
                print(it.toString())
                print(",")
            }
            var answer = Fraction(0)
            zConstructorList.forEachIndexed { index, fraction ->
                zConstructorList[index] = zConstructorList[index].multiply(Fraction(-1))
            }
            resultList.forEachIndexed { index, fraction ->
                var plus = zConstructorList[index].multiply(fraction)
                plus = plus.multiply(Fraction(-1))
                answer = answer.plus(plus)
            }
            print(") = ")
            println(answer.toString())
        }
    }

    fun addBasisVariables() {
        for (i in 0 until limitCount) {
            val tmpInCycle = mutableListOf<Fraction>()
            for (j in 0 until limitCount) {
                if (j == i) {
                    tmpInCycle.add(Fraction(1))
                } else {
                    tmpInCycle.add(Fraction(0))
                }
            }
            limitsList[i].addAll(tmpInCycle)
            tmpInCycle.clear()
        }
    }

    private fun countSimplexRelatives() {
        simplexRelativeList.clear()
        with(simplexRelativeList) {
            val minIndex = mList.minValueLim().second
            for (i in 0 until limitCount) {
                if (limitsList[i][minIndex].nominator != 0 &&
                    limitsList[i][0].divide(limitsList[i][minIndex]).nominator > 0 &&
                    limitsList[i][0].divide(limitsList[i][minIndex]).denominator > 0
                ) {
                    add(limitsList[i][0].divide(limitsList[i][minIndex]))
                } else {
                    add(Fraction(99999))
                }
            }
        }
    }

    private fun getMinElemParams() {
        val minM = mList.minValueLim()
        val minSR = simplexRelativeList.minValue().second
        currentMin = Triple(minM.second, minSR, limitsList[minSR][minM.second])
    }
}