package quiz.cosmos.service;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 
 * all the methods all self- explanatory, no read-me doc is placed.
 * 
 * @author shiy
 *
 * 
 * 
 */
public class MyTrains {
	static Map<String, Integer> edges = new HashMap<String, Integer>();
	private Set<String> routes = edges.keySet();
	public static void main(String args[]) {
		edges.put("AB", 5);
		edges.put("BC", 4);
		edges.put("CD", 8);
		edges.put("DC", 8);
		edges.put("DE", 6);
		edges.put("AD", 5);
		edges.put("CE", 2);
		edges.put("EB", 3);
		edges.put("AE", 7);
		// number of paths from one stop to another
		MyTrains tr = new MyTrains();
		System.out.println("Output #1: " + tr.getDistOfGivenPath("A-B-C"));
		System.out.println("Output #2: " + tr.getDistOfGivenPath("A-D"));
		System.out.println("Output #3: " + tr.getDistOfGivenPath("A-D-C"));
		System.out.println("Output #4: " + tr.getDistOfGivenPath("A-E-B-C-D"));
		System.out.println("Output #5: " + tr.getDistOfGivenPath("A-E-D"));
		System.out.println("Output #6: " + tr.findMaxNumOfPaths('C', 'C', "C", 0));
		System.out.println("Output #7: " + tr.findExactNumOfPaths('A', 'C', "A", 0));
		System.out.println("Output #8: " + tr.findMinPath('A', 'C', 0, Integer.MAX_VALUE));
		System.out.println("Output #9: " + tr.findMinPath('B', 'B', 0, Integer.MAX_VALUE));
		System.out.println("Output #10: " + tr.findPaths('C', 'C', 0, 0, "C"));
	}
	
	public String getDistOfGivenPath(String route) {
		String[] destStops = route.split("-");
		int len = 0;
		for (int i = 0; i < destStops.length - 1; i++) {
			if (edges.containsKey(destStops[i] + destStops[i + 1]))
				len += edges.get(destStops[i] + destStops[i + 1]);
			else {
				return "NO SUCH ROUTE";
			}
		}
		return String.valueOf(len);
	}
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @param pathStr
	 * @param i
	 * @return
	 */
	public int findMaxNumOfPaths(char src, char dest, String pathStr, int i) {
		for (String route : routes) {
			if (route.charAt(0) == src) {
				// System.out.println("debug " + pathStr);
				if (route.charAt(1) == dest) {
					// System.out.println(pathStr + pair.charAt(1));
					i++;
				}
				if (pathStr.length() >2) 
					break;
				i = findMaxNumOfPaths(route.charAt(1), dest, pathStr + route.charAt(1), i);
			}
		}
		return i;
	}
	
	// ABCDC
	public int findExactNumOfPaths(char src, char dest, String pathStr, int i) {
		for (String route : routes) {
			if (route.charAt(0) == src) {
				// System.out.println("debug " + pathStr);
				if (pathStr.length() == 4 && route.charAt(1) == dest) {
					//System.out.println(pathStr + route.charAt(1));
					i++;
					// no duplicate target town will appear
					break;
				}
				if (pathStr.length() >= 5)
					break;
				i = findExactNumOfPaths(route.charAt(1), dest, pathStr + route.charAt(1), i);
			}
		}
		return i;
	}

	/**
	 * 
	 * @param src
	 * @param dest
	 * @param distance
	 * @param min
	 * @return
	 */
	public int findMinPath(char src, char dest, int distance, int min) {
		for (String route : routes) {
			if (route.charAt(0) == src) {
				int curDist = distance + edges.get(route);
				if(curDist < min){
					if (route.charAt(1) == dest){
						min = curDist;
					}
					min = findMinPath(route.charAt(1), dest, curDist, min);
				}
			}
		}
		return min;
	}


	/**
	 * 
	 * @param src
	 * @param dest
	 * @param distance
	 * @param numPaths
	 * @param pathStr
	 * @return
	 */
	public int findPaths(char src, char dest, int distance, int numPaths, String pathStr) {
		for (String route : routes) {
			if (route.charAt(0) == src) {
				int fullLen = distance + edges.get(route);;
				// System.out.println("debug " + pair);
				 if(fullLen < 30) {
					 if (route.charAt(1) == dest){
						 numPaths++;
						 //System.out.println(pathStr + route.charAt(1) + " full length: " + fullLen);
					 }
					 numPaths = findPaths(route.charAt(1), dest, fullLen, numPaths, pathStr + route.charAt(1));
				}
			}
		}
		return numPaths;
	}
}