package hand.implementation;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;

/**
 * Utility class to sort and analyse Hand
 * @author Rory Buckley
 *
 */
public class HandUtils {
    /**
     * Utility method to count the number of cards of a particular Face Value
     * @return counter Counter keeps track of the number of cards of a particular Face Value
     */
    static HashMap<Integer, Integer> faceCount(PlayingCard[] hand){
      HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
      
      for (PlayingCard card : hand){
        Integer facevalue = card.getFaceVal();
        
        if (counter.containsKey(facevalue)){
          Integer currentcount = counter.get(facevalue);
          counter.put(facevalue, new Integer(currentcount + 1));
        }
        else {
          counter.put(facevalue, new Integer(1));
        }
      }
      return counter;
    }
    
    /**
     * Determines the maximum number of cards with the same facevalue
     * @param counter Counter keeps track of the number of cards of a particular Face Value
     * @return
     */
    static int maxCount(HashMap<Integer, Integer> counter){
      Stack<Integer> max = new Stack<Integer>();
      
      for (Integer count : counter.values()){
        if (max.isEmpty()){
          max.push(count);
        }
        else if (count > max.peek()){
          max.pop();
          max.push(count);
        }
      }
      return max.pop();
    }
    
    /**
     * Utility method to determine the number of pairs in the Hand
     * @return paircount The number of pairs in the Hand
     */
    static int pairCount(HashMap<Integer, Integer> counter){
      int paircount = 0;
      
      for (Integer count : counter.values()){
        if (count == 2){
          paircount++;
        }
      }  
      return paircount;
    }
    
    /**
     * Utility method to determine if the hand contains a Flush
     * @return true if hand contains a flush
     */
    static boolean containsFlush(PlayingCard[] hand){
      boolean hasflush = true;
      char suit = hand[0].getSuit();
      
      for (int i = 1; i < hand.length; i++){
        if (hand[i].getSuit() != suit){
          hasflush = false;
        }
      }
      return hasflush;
    }
    
    /**
     * Utility method to determine if the hand contains a Straight
     * @return true if the hand contains a straight
     */
    static boolean containsStraight(PlayingCard[] hand){
      boolean hasstraight = true;
      int lastvalue = hand[0].getFaceVal();
      
      int i = 1;
      while (i < hand.length && hasstraight){
        int facevalue = hand[i].getFaceVal();
        if (facevalue + 1 != lastvalue){
          hasstraight = false;
        }
        else{
          lastvalue = facevalue;
          i++;
        }
      }
      return hasstraight;
    }
    
    /**
     * Utility method to determine if the hand contains a Royal Straight
     * @return true if the hand contains a royal straight
     */
    static boolean containsRoyalStraight(PlayingCard[] hand){
      boolean hasroyalstraight = (hand[4].getGameVal() == 14);
      if (hasroyalstraight){
        int lastvalue = hand[4].getGameVal();
        
        int i = 0;
        while (i < hand.length - 1 && hasroyalstraight){
          int gamevalue = hand[i].getGameVal();
          if (gamevalue + 1 != lastvalue){
            hasroyalstraight = false;
          }
          else{
            lastvalue = gamevalue;
            i++;
          }
        }
      }
      return hasroyalstraight;
    }
    
    /**
     * Sort sorts the hand of cards dealt from the deck
     */
    static PlayingCard[] sort(PlayingCard[] hand){
      for (int i = 0; i < hand.length; i++){
        Stack<Integer> maxtomin = new Stack<Integer>();
        for (int j = i; j < hand.length; j++){
          if (maxtomin.isEmpty()){
            maxtomin.push(j);
          }
          else if (hand[j].getFaceVal() > hand[maxtomin.peek()].getFaceVal()){
            maxtomin.pop();
            maxtomin.push(j);
          }
        }
        int swapindex = maxtomin.pop();
        PlayingCard maxcard = hand[swapindex];
        hand[swapindex] = hand[i];
        hand[i] = maxcard;
      }
      return hand;
    }
    
    /**
     * Minimum of two integer distances
     * @param distance1
     * @param distance2
     * @return min
     */
    static int min(int distance1, int distance2){
      return distance1 < distance2 ? distance1 : distance2;
    }

    /**
     * Minimum of two double precision distances
     * @param distance1
     * @param distance2
     * @return min
     */
    static double min(double distance1, double distance2){
      return distance1 < distance2 ? distance1 : distance2;
    }

    /**
     * Counts the multiplicity of a facevalue
     * @param cardposition
     * @return
     */
    static int multiplicityCount(int cardposition, PlayingCard[] hand){
      int count = 0;
      for (Entry<Integer, Integer> entry : faceCount(hand).entrySet()){
        if (entry.getKey() == hand[cardposition].getFaceVal()){
          count = entry.getValue();
        }
      }
      return count;
    }

    /**
     * Distance of specific Card from the targeted multiplicity
     * Eg. How many cards away from a Three of a Kind is a certain face type
     * @param multiplicitytarget
     * @param cardposition
     * @return
     */
    static int distanceOfCardFrom(int multiplicitytarget, int cardposition, PlayingCard[] hand){
      int distance = multiplicitytarget - multiplicityCount(cardposition, hand);
      return distance < 0 ? 0 : distance;
    }

    /**
     * Distance of other cards from the targeted multiplicity
     * @param multiplicitytarget
     * @param cardposition
     * @return
     */
    static int distanceOfRestFrom(int multiplicitytarget, int cardposition, PlayingCard[] hand){
      int mindistance = Integer.MAX_VALUE;
      for (int i = 0; i < hand.length; i++){
        if (hand[i].getFaceVal() != hand[cardposition].getFaceVal()){
          mindistance = min(mindistance, distanceOfCardFrom(multiplicitytarget, i, hand));
        }
      }
      return mindistance < 0 ? 0 : mindistance;
    }
    
    /**
     * Return Discard Probability
     * Discard Probability based on completing any hand type
     * Any hand type being completed is calculated as the sum of probabilities of ...
     * ...completing any hand type alone.
     * Each probability is weighted by significance of the hand type completion
     * Discard Probability is 1 minus the probability of completing any hand
     * Discard Probability is then expressed as a percentage
     * @param hand
     * @param index
     * @return
     */
    static int getDiscardProbability(PlayingCard[] hand, int index){
        double onePairCompletionProb = getOnePairCompletionProbability(hand, index) / 5.0;
        double twoPairCompletionProb = getTwoPairCompletionProbability(hand, index) / 4.0;
        double threeOfAKindCompletionProb = getThreeOfAKindCompletionProbability(hand, index) / 3.0;
        double flushCompletionProb = getFlushCompletionProbability(hand, index) / 2.0;
        double straightCompletionProb = getStraightCompletionProbability(hand, index);
        double fullHouseCompletionProb = getFullHouseCompletionProbability(hand, index) * 2.0;
        double fourOfAKindCompletionProb = getFourOfAKindCompletionProbability(hand, index) * 3.0;
        double straightFlushCompletionProb = getStraightFlushCompletionProbability(hand, index) * 4.0;
        double royalFlushCompletionProb = getRoyalFlushCompletionProbability(hand, index) * 5.0;
        
        double probabilityCompletingHand = 
                onePairCompletionProb + twoPairCompletionProb + threeOfAKindCompletionProb 
                + flushCompletionProb + straightCompletionProb + fullHouseCompletionProb 
                + fourOfAKindCompletionProb + straightFlushCompletionProb + royalFlushCompletionProb;
        
        double discardProbability = 1.0 - probabilityCompletingHand;
        return (int)(discardProbability * 100);
    }
    
    /**
     * One Pair Completion Probability
     * @param hand
     * @param index
     * @return onePairCompletionProb
     */
    static double getOnePairCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double onePairDistance = OnePair.distanceToOnePair(index, hand);
        double onePairProbability = Math.pow(COMPLETION_PROBABILITY, onePairDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += OnePair.distanceToOnePair(i, hand);
        }
        weight = (onePairDistance / totalDistances) * hand.length;
        
        double onePairCompletionProb = onePairProbability * weight;
        return onePairCompletionProb;
    }
    
    /**
     * Two Pair Completion Probability
     * @param hand
     * @param index
     * @return twoPairCompletionProb
     */
    static double getTwoPairCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double twoPairDistance = TwoPair.distanceToTwoPair(index, hand);
        double twoPairProbability = Math.pow(COMPLETION_PROBABILITY, twoPairDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += TwoPair.distanceToTwoPair(i, hand);
        }
        weight = (twoPairDistance / totalDistances) * hand.length;
        
        double twoPairCompletionProb = twoPairProbability * weight;
        return twoPairCompletionProb;
    }
    
    /**
     * Three of A Kind Completion Probability
     * @param hand
     * @param index
     * @return threeOfAKindCompletionProb
     */
    static double getThreeOfAKindCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double threeOfAKindDistance = ThreeOfAKind.distanceToThreeOfAKind(index, hand);
        double threeOfAKindProbability = Math.pow(COMPLETION_PROBABILITY, threeOfAKindDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += ThreeOfAKind.distanceToThreeOfAKind(i, hand);
        }
        weight = (threeOfAKindDistance / totalDistances) * hand.length;
        
        double threeOfAKindCompletionProb = threeOfAKindProbability * weight;
        return threeOfAKindCompletionProb;
    }
    
    /**
     * Flush Completion Probability
     * @param hand
     * @param index
     * @return flushCompletionProb
     */
    static double getFlushCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 1.0 / 4.0;
        double flushDistance = Flush.distanceToFlush(index, hand);
        double flushProbability = Math.pow(COMPLETION_PROBABILITY, flushDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += Flush.distanceToFlush(i, hand);
        }
        weight = (flushDistance / totalDistances) * hand.length;
        
        double flushCompletionProb = flushProbability * weight;
        return flushCompletionProb;
    }
    
    /**
     * Straight Completion Probability
     * @param hand
     * @param index
     * @return straightCompletionProb
     */
    static double getStraightCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double straightDistance = Straight.distanceToStraight(index, hand);
        double straightProbability = Math.pow(COMPLETION_PROBABILITY, straightDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += Straight.distanceToStraight(i, hand);
        }
        weight = (straightDistance / totalDistances) * hand.length;
        
        double straightCompletionProb = straightProbability * weight;
        return straightCompletionProb;
    }
    
    /**
     * Full House Completion Probability
     * @param hand
     * @param index
     * @return fullHouseCompletionProb
     */
    static double getFullHouseCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double fullHouseDistance = FullHouse.distanceToFullHouse(index, hand);
        double fullHouseProbability = Math.pow(COMPLETION_PROBABILITY, fullHouseDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += FullHouse.distanceToFullHouse(i, hand);
        }
        weight = (fullHouseDistance / totalDistances) * hand.length;
        
        double fullHouseCompletionProb = fullHouseProbability * weight;
        return fullHouseCompletionProb;
    }
    
    /**
     * Four of a Kind Completion Probability
     * @param hand
     * @param index
     * @return fourOfAKindCompletionProb
     */
    static double getFourOfAKindCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double fourOfAKindDistance = FourOfAKind.distanceToFourOfAKind(index, hand);
        double fourOfAKindProbability = Math.pow(COMPLETION_PROBABILITY, fourOfAKindDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += FourOfAKind.distanceToFourOfAKind(i, hand);
        }
        weight = (fourOfAKindDistance / totalDistances) * hand.length;
        
        double fourOfAKindCompletionProb = fourOfAKindProbability * weight;
        return fourOfAKindCompletionProb;
    }
    
    /**
     * Straight Flush Completion Probability
     * @param hand
     * @param index
     * @return straightFlushCompletionProb
     */
    static double getStraightFlushCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double straightFlushDistance = StraightFlush.distanceToStraightFlush(index, hand);
        double straightFlushProbability = Math.pow(COMPLETION_PROBABILITY, straightFlushDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += StraightFlush.distanceToStraightFlush(i, hand);
        }
        weight = (straightFlushDistance / totalDistances) * hand.length;
        
        double straightFlushCompletionProb = straightFlushProbability * weight;
        return straightFlushCompletionProb;
    }
    
    /**
     * Royal Flush Completion Probability
     * @param hand
     * @param index
     * @return royalFlushCompletionProb
     */
    static double getRoyalFlushCompletionProbability(PlayingCard[] hand, int index){
        double COMPLETION_PROBABILITY = 4.0 / 52.0;
        double royalFlushDistance = RoyalFlush.distanceToRoyalFlush(index, hand);
        double royalFlushProbability = Math.pow(COMPLETION_PROBABILITY, royalFlushDistance);
        
        double weight = 0;
        double totalDistances = 0;
        for (int i = 0; i < hand.length; i++){
            totalDistances += RoyalFlush.distanceToRoyalFlush(i, hand);
        }
        weight = (royalFlushDistance / totalDistances) * hand.length;
        
        double royalFlushCompletionProb = royalFlushProbability * weight;
        return royalFlushCompletionProb;
    }
}
