import java.util.*;

/**
   This class demonstrates interesting challenges with Strings in Java.
   Each function in this class represents a different challenge.
*/

public class StringSequence {

   public static void main(String args[]) {
      String s1 = "AABCDDDEEEFFGGGGAAEZZZZZZPPPPPPPPPPPOOOOUUUUUUUUUUUUUUUUUIIIII"; // Test getLongestSubseqChar(String str)
      String s2 = "ABCD"; // Test permutation(String prefix, String str)
      String s3 = "ABCDEFG"; // Test #1 of isStringUnique(String s)
      String s4 = "ABCDEFGA"; // Test #2 of isStringUnique(String s)
      String s5 = "abcd", s6 = "acbd"; // Test isPermutation() and addPermuteArrList() called in isPermutation
      String s7 = "apple", s8 = "pleap", s9 = "leapl";

      // Call to function getLongestSubseqChar(String str)
      System.out.println("\nCall to getLongestChar(String str) returned: " + getLongestSubseqChar(s1) + "\n");

      // Call to permutation(String prefix, String strToPermute)
      System.out.println("\nCall to permutation(String prefix, String str) returned: ");
      permutation("--> ", s2);

      System.out.println("\nCall to isStringUnique(String s) on String s3 returned (boolean): " + isStringUnique(s3));
      System.out.println("\nCall to isStringUnique(String s) on String s4 returned (boolean): " + isStringUnique(s4));

      System.out.println("\nCall to isPermutation() on Strings s5 & s6 returned (boolean): " + isPermutation(s5, s6, ""));

      System.out.println("\nCall to isRotation() on Strings s7 & s8 returned (boolean): " + isRotation(s7, s8));
      System.out.println("\nCall to isRotation() on Strings s7 & s9 returned (boolean): " + isRotation(s7, s9));


      // INSERT LINES ABOVE
      System.out.println("\n\n******** END PROGRAM ********\n\n");
   }

   /**
      This function was based on a CSDojo challenge
      (https://www.youtube.com/watch?v=qRNB8CV3_LU )
      and shows my attempt at a solution. The goal with this
      function is to return the character with the longest subsequence
      in a given string along with the longest count for that character.

      TIME COMPLEXITY: ____
      SPACE COMPLEXITY: ____
   */
   public static String getLongestSubseqChar(String str) {
      HashMap<Character, Integer> charMap = new HashMap<>();
      char[] chars = str.toCharArray();
      int startSeq, countSeq = 0;

      for (int i = 0; i < chars.length - 1; i++) {
         startSeq = i; // start sequence index
         countSeq = 1;
         // Map has the key
         if (charMap.containsKey(chars[startSeq])) {
            while (startSeq != chars.length - 1 && chars[startSeq] == chars[startSeq + 1]) {
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

   /**
      This function returns every permutation of a given string.

      TIME COMPLEXITY: ____
      SPACE COMPLEXITY: ____

   */
   private static void permutation(String prefix, String str) {
      int n = str.length();
      if (n == 0) {
         System.out.println(prefix);
      }
      else {
         for (int i = 0; i < n; i++) {
            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
         }
      }
   }


   /**
        Implement an algorithm to determine if a string has all unique characters.
        -- What if you cannot use additional data structures?

        TIME COMPLEXITY: ____
        SPACE COMPLEXITY: ____
   */
   public static boolean isStringUnique(String s) {
       HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
       // Could have likely implemented an ArrayList instead of HashMap for this, since we
       // don't really need a Map.Entry<K, V> pair, but this works. Might be considered overkill though
       for (char ch: s.toCharArray()) {
           int count = 1;
           if (!charMap.containsKey(ch)) {
               charMap.put(ch, count);
           }
           else {
               return false;
           }
       }
       return true;
   }

   /**
        Given two strings, write a method to decide if one is a permutation of the other.
        Uses addPermuteArrList()
   */
   public static boolean isPermutation(String s1, String s2, String prefix) {
       ArrayList<String> permuteList = new ArrayList<String>();
       int n = Math.max(s1.length(), s2.length());
       String sTemp;
       if (s1.length() != s2.length()) {
           return false;
       }
       if (s1.length() >= s2.length()) {
           sTemp = s1;
       }
       else {
           sTemp = s2;
       }
       addPermuteArrList(prefix, sTemp, permuteList);
       if (permuteList.contains(s1) && permuteList.contains(s2)) {
           return true;
       }
       return false;
   }

   /** CALLED IN isPermutation() Given two strings, write a method to decide if one is a permutation of the other. */
   public static void addPermuteArrList(String prefix, String suffix, ArrayList<String> arrList) {
       int n = suffix.length();
	   if (n == 0) {
	        arrList.add(prefix);
	   }
       else {
	        for (int i = 0; i < n; i++) {
                addPermuteArrList(prefix + suffix.charAt(i), suffix.substring(0, i) + suffix.substring(i + 1,  n), arrList);
            }
        }
    }

    /**
        Assume you have a method isSubstring which checks if one word is a
        substring of another. Given two strings, s1 and s2, write code to check if s2 is
        a rotation of s1 using only one call to isSubstring (e.g.,"waterbottle"is a rotation
        of "erbottlewat").

        TEST 1: str1 = "apple" , str2 = "pleap"
        TEST 2: str1 = "apple" , str2 = "leape"

        Assumptions/Considerations:
        -- Assume cases are the same (If not, we can just call toUpperCase() or toLowerCase() on both strings)
        -- String 2's length must be exactly equal to string 1's length for the purpose of determining rotation 
    */
    // Is s2 a rotation of s1?
    public static boolean isRotation(String str1, String str2) {
	    if (str1.length() != str2.length()) {
		    return false;
        }
        String s3 = str1 + str1;
	    if (s3.contains(str2)) {
	        return true;
        }
	    return false;
    }



}
