/**
 * Public class to simulate an individual cache line. 
 * Included are methods to check values of the cacheLine and to update accordingly
 * We assume:
 * 	That the driver class is providing a public variable touchCounter
 *
 * @author andresfibarra, Spring 2022, COSC51 Homework 7
 */
public class CacheLine {
	private boolean valid; 			//valid bit
	private int tag; 					//tag bits
	private int lastTouch;

	public CacheLine() {
		tag = 0; 
		lastTouch = 0; 
		valid = false; 
	}

	public boolean getValid() {
		return valid; 
	}

	public void setInvalid() {
		valid = false; 
	}

	/**
	 * Update the cache line's metadata
	 * Caller is responsible for: 
	 * 	Incrementing touchCounter after calling this method
	 * 
	 * @param newTag - the new tag the cacheLine will hold
	 * @param touchCounter - the current touch counter
	 */
	public void updateLine(int newTag, int touchCounter, boolean verbose) {
		if (!valid) {
			valid = true; 
		}
		tag = newTag; 
		lastTouch = touchCounter; 
	}

	public int getTag() {
		return tag; 
	}

	public int getLastTouch() {
		return lastTouch; 
	}
}

/*
 * use Integer.parseInt(String s, 16) to get an int with the hex numbers I want
 * use bitwise and & with tag mask and set mask to get it out
 * Organization
 * 	have a cacheline object that holds metadata
 * 	have a cache constructor that takes in a parameter for 
 * direct mapped, 2 way, 4 way, fully associative
 * verbose option (boolean)
 * 
 * to simulate, don't keep track of the value of each byte
 * keep track of the metadata
 * 	valid bit
 * 	tag
 * 	when it was last touched
 * 
 * 32 bit address
 * 16 byte block
 * 64 lines total in the cache
 * 
 * direct associative
 * 	one line per set
 * 	--> 64 sets
 * 	22 tag bits
 * 	6 set bits
 * 	4 block bits
 * 
 * 2 way set-associative
 * 	2 lines per set, 32 sets
 * 	23 tag bits
 * 	5 set bits
 * 	4 block bits
 * 
 * 4 way set-associative
 * 	4 lines per set, 16 sets
 * 	24 tag bits
 * 	4 set bits
 * 	4 block bits
 * 
 * fully associative
 * 	a lot of tag bits, no set bits, some block offset bits --> 1 set, 64 lines
 * 	28 tag bits
 * 	0 set bits
 * 	4 block bits
 */