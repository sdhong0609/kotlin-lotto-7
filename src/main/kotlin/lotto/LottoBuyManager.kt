package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

class LottoBuyManager {

    var money: Int = 0
    var userLottos = mutableListOf<List<Int>>()

    fun requestMoney() {
        println(REQUEST_MONEY_MESSAGE)
        val moneyInput = Console.readLine()
        try {
            validateMoneyInput(moneyInput)
            println()
        } catch (e: IllegalArgumentException) {
            println(e.message)
            println()
            requestMoney()
        }
    }

    private fun validateMoneyInput(moneyInput: String) {
        if (!moneyInput.matches(Regex(REGEX_NUMBER_PATTERN))) {
            throw IllegalArgumentException("$ERROR_TITLE $ERROR_NOT_NUMBER_MESSAGE")
        }

        money = moneyInput.toInt()

        if (money < LOTTO_PRICE) {
            throw IllegalArgumentException("$ERROR_TITLE $ERROR_MINIMUM_AMOUNT_MESSAGE")
        }

        if (money % LOTTO_PRICE != 0) {
            throw IllegalArgumentException("$ERROR_TITLE $ERROR_UNIT_AMOUNT_MESSAGE")
        }
    }

    fun buyLottos() {
        val lottoCount = money / LOTTO_PRICE
        println("${lottoCount}${BUY_LOTTOS_MESSAGE}")
        repeat(lottoCount) {
            val numbers = Randoms.pickUniqueNumbersInRange(LOTTO_RANGE_START, LOTTO_RANGE_END, LOTTO_COUNT).sorted()
            println(numbers)
            userLottos += numbers
        }
        println()
    }

    companion object {
        private const val REQUEST_MONEY_MESSAGE = "구입금액을 입력해 주세요."
        private const val BUY_LOTTOS_MESSAGE = "개를 구매했습니다."
        private const val LOTTO_PRICE = 1000
        private const val LOTTO_RANGE_START = 1
        private const val LOTTO_RANGE_END = 45
        private const val LOTTO_COUNT = 6
        private const val REGEX_NUMBER_PATTERN = "^[0-9]+\$"
        private const val ERROR_TITLE = "[ERROR]"
        private const val ERROR_NOT_NUMBER_MESSAGE = "금액은 숫자여야 합니다."
        private const val ERROR_MINIMUM_AMOUNT_MESSAGE = "로또 구입금액은 1000원 이상이어야 합니다."
        private const val ERROR_UNIT_AMOUNT_MESSAGE = "로또 구입금액은 1000원 단위여야 합니다."
    }
}