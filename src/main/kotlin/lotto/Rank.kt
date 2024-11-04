package lotto

enum class Rank(
    val matchCount: Int,
    val money: Int,
    val hasBonus: Boolean = false
) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    companion object {
        fun matchRank(matchCount: Int, isBonusMatch: Boolean): Rank {
            return when (matchCount) {
                6 -> FIRST
                5 -> if (isBonusMatch) SECOND else THIRD
                4 -> FOURTH
                3 -> FIFTH
                else -> NONE
            }
        }
    }
}