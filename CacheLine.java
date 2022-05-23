/**
 * CacheLine class to simulate an individual cache line. Used to keep track
 * 	of the necessary metadata
 * For use to populate the list stored in a CacheSet object
 *
 * @author andresfibarra, Spring 2022, COSC51 Homework 7
 */
public class CacheLine {
	private boolean valid; 			//valid bit
	private int tag; 						//tag bits
	private int lastTouch;

	public CacheLine() {
		tag = 0; 
		lastTouch = 0; 
		valid = false; 
	}

	/**
	 * Getter for the valid bit
	 * @return - the truthiness of the valid bit
	 */
	public boolean getValid() {
		return valid; 
	}

	/**
	 * Setter for the valid bit to set it to false
	 */
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

	/**
	 * Getter for the line's tag being stored
	 * @return - the line's tag
	 */
	public int getTag() {
		return tag; 
	}

	/**
	 * Getter for the lastTouch field in the CacheLine
	 * @return - integer representing last touch
	 */
	public int getLastTouch() {
		return lastTouch; 
	}
}