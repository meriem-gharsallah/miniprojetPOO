package miniprojetPOOpackage;
/**
 * A strategy that uses the Levenshtein's Distance to calculate the <i>edit distance</i> of two strings.
 * Then it converts this to a "score" to fit the framework.
 *
 * @see <a href="http://en.wikipedia.org/wiki/Levenshtein_distance">About Levenshtein Distance</a>
*/

public class ComparateurLevenshtein implements ComparateurApproximatifdeChaine {
	/**
     * Calculates the similarity score of objects, where 0.0 implies absolutely no similarity
     * and 1.0 implies absolute similarity.
     *
     * @param first The first string to compare.
     * @param second The second string to compare.
     * @return A number between 0.0 and 1.0.
     * @throws NullPointerException if one or both of the strings are null
     */

   public double comparer(String s1, String s2) {
	   int maxLength = Math.max(s1.length(), s2.length());
       //Can't divide by 0
       if (maxLength == 0) return 1.0d;
       return ((double) (maxLength - computeEditDistance(s1, s2))) / (double) maxLength;
  	}
   protected int computeEditDistance(String s1, String s2) {
       s1 = s1.toLowerCase();
       s2 = s2.toLowerCase();
       int[] costs = new int[s2.length() + 1];
       for (int i = 0; i <= s1.length(); i++) {
           int previousValue = i;
           for (int j = 0; j <= s2.length(); j++) {
               if (i == 0) {
                   costs[j] = j;
               }
               else if (j > 0) {
                   int useValue = costs[j - 1];
                   if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                       useValue = Math.min(Math.min(useValue, previousValue), costs[j]) + 1;
                   }
                   costs[j - 1] = previousValue;
                   previousValue = useValue;

               }
           }
           if (i > 0) {
               costs[s2.length()] = previousValue;
           }
       }
       return costs[s2.length()];
   }
   public boolean estDistance() {

		return false;
	}

}

//source:https://github.com/rrice/java-string-similarity/blob/master/src/main/java/net/ricecode/similarity/LevenshteinDistanceStrategy.java