

import java.util.*;


public class StringSequence {

   public static void main(String args[]) {
      String s = "AABCDDDEEEFFGGGGAAEZZZZZZPPPPPPPPPPP";
      System.out.println("Call to getLongestChar(String str) returned: " + getLongestSubseqChar(s));
   }

   public static String getLongestSubseqChar(String str) {
      HashMap<Character, Integer> charMap = new HashMap<>();
      char[] chars = str.toCharArray();
      int startSeq, countSeq = 0;
   
      for (int i = 0; i < chars.length - 1; i++) {
         startSeq = i; // start sequence index
         countSeq = 1;
         // Map has the key
         if (startSeq != chars.length - 1 && charMap.containsKey(chars[startSeq])) {
            while (chars[startSeq] == chars[startSeq + 1]) {
               ++countSeq;
               startSeq++;
            }
            // Get the count value of the specified key
            Integer mapVal = charMap.get(chars[i]);
            // If the value in the map is less than our new count, replace
            if (mapVal < countSeq) {
               charMap.put(chars[i], countSeq);
            }
         }
         // Map does not already have the key
         else {
            while (startSeq != chars.length - 1 && chars[startSeq] == chars[startSeq + 1]) {
               ++countSeq;
               startSeq++;
            }
            charMap.put(chars[i], countSeq);
         }
         i = startSeq;
      }
   
      // NOTE: we can have multiple chars with the same sequence length
      //ArrayList<Pair<Character, Integer>> pairList = new ArrayList<>();
   
      //Integer lastInt = 0, tempInt = 0;
      //for (Map.Entry<Character, Integer> entry: charMap.entrySet()) {
      //    Character tempChar = entry.getKey();
      //    tempInt = entry.getValue();
      //    if (tempInt >= lastInt)
      //}
   
      Character greatestChar = Collections.max(charMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
      Integer greatestCharCount = charMap.get(greatestChar);
      //return new Map.Entry<Character, Integer>(greatestChar, greatestCharCount);
      String ret = "[ " + greatestChar + ", " + greatestCharCount + " ]";
      return ret;
   
   }
}
