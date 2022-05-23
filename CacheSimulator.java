import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Driver class for the cache simulation
 * Reads from files and acts accordingly
 * 
 * @author andresfibarra, Spring 2022, COSC51 Homework 7
 */
public class CacheSimulator {
	private boolean verbose; 		//when true, print out status of cache

	public CacheSimulator(boolean verbose) {
		this.verbose = verbose; 
	}

	/**
	 * Reads the trace file and simulates the cache using that information
	 * If mode = 1, direct mapped cache is simulated, if mode = 2, two-way 
	 * 	set associative, if mode = 3, 4-way set associative, if mode = 4, 
	 * 	fully associative is simulated
	 * @param fileName - path to trace text file
	 * @param mode - which cache to be simulated
	 */
	public void simulate(String fileName, int mode) {
		BufferedReader input;
		int numLines; 
		int numSets;

		System.out.println("-----------------------");
		System.out.println("-----------------------");

		System.out.println("trace: " + fileName); 

		//adjust parameters to mode
		if (mode == 1) {					//direct
			numLines = 1; 
			numSets = 64; 
			System.out.println("Direct mapped");
		} else if (mode == 2) {		//2 way
			numLines = 2; 
			numSets = 32; 
			System.out.println("2-way set associative");
		} else if (mode == 3) {		//4 way
			numLines = 4; 
			numSets = 16; 
			System.out.println("4-way set associative");
		} else {									//full 
			numLines = 64; 
			numSets = 1; 
			System.out.println("fully associative");
		}

		Cache cache = new Cache(numSets, numLines, verbose, mode);

		System.out.println("-----------------------");
		System.out.println("-----------------------");

		try{
			input = new BufferedReader(new FileReader(fileName));
		}
		catch (FileNotFoundException e) {
				System.err.println("Cannot open file. \n" + e.getMessage());
				return;
		}

		try{
			String line;

			while ((line = input.readLine()) != null) {
				String[] lineArray = line.split(" "); 
				String address = lineArray[1]; 			//get the address in hex 

				cache.checkCache(address, mode); 		//pass off work to the cache object
			}

			System.out.println(cache); 
		}
		catch (IOException e) {
				System.err.println("IO error while reading.\n" + e.getMessage());
		}
		//close files
		finally {
				try {
						input.close();
				}
				catch (IOException e) {
						System.err.println("Cannot close file.\n" + e.getMessage());
				}
		}
	}

	public static void main(String[] args) {
		String fileName = "inputs/long-trace.txt"; 

		CacheSimulator sim = new CacheSimulator(false); 
		sim.simulate(fileName, 1);
		sim.simulate(fileName, 2);
		sim.simulate(fileName, 3);
		sim.simulate(fileName, 4);

	}
}
