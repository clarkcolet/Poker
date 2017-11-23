package com.poker;

/**
 * Ranking_Tie is a subclass of Ranking. It is implemented to obtain the highest
 * card in the case of a tie. The criteria for declaring the winner is based on the source:
 * https://www.pokerstars.uk/poker/games/rules/hand-rankings/?no_redirect=1
 * @author Claudio
 */
public class Ranking_Tie extends Ranking {
    /**
     * getHighCard is a function that obtains the highest value card
     * according to the category criteria.
     * @param cards
     * @param rank
     * @return 
     */
      public int getHighCard(int cards[], Ranking_Categories rank)
        {
           /**
           * The winner of the same category class is that with the highest
           * card in the classified category. For example, for Four of a kind,
           * the winner is the player with the highest repeated card.
           * A comparison between the cards is performed to obtain the value
           * for one hand
           */
            switch(rank)
            {
                case FOUR_OF_A_KIND:
                   if(cards[0] == cards[1])
                     {
                    return cards[0];
                     }
                     else
                    {
                    return cards[4];
                    }
                case FULL_HOUSE:
                case THREE_OF_A_KIND:
                   if(cards[0] == cards[1] && cards[0] == cards [2])
                   {
                       return cards[0];
                   }
                    else
                   {
                       return cards[4];
                   }
                case TWO_PAIRS:
                //Two denomination left
                 if(cards[0] == cards[1] && cards[2] == cards[3])
                 {
                    if(cards[0] > cards[3])
                    {
                        return cards[0];
                    }
                    else
                    {
                        return cards[3];
                    }
                  }
                 //Two denomination right
                 if(cards[1] == cards[2] && cards[3] == cards[4])
                {
                    if(cards[1] > cards[3])
                    {
                        return cards[1];
                    }
                    else
                    {
                        return cards[3];
                    }
              }
            //Two denomination different middle
            if(cards[0] == cards[1] && cards[3] == cards[4])
            {
                    if(cards[0] > cards[4])
                    {
                        return cards[0];
                    }
                    else
                    {
                        return cards[4];
                    }
            }
                case PAIR:
                    if(cards[0] == cards [1])
                    {
                        return cards[0];
                    }
                   if(cards[1] == cards [2])
                    {
                        return cards[1];
                    }
                   if(cards[2] == cards [3])
                    {
                        return cards[2];
                    }
                    if(cards[3] == cards [4])
                    {
                        return cards[3];
                    }
            }
            return 0;
        }
}
