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
}
