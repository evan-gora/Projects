
/**
 * Tester Method for final exam
 * 
 * @author john1819
 *
 */
public class TreeTester2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create a binary tree
		SimpleIntegerBST2 theTree = new SimpleIntegerBST2();

		// populate the tree
		int[] starterTree = { 161, 388, 453, 296, 158, 328, 
				179, 248, 391, 411, 110, 98, 97, 218, 441, 312, 192, 408, 275, 12, 19, 432, 190, 457, 172,
				27, 457, 249, 75, 468, 35, 446, 294, 117, 275, 214, 323, 43, 435, 427, 154, 350, 81, 233, 307, 251, 282, 
				261, 388, 402, 111, 138, 8, 467, 175, 230, 22, 250, 39, 350, 37, 160, 425, 179, 1, 309, 298, 170, 428, 112,
				98, 215, 377, 214, 290, 135, 33, 183, 110, 199, 496, 234, 135, 45, 405, 136, 73, 374, 342, 68, 157, 181, 
				292, 422, 180, 116, 323, 70, 131, 245};
		
		for (int i =0; i<starterTree.length; i++)
		{
			theTree.insert(starterTree[i]);
		}

		// run the problems

		System.out.println("Counting Odds- expected value =  45; actual value = " + theTree.findNumOdds() );
		
		// test second tree
		int[] tree2 =  { 134, 361, 309, 260, 83, 436, 457, 306, 309, 95, 246, 34, 373, 446, 206, 272, 440,
				189, 476, 400, 476, 192, 317, 109, 30, 496, 335, 85, 301, 274, 25, 79, 39, 231, 496, 228, 69, 455, 75,
				148, 182, 311, 189, 141, 268, 127, 393, 118, 39, 12, 212, 193, 167, 308, 111, 293, 325, 208, 20, 227, 11,
				207, 322, 257, 293, 213, 178, 280, 471, 81, 329, 299, 8, 185, 32, 384, 61, 369, 87, 9, 0, 183, 469, 79, 
				129, 238, 254, 97, 146, 304, 35, 388, 212, 130, 11, 20, 153, 344, 450, 6};
		theTree = new SimpleIntegerBST2();
		
		for (int i =0; i<tree2.length; i++)
		{
			theTree.insert(tree2[i]);
		}

		// run the problems

		System.out.println("Counting Odds- expected value =  48; actual value = " + theTree.findNumOdds() );
	
	}

}
