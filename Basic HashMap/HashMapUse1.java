import java.util.HashMap;
import java.util.Map;

/**
 * Final Exam starter code.  Complete the code below as indicated
 * in final exam question.
 * @author john1819
 *
 */
public class HashMapUse1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// do not change this code
		String[] s1 = {"a", "bb", "a", "bb"};
		String[] s2 = {"this", "and", "that", "and"};
		String [] s3 = {"code", "code", "code", "bug"};
		
		Map<String, Integer> aMap = wordLen(s1);
		
		System.out.println("First map = " + aMap);
		
		aMap = wordLen(s2);
		System.out.println("Second map = " + aMap );
		
		aMap = wordLen(s3);
		System.out.println("Third map = " + aMap);
		

	}
	
	/**
	 * Write this method such that 
	 * Given an array of strings, return a Map<String, Integer>
	 * containing a key for every different string in the array,
	 *  and the value is that string's length.
	 */
	public static Map<String, Integer> wordLen(String[] strings) {
	    Map<String, Integer> words = new HashMap<String, Integer>();
	    for (int i = 0; i < strings.length; i ++) {
	        words.put(strings[i], strings[i].length());
	    }
		return words;
	}

}
