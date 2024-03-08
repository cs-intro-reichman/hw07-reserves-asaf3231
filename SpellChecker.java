
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tailword = str.substring(1) ; 
		return tailword ; 
	}

	public static int levenshtein(String word1, String word2) {
		if(word2.length() == 0){
			return word1.length() ; 
		}
		 if(word1.length() == 0 ){
			return word2.length() ; 
		}
		word1.toLowerCase() ; 
		word2.toLowerCase() ; 

		 if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(word1.substring(1), word2.substring(1)); 
		}
		else{
			int lev1 = levenshtein(word1.substring(1), word2) ;
			int lev2 = levenshtein(word1, word2.substring(1)) ; 
			int lev3 = levenshtein(word1.substring(1),word2.substring(1));

			int minimum = 1 + Math.min(Math.min(lev2, lev3), lev1) ;
			return minimum ; 
		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for( int i = 0 ; i < dictionary.length ; i ++){
			dictionary[i] = in.readLine() ; 
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int temp ; 
		String finalword = word ;

		for ( int i = 0 ; i < dictionary.length ; i ++ ){

		temp = levenshtein(word, dictionary[i]) ; 
		if(threshold >= temp ){
			threshold = temp ; 
			finalword = dictionary[i] ; 
			return finalword ;

		}
		}
		return finalword ;
	}

}
