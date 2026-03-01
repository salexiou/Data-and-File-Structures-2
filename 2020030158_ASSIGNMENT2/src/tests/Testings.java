package tests;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import counters.DepthCounter;
import counters.TimeCounter;
import kd_tree.KdTree;
import quad_tree.QuadTree;

public class Testings {

	public static final int N = (int) Math.pow((int) 2, (int) 16);
	public static final int[] M = { 200, 500, 1000, 10000, 30000, 50000, 70000, 100000 };
	public static final int numOfSearches = 100;

	public static void main(String[] args) {

		System.out.println("====================================================================================================");
		System.out.println("====================================================================================================");
		System.out.println("\t\t\t\t ALEXIOU STAMATIOS 2020030158");
		
		
		for (int i = 0; i < M.length; i++) {
			
			KdTree kd = new KdTree();

			QuadTree qt = new QuadTree();
			
			/* Create the points that will be inserted into the Trees */

			int[] x = new int[M[i]] ;		
			int[] y = new int[M[i]];
			
			if(M[i] < N) {
				 x = createRandomUniqueInts(N, M[i]);
				 y = createRandomUniqueInts(N, M[i]);
			}	
			else {
			    Set<Integer> set = new HashSet<Integer>();
			    for (int j = 0; j < M[i]; j++) {
			        int randX, randY;
			        do {
			            randX = (int) (Math.random() * N);
			            randY = (int) (Math.random() * N);
			        } while (set.contains(randX * M[i] + randY)); 
			        set.add(randX * M[i] + randY); // storing a random unique integer into the set!
			        x[j] = randX;
			        y[j] = randY;
			    }
			}

						/* Insert keys into Trees */
			
			for (int j = 0; j < M[i]; j++) {				
								
				kd.setRoot(kd.insert(kd.getRoot(), x[j], y[j]));
				qt.setRoot(qt.insert(qt.getRoot(), x[j], y[j]));

			}

						/* Search keys in Kd_Tree */

			/* Keys exist in Tree */
			
			int[] searchPointX = new int[numOfSearches];
			int[] searchPointY = new int[numOfSearches];
			
			for (int k = 0 ; k < numOfSearches;k++) {
				int z = (int) (Math.random() * M[i]);
				searchPointX[k] = x[z];
				searchPointY[k] = y[z];
			}
			
			for (int k = 0; k < numOfSearches; k++) {
				long time_start = TimeCounter.startOrEndTimeCounter();
				kd.search(kd.getRoot(), searchPointX[k], searchPointY[k], k);
				long time_end = TimeCounter.startOrEndTimeCounter();
				TimeCounter.calculateTime(time_start, time_end, k);
			}
			
			printStats(M[i], "Found Keys in Kd Tree");
			
			/* Keys do not exist in Tree */
			
			int[] newX = new int[numOfSearches];
			int[] newY = new int[numOfSearches];
			int count = 0;
			
			while (count < numOfSearches) {
			    int searchX = (int) (Math.random() * N);
			    int searchY = (int) (Math.random() * N);
			    boolean exists = false;
			    
			    for (int j = 0; j < M[i]; j++) {
			        if (x[j] == searchX && y[j] == searchY) {
			            exists = true;                          // make sure that the point is not in the tree
			            break;
			        }
			    }
			    for (int j = 0; j < count; j++) {
			        if (newX[j] == searchX && newY[j] == searchY) {
			            exists = true;						   // make sure that the point is not already created
			            break;
			        }
			    }
			    if (!exists) {
			        newX[count] = searchX;
			        newY[count] = searchY;
			        count++;
			    }
			}
			
			for(int j = 0 ; j < numOfSearches; j ++) {
				long time_start = TimeCounter.startOrEndTimeCounter();
				kd.search(kd.getRoot(), newX[j], newY[j], j);
				long time_end = TimeCounter.startOrEndTimeCounter();
				TimeCounter.calculateTime(time_start, time_end, j);
			}

			printStats(M[i], "Not Found Keys in Kd Tree");
			
			/** 				Search keys in QuadTree 
			 * We insert and search the same keys as we did in the Kd Tree
			 * 		  so that we can compare the two Trees better  
			 */

			/* Keys exist in Tree */
			for (int k = 0; k < numOfSearches; k++) {;
				long time_start = TimeCounter.startOrEndTimeCounter();
				qt.search(qt.getRoot(), searchPointX[k], searchPointY[k], k);
				long time_end = TimeCounter.startOrEndTimeCounter();
				TimeCounter.calculateTime(time_start, time_end, k);
			}

			printStats(M[i], "Found Keys in QuadTree");
			
			/* Keys do not exist in Tree */
			for (int j = 0; j < numOfSearches; j++) {
				
				long time_start = TimeCounter.startOrEndTimeCounter();
				qt.search(qt.getRoot(), newX[j], newY[j], j);
				long time_end = TimeCounter.startOrEndTimeCounter();
				TimeCounter.calculateTime(time_start, time_end, j);
			}
			
			printStats(M[i], "Not Found Keys in QuadTree");

		}
	}

	public static void printStats(int M, String opt) {
		System.out.println(
				"====================================================================================================");

		System.out.println(
				"====================================================================================================\n\n\n");
		System.out.println("===================================================");
		System.out.println(" (" + opt + ")  For " + M + " Points: 	  	 ");
		System.out.println("===================================================");
		System.out.println("\nAverage depth:  " + DepthCounter.averageDepthOnSearching() + "\t\tTime: "
				+ TimeCounter.averageTimeOnSearching() + " ns");
		System.out.println("\n===================================================");
		System.out.println("\n");

		/** Reset depth and time counters for the next measurements (they both have the
		 * 	same amount of counters) so DepthCounter.getCounters = TimeCounter.getCounters
		 */
		for (int i = 0; i < DepthCounter.getCounters().length; i++) {
			DepthCounter.resetCounter(i);
			TimeCounter.resetCounter(i);
		}
	}

	public static int[] createRandomUniqueInts(int N, int M) {

		Random randomGenerator = new Random();
		return randomGenerator.ints(0, N - 1).distinct().limit(M).toArray();
	}
	
}
