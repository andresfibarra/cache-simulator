import java.util.ArrayList;

/**
 * Cache class for program simulating the cache. 
 * Each Cache object has a list of cacheSet objects
 * 
 * @author andresfibarra, Spring 2022, COSC51 Homework 7
 */
public class Cache {
	int mode; 													//1 = direct, 2 = 2 way, 
																			//3 = 4 way, 4 = fully associative
	private ArrayList<CacheSet> cacheSets; 
	private int tagMaskInt; 
	private int setMaskInt; 
	boolean verbose; 

	private int hits; 									//counter for total hits and misses
	private int misses; 

	public Cache(int numSets, int numLines, boolean verbose, int mode) {
		hits = 0; 
		misses = 0; 

		//set up tag and set mask according to which cache is being simulated
		if (mode == 1) {							//if direct
			tagMaskInt = -1024; 				//0xfffffc00 in decimal
			setMaskInt = 1008; 					//0x000003f0 in decimal		
		} else if (mode == 2) {				//if two way
			tagMaskInt = -512;					//0xfffffe00 in decimal
			setMaskInt = 496;						//0x000001f0 
		} else if (mode == 3) {				//if four way
			tagMaskInt = -256;					//0xffffff00
			setMaskInt = 240;						//0x000000f0
		} else if (mode == 4) {				//if fully, or if any incorrect number is encountered
			tagMaskInt = -16; 					//0xfffffff0
			setMaskInt = 0; 						//0x00000000
		}

		System.out.println("tagMask: 0x" + Integer.toHexString(tagMaskInt)); 
		System.out.println("setMask: 0x" + Integer.toHexString(setMaskInt)); 

		this.verbose = verbose; 

		cacheSets = new ArrayList<>(); 

		//fill in cacheSet with empty cacheLines
		for (int i = 0; i < numSets; i++) {
			cacheSets.add(new CacheSet(numLines, verbose)); 
		}
	}

	/**
	 * Checks cache for the provided address
	 * 
	 * @param - Address in hexadecimal, as a string
	 * @param - the type of cache to be simulated. View cacheSimulator simulate()
	 * 	documentation to see what each mode means
	 */
	public void checkCache(String address, int mode) {
		//extract set bits, tag bits
		int addressInt = Integer.parseUnsignedInt(address, 16); 
		int setMasked = addressInt & setMaskInt; 
		int tagMasked = addressInt & tagMaskInt; 
		int setShift = 4; 
		int tagShift; 

		//set up tagShift
		if (mode == 1) {	//direct
			tagShift = 10; 
		} else if (mode == 2) {
			tagShift = 9; 
		} else if (mode == 3) {
			tagShift = 8; 
		} else {
			tagShift = 4; 
		}

		//obtain integer versions of setId and tag by bit shifting
		int setId = setMasked >> setShift; 
		int tag = tagMasked >> tagShift; 

		//print if necessary
		if (verbose) {
			System.out.print("address: 0x" + address); 
			System.out.println(" ; looking for tag 0x" + Integer.toHexString(tag) + " in set 0x" + Integer.toHexString(setId)); 
			System.out.println("\nState of set 0x" + Integer.toHexString(setId));
			cacheSets.get(setId).printSetState(); 
		}

		//Update the set and increment either hits or misses
		boolean isHit = cacheSets.get(setId).updateSet(tag); 
		if (isHit) {
			hits++; 
		} else {
			misses++; 
		}
	}

	/**
	 * toString method for the Cache class, printing out the total number of 
	 * 	hits and misses
	 */
	@Override
	public String toString() {
		String s = "Hits: " + hits + "; misses: " + misses + "\n"; 
		return s; 
	} 
}
