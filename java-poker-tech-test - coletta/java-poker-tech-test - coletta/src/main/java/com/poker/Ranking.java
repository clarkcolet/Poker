/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poker;

import static java.lang.Character.getNumericValue;
import java.util.Arrays;

/**
 * Ranking is the class that contains the necessary information to perform
 * classification of the cards and place them in a category.
 *
 * @author Claudio
 */
public class Ranking {

    public enum Ranking_Categories {

        HIGH_CARD,
        PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH,
        ROYAL_FLUSH;
    }

    /**
     * This method performs the ranking of a hand by recurring to additional
     * methods that obtain whether the hand belongs to the category (true) or
     * not (false). The method relies on returning values to avoid entering each
     * category once successful
     *
     * @param cards
     * @return
     */
    public Ranking_Categories rankHand(String cards) {
        if (royalFlush(cards) == true) {
            System.out.println("RETURNING ROYAL FLUSH");
            return Ranking_Categories.ROYAL_FLUSH;
        }
        if (straightFlush(cards) == true) {
            System.out.println("RETURNING STRAIGHT FLUSH");
            return Ranking_Categories.STRAIGHT_FLUSH;
        }
        if (fourOfAKind(cards) == true) {
            System.out.println("REUTRNING FOUR OF A KIND");
            return Ranking_Categories.FOUR_OF_A_KIND;
        }
        if (fullHouse(cards) == true) {
            System.out.println("RETURNING FULL HOUSE");
            return Ranking_Categories.FULL_HOUSE;
        }
        if (flush(cards) == true) {
            System.out.println("RETURNING FLUSH");
            return Ranking_Categories.FLUSH;
        }
        if (straight(cards) == true) {
            System.out.println("RETURNING STRAIGHT");
            return Ranking_Categories.STRAIGHT;
        }
        if (threeOfAKind(cards) == true) {
            System.out.println("RETURNING THREE OF A KIND");
            return Ranking_Categories.THREE_OF_A_KIND;
        }
        if (twoPairs(cards) == true) {
            System.out.println("RETURNING TWO PAIRS");
            return Ranking_Categories.TWO_PAIRS;
        }
        if (pair(cards) == true) {
            System.out.println("RETURNING PAIR");
            return Ranking_Categories.PAIR;
        }
        return Ranking_Categories.HIGH_CARD;
    }

    /**
     * Royal Flush: Ten, Jack, Queen, King and Ace in the same suit First, the
     * cards are verified to be in the same suit. If they meet this condition,
     * the method proceeds to sort the hand in ascending values after converting
     * the letter cards (T,J,Q,K,A) into the corresponding values.
     *
     * @param cards
     * @return
     */
    private boolean royalFlush(String cards) {
        int cardsNumbers[] = new int[5];
        int cardNumber;
        int optionConvert = 1;

        //Verify if same suit
        if (cards.charAt(1) == cards.charAt(4) && cards.charAt(1) == cards.charAt(7)
                && cards.charAt(1) == cards.charAt(10) && cards.charAt(1) == cards.charAt(13)) {
            //Verify required numbers
            for (int i = 0; i < 5; i++) {
                cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
                cardsNumbers[i] = cardNumber;
            }
            Arrays.sort(cardsNumbers);
            //Royal Flush
            if (cardsNumbers[0] == 10 && cardsNumbers[4] == 14) {
                return true;
            }
            //
        }
        //
        return false;
    }

    /**
     * Five cards in sequence, all the same suit. First, the cards are verified
     * to be in the same suit. If they meet this condition, the method proceeds
     * to sort the hand in ascending values after converting the letter cards
     * (T,J,Q,K,A) into the corresponding values. It must be noted that a flush
     * has the characteristic that if the cards are 2, 3, 4, 5, the A value
     * becomes 1 instead of 14
     *
     * @param cards
     * @return
     */
    private boolean straightFlush(String cards) {
        int cardsNumbers[] = new int[5];
        int cardNumber;
        boolean sequence = false;
        int optionConvert = 1;
        //Verify if same suit
        if (cards.charAt(1) == cards.charAt(4) && cards.charAt(1) == cards.charAt(7)
                && cards.charAt(1) == cards.charAt(10) && cards.charAt(1) == cards.charAt(13)) {
            //Verify required numbers
            if (cards.indexOf('2') != -1 && cards.indexOf('3') != -1 && cards.indexOf('4') != -1 && cards.indexOf('5') != -1) {
                optionConvert = 2;
            }
            for (int i = 0; i < 5; i++) {
                cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
                cardsNumbers[i] = cardNumber;
            }
            Arrays.sort(cardsNumbers);
            //Straight Flush
            for (int i = 0; i < cardsNumbers.length - 1; i++) {
                if (cardsNumbers[i] + 1 == cardsNumbers[i + 1]) {
                    sequence = true;
                } else {
                    return false;
                }
            }
            if (sequence == true) {
                return true;
            }
            //
        }
        return false;
    }

    /**
     * Four cards of the same denomination This category is a simple one as suit
     * does not need to be verified, relying on comparison of the values making
     * sure the 'odd' card can be place in one of three places: left, middle,
     * right.
     *
     * @param cards
     * @return
     */
    private boolean fourOfAKind(String cards) {

        int cardsNumbers[] = new int[5];
        int cardNumber;
        int optionConvert = 1;
        for (int i = 0; i < 5; i++) {
            cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
            cardsNumbers[i] = cardNumber;
        }
        Arrays.sort(cardsNumbers);
        //Four of a kind
        if ((cardsNumbers[0] == cardsNumbers[1] && cardsNumbers[0] == cardsNumbers[2]
                && cardsNumbers[0] == cardsNumbers[3])
                || (cardsNumbers[1] == cardsNumbers[2] && cardsNumbers[1] == cardsNumbers[3]
                && cardsNumbers[1] == cardsNumbers[4])) {
            return true;
        }
        return false;
    }

    /**
     * Three cards of one denomination and two cards of another denomination.
     * The cards are verified by relying on the nesting to meet the requirement
     * criteria.
     *
     * @param cards
     * @return
     */
    private boolean fullHouse(String cards) {

        int cardsNumbers[] = new int[5];
        int cardNumber;
        int optionConvert = 1;

        for (int i = 0; i < 5; i++) {
            cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
            cardsNumbers[i] = cardNumber;
        }
        Arrays.sort(cardsNumbers);

        //Three denomination left
        if (cardsNumbers[0] == cardsNumbers[1] && cardsNumbers[0] == cardsNumbers[2]) {
            if (cardsNumbers[3] == cardsNumbers[4]) {
                return true;
            }
        }
        //Three denomination right
        if (cardsNumbers[2] == cardsNumbers[3] && cardsNumbers[2] == cardsNumbers[4]) {
            if (cardsNumbers[0] == cardsNumbers[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Five cards all in the same suit The cards are verified to contain the
     * same letter. Letters are predefined to be in the positions 1, 4, 7, 10,
     * 13
     *
     * @param cards
     * @return
     */
    private boolean flush(String cards) {
        //Verify if same suit
        if (cards.charAt(1) == cards.charAt(4) && cards.charAt(1) == cards.charAt(7)
                && cards.charAt(1) == cards.charAt(10) && cards.charAt(1) == cards.charAt(13)) {
            return true;
        }
        return false;
    }

    /**
     * Straight: Five cards of any suit in any sequence
     *
     * @param cards
     * @return
     */
    private boolean straight(String cards) {
        int cardsNumbers[] = new int[5];
        int cardNumber;
        boolean sequence = false;
        int optionConvert = 1;

        for (int i = 0; i < 5; i++) {
            if (cards.indexOf('2') != -1 && cards.indexOf('3') != -1 && cards.indexOf('4') != -1 && cards.indexOf('5') != -1) {
                optionConvert = 2;
            }
            cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
            cardsNumbers[i] = cardNumber;
        }
        Arrays.sort(cardsNumbers);
        for (int i = 0; i < cardsNumbers.length - 1; i++) {
            if (cardsNumbers[i] + 1 == cardsNumbers[i + 1]) {
                sequence = true;
            } else {
                return false;
            }
        }
        if (sequence == true) {
            return sequence;
        }

        return false;
    }

    /**
     * Three cards of the same denomination and two unmatched cards Same process
     * as Full House but without verifying the two same denomination card
     *
     * @param cards
     * @return
     */
    private boolean threeOfAKind(String cards) {
        int cardsNumbers[] = new int[5];
        int cardNumber;
        int optionConvert = 1;

        for (int i = 0; i < 5; i++) {
            cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
            cardsNumbers[i] = cardNumber;
        }
        Arrays.sort(cardsNumbers);

        //Three denomination left
        if (cardsNumbers[0] == cardsNumbers[1] && cardsNumbers[0] == cardsNumbers[2]) {
            return true;
        }
        //Three denomination middle
        if (cardsNumbers[1] == cardsNumbers[2] && cardsNumbers[1] == cardsNumbers[3]) {
            return true;
        }
        //Three denomination right
        if (cardsNumbers[2] == cardsNumbers[3] && cardsNumbers[2] == cardsNumbers[4]) {
            return true;
        }
        return false;
    }

    /**
     * Two cards of the same denomination and any three unmatched cards There
     * are several conditionals performed to identify the different positions
     * being based on the different card.
     *
     * @param cards
     * @return
     */
    private boolean twoPairs(String cards) {
        int cardsNumbers[] = new int[5];
        int cardNumber;
        int optionConvert = 1;

        for (int i = 0; i < 5; i++) {
            cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
            cardsNumbers[i] = cardNumber;
        }
        Arrays.sort(cardsNumbers);
        //Two denomination left
        if (cardsNumbers[0] == cardsNumbers[1] && cardsNumbers[2] == cardsNumbers[3]) {
            return true;
        }
        //Two denomination right
        if (cardsNumbers[1] == cardsNumbers[2] && cardsNumbers[3] == cardsNumbers[4]) {
            return true;
        }
        //Two denomination different middle
        if (cardsNumbers[0] == cardsNumbers[1] && cardsNumbers[3] == cardsNumbers[4]) {
            return true;
        }
        return false;
    }

    /**
     *
     * Two sets of two cards of the same denomination and any fifth card Same
     * criteria use in Full House without verifying three-same denomination
     * cards
     *
     * @param cards
     * @return
     */
    private boolean pair(String cards) {
        int cardsNumbers[] = new int[5];
        int cardNumber;
        int optionConvert = 1;

        for (int i = 0; i < 5; i++) {
            cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
            cardsNumbers[i] = cardNumber;
        }
        Arrays.sort(cardsNumbers);
        if (cardsNumbers[0] == cardsNumbers[1] || cardsNumbers[1] == cardsNumbers[2]
                || cardsNumbers[2] == cardsNumbers[3]
                || cardsNumbers[3] == cardsNumbers[4]) {
            return true;
        }
        return false;
    }

    /**
     * This method converts the letter cards to numeric values in order to be
     * able to perform comparisons. Additionally, the string values for the
     * numbers are turned to the type int.
     *
     * convertToNumber has two options as "A" has a value of 14 unless the
     * Straight Flush or Flush contain the cards "2", "3", "4", "5", where "A"
     * has a value of 1.
     *
     * @param card
     * @param option
     * @return
     */
    private int convertToNumber(char card, int option) {
        int card_number = getNumericValue(card);
        if (option == 1) {
            switch (card) {
                case 'T':
                    card_number = 10;
                    break;
                case 'J':
                    card_number = 11;
                    break;
                case 'Q':
                    card_number = 12;
                    break;
                case 'K':
                    card_number = 13;
                    break;
                case 'A':
                    card_number = 14;
            }
        } else {
            switch (card) {
                case 'T':
                    card_number = 10;
                    break;
                case 'J':
                    card_number = 11;
                    break;
                case 'Q':
                    card_number = 12;
                    break;
                case 'K':
                    card_number = 13;
                    break;
                case 'A':
                    card_number = 1;
            }
        }
        return card_number;
    }

    /**
     * This method sorts the hands in ascending values. It verifies the
     * condition for the appropriate conversion from string to int.
     *
     * @param cards
     * @return
     */
    public int[] sortHand(String cards) {
        int cardsNumbers[] = new int[5];
        int cardNumber;
        int optionConvert = 1;

        if (cards.indexOf('2') != -1 && cards.indexOf('3') != -1 && cards.indexOf('4') != -1 && cards.indexOf('5') != -1) {
            optionConvert = 2;
        }

        for (int i = 0; i < 5; i++) {
            cardNumber = convertToNumber(cards.charAt(i * 3), optionConvert);
            cardsNumbers[i] = cardNumber;
        }
        Arrays.sort(cardsNumbers);
        return cardsNumbers;
    }
}
