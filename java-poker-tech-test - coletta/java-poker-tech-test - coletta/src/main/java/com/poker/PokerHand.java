package com.poker;

import com.poker.Ranking.Ranking_Categories;
import static com.poker.Ranking.Ranking_Categories.*;

/**
 * PokerHand contains the methods that perform the ranking of the two hands. It
 * verifies the highest card in the case of a tie for the appropriate category.
 *
 * @author Claudio
 */
public class PokerHand {

    private final String cards;
    private final Ranking rank;
    private final Ranking_Tie rankTie;

    public enum Result {

        WIN,
        LOSS,
        TIE;
    }

    public PokerHand(String hand) {
        this.rank = new Ranking();
        this.rankTie = new Ranking_Tie();
        this.cards = hand;
    }

    /**
     * This method compares the two hands by getting the rank of each hand. The
     * comparison of the hands is made by the numeric values of the categories
     * of the Ranking in the enum class: High Card has a value of 1 whereas
     * Royal Flush has a value of 10. If the two hands belong to the same
     * category, it is proceeded to perform comparisons based on the criteria of
     * the category.
     *
     * @param hand
     * @return
     */
    public Result compareWith(PokerHand hand) {
        String cards_hand_1 = cards;
        Ranking_Categories rank_hand_1;
        String cards_hand_2 = hand.cards;
        Ranking_Categories rank_hand_2;

        if (cards_hand_1 == null || cards_hand_2 == null) {
            throw new IllegalArgumentException(cards, null);
        }
        
        rank_hand_1 = rank.rankHand(cards_hand_1);
        rank_hand_2 = rank.rankHand(cards_hand_2);

        if (rank_hand_1.ordinal() > rank_hand_2.ordinal()) {

            return Result.WIN;
        }
        if (rank_hand_1.ordinal() < rank_hand_2.ordinal()) {
            return Result.LOSS;
        }
        if (rank_hand_1.ordinal() == rank_hand_2.ordinal()) {
            int cardsNumbers_hand1[];
            int cardsNumbers_hand2[];
            int high_card_hand_1;
            int high_card_hand_2;

            switch (rank_hand_1) {
                case STRAIGHT_FLUSH:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);

                    if (cardsNumbers_hand1[4] > cardsNumbers_hand2[4]) {
                        return Result.WIN;
                    } else {
                        return Result.LOSS;
                    }
                case FOUR_OF_A_KIND:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);

                    high_card_hand_1 = rankTie.getHighCard(cardsNumbers_hand1, FOUR_OF_A_KIND);
                    high_card_hand_2 = rankTie.getHighCard(cardsNumbers_hand2, FOUR_OF_A_KIND);

                    if (high_card_hand_1 > high_card_hand_2) {
                        return Result.WIN;
                    } else {
                        return Result.LOSS;
                    }
                case FULL_HOUSE:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);

                    high_card_hand_1 = rankTie.getHighCard(cardsNumbers_hand1, FULL_HOUSE);
                    high_card_hand_2 = rankTie.getHighCard(cardsNumbers_hand2, FULL_HOUSE);

                    if (high_card_hand_1 > high_card_hand_2) {
                        return Result.WIN;
                    } else {
                        return Result.LOSS;
                    }
                case FLUSH:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);
                    for (int i = cardsNumbers_hand1.length - 1; i > 0; i--) {
                        if (cardsNumbers_hand1[i] != cardsNumbers_hand2[i]) {
                            if (cardsNumbers_hand1[i] > cardsNumbers_hand2[i]) {
                                return Result.WIN;
                            } else {
                                return Result.LOSS;
                            }
                        } else {
                            return Result.TIE;
                        }
                    }
                    break;
                case STRAIGHT:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);
                    for (int i = cardsNumbers_hand1.length - 1; i > 0; i--) {
                        if (cardsNumbers_hand1[i] != cardsNumbers_hand2[i]) {
                            if (cardsNumbers_hand1[i] > cardsNumbers_hand2[i]) {
                                return Result.WIN;
                            } else {
                                return Result.LOSS;
                            }
                        }
                    }
                    break;
                case THREE_OF_A_KIND:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);

                    high_card_hand_1 = rankTie.getHighCard(cardsNumbers_hand1, THREE_OF_A_KIND);
                    high_card_hand_2 = rankTie.getHighCard(cardsNumbers_hand2, THREE_OF_A_KIND);

                    if (high_card_hand_1 > high_card_hand_2) {
                        return Result.WIN;
                    } else {
                        return Result.LOSS;
                    }
                case TWO_PAIRS:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);
                    high_card_hand_1 = rankTie.getHighCard(cardsNumbers_hand1, TWO_PAIRS);
                    high_card_hand_2 = rankTie.getHighCard(cardsNumbers_hand2, TWO_PAIRS);

                    if (high_card_hand_1 > high_card_hand_2) {
                        return Result.WIN;
                    } else {
                        return Result.LOSS;
                    }
                case PAIR:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);
                    high_card_hand_1 = rankTie.getHighCard(cardsNumbers_hand1, PAIR);
                    high_card_hand_2 = rankTie.getHighCard(cardsNumbers_hand2, PAIR);
                    if (high_card_hand_1 > high_card_hand_2) {
                        return Result.WIN;
                    } else {
                        return Result.LOSS;
                    }
                case HIGH_CARD:
                    cardsNumbers_hand1 = rank.sortHand(cards_hand_1);
                    cardsNumbers_hand2 = rank.sortHand(cards_hand_2);
                    high_card_hand_1 = cardsNumbers_hand1[4];
                    high_card_hand_2 = cardsNumbers_hand2[4];
                    if (high_card_hand_1 != high_card_hand_2) {
                        if (high_card_hand_1 > high_card_hand_2) {
                            return Result.WIN;
                        } else {
                            return Result.LOSS;
                        }
                    }
            }
        }

        return Result.TIE;

    }

}
