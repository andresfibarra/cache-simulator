import java.util.ArrayList;

/**
 * CacheSet class to represent each cacheSet when simulating a cache
 * Information on the number of lines in the cache to be provided 
 * in instantiation
 * 
 * @author andresfibarra, Spring 2022, COSC51 Homework 7
 */
public class CacheSet {
	private int numLines; 
	private ArrayList<CacheLine> cacheLines; 
	private boolean verbose; 
	private static int touchCounter = 1; 

	public CacheSet(int numLines, boolean verbose) {
		this.verbose = verbose; 
		this.numLines = numLines; 
		cacheLines = new ArrayList<>(); 

		//fill in cacheSet with empty cacheLines
		for (int i = 0; i < numLines; i++) {
			cacheLines.add(new CacheLine()); 
		}
	}

	/**
	 * For use during when the simulator is set to "verbose" - prints out 
	 * 	the state of each line
	 */
	public void printSetState() {
		for (int i = 0; i < numLines; i++) {
			System.out.println("line 0x" + Integer.toHexString(i) + " V = " + cacheLines.get(i).getValid() + " tag 0x" 
					+ Integer.toHexString(cacheLines.get(i).getTag()) + " last touch = " + cacheLines.get(i).getLastTouch());
		}
		System.out.print("\n"); 
	}

	/**
	 * Searches the cacheSet for the given tag and updates the line accordingly
	 * If the tag is not found, the least recently touched line is evicted
	 * @return true if hit, false if miss
	 */
	public boolean updateSet(int tag) {
		int idx = 0; 	//index of the line least recently touched if !hit; else, = idx of line where found
		boolean hit = false; 

		for (int i = 0; i < numLines; i++) {
			if (cacheLines.get(i).getLastTouch() < cacheLines.get(idx).getLastTouch()) {
				idx = i; 																	//update least recently touched
			}
			if (cacheLines.get(i).getValid()){
				if (cacheLines.get(i).getTag() == tag) {
					idx = i; 
					hit = true; 
					break;
				}
			}
		}

		if (verbose) {
			if (hit) { //if hit
				System.out.println("Hit! Found in line " + idx + "! Update last touch to " + touchCounter); 
			} else if (!hit) { //if miss
				System.out.println("Miss! Evicting line 0x" + Integer.toHexString(idx) 
							+ "; Adding block there; Setting last touch to " + touchCounter); 
			}
			System.out.println("-----------------------");
		}

		cacheLines.get(idx).updateLine(tag, touchCounter, verbose); 
		touchCounter++; 
		return hit; 
	}
}
