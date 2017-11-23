The program was implemented utilising a combination of the bottom-up approach and the top-down
approach in the class-based programming paradigm. The design is focused on the bridge pattern 
as the ranking process is decoupled from the PokerHand class, in order to avoid excess of
information. 

PokerHand: The class has been extended to compare the two hands by converting the string objects
into the corresponding int values. 

Two classes are created: 

Rank: Performs the comparison of two hands based on the corresponding category of each.
i.e.: If the first hand belongs to Full House and the second hand belongs to Two Pairs,
The first hand will have a higher rank (represented by an int value) than the second hand
(represented by a lowe value) and the winning criteria is made by a conditional.

Rank_Tie: This is a subclass of Rank, in the case the two hands belong to the same category
(resulting in a tie), methods are performed to obtain the highest card value for each hand
and make the comparison, once more, to determine the winner.    

Note: The program includes a javadoc, it details briefly the functionalities
of the different implemented methods to give a more appropriate specification.