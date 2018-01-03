import java.util.ArrayList;
import java.util.HashMap;

public class ShortestEncodingClass {

	public static void main(String[] args) {
		String expected3 = "3(abc)";
		String expected2 =  "a9da20d";
		String str3 =      "aaabbbaaabbbaaabbbaaabbb";
		String str2 =     "adddddddddadddddddddddddddddddd";
		String str = "aaabaabaab";
		String expected = "a3(aab)";

		System.err.println(runner(str));
	}

	/**
	 * Runs the methods required to return the shortest encoded version of a String
	 *
	 * @param String str: the String to be encoded
	 * @return String of the shortest encoded version of str
	 */
	public static String runner(String str)
	{
		char[] letters = turnIntoCharArray(str);

		ArrayList<String> patternList = new ArrayList<String>();
		ArrayList<Integer> numOfAppearances = new ArrayList<Integer>();
		getNotationFirstTime(letters, patternList, numOfAppearances);

		ArrayList<String> pList = new ArrayList<String>();
		ArrayList<Integer> numReps = new ArrayList<Integer>();
		HashMap<ArrayList<String>, String> keys = new HashMap<ArrayList<String>,String>();
		ArrayList<String> code = new ArrayList<String>();
		String notation = getNotation(code,patternList,numOfAppearances,2,pList,numReps,keys);
		return notation;
	}

	/**
	 * Runs the methods required to return the shortest encoded version of a String and compares it to an expected value
	 *
	 * @param String str: the String to be encoded
	 * @param String expected: the expected shortest notation of str
	 * @return boolean if the shortest notation is the same as the expected value
	 */
	public static boolean runner(String str, String expected)
	{
		char[] letters = turnIntoCharArray(str);

		ArrayList<String> patternList = new ArrayList<String>();
		ArrayList<Integer> numOfAppearances = new ArrayList<Integer>();
		getNotationFirstTime(letters, patternList, numOfAppearances);

		ArrayList<String> pList = new ArrayList<String>();
		ArrayList<Integer> numReps = new ArrayList<Integer>();
		HashMap<ArrayList<String>, String> keys = new HashMap<ArrayList<String>,String>();
		ArrayList<String> code = new ArrayList<String>();

		String notation = getNotation(code,patternList,numOfAppearances,2,pList,numReps,keys);

		System.err.println(notation);
		System.out.println();
		System.out.println(expected);
		System.out.println();

		if(notation.equals(expected))
			return true;
		else
			return false;
	}


	/**
	 * Searches through char[] letters to find consecutive repetitions of single characters
	 *
	 * @param char[] letters: the character array of the original String
	 * @param ArrayList<String> patternArr: an empty ArrayList to put single letter Patterns into
	 * @param ArrayList<Integer> repArr: an empty ArrayList to put the repetitions of each letter into
	 */
	public static void getNotationFirstTime(char[] letters, ArrayList<String> patternArr, ArrayList<Integer> repArr)
	{
		for(int i = 0; i<letters.length; i++)
		{
			//find the number of times a character is repeated consecutively
			int reps = getReps(letters,i,i);

			//adds the character to patternArr and how many times it repeats to repArr
			patternArr.add(Character.toString(letters[i]));
			repArr.add(reps);

			//if the letter is repeated: increment the index so that it moves onto the next different character (or the end of the array)
			if(reps >1)
				i=i+(1*(reps-1));
		}
	}

	/**
	 * Returns shortest notation of the original string based off of ArrayList<String> from getNotationFirstTime()
	 *
	 * @param ArrayList<String> codes: holds all possible notations (empty on first call but gets filled with recursive calls)
	 * @param ArrayList<String> patternArr: holds some notation of the original string
	 * @param ArrayList<Integer> repArr: holds the number of times each element in patternArr is repeated
	 * @param int pattSize: holds the length of the pattern the program is searching for
	 * @param ArrayList<String> newPatternArr: empty ArrayList that will hold new patterns
	 * @param ArrayList<Integer> newRepArr: empty ArrayList that will hold new repetitions of elements in newPatternArr
	 * @param HashMap<ArrayList<String>, String> keys: keeps record of different patterns so if they are found again, they don't have to be re encoded
	 * @return returns shortest notation of the original string
	 */
	public static String getNotation(ArrayList<String> codes, ArrayList<String> patternArr, ArrayList<Integer> repArr, int pattSize, ArrayList<String> newPatternArr, ArrayList<Integer> newRepArr, HashMap<ArrayList<String>, String> keys)
	{
		System.out.println(patternArr+"\n"+repArr);
		//base case: if the pattern is larger than half of patternArr length (it reaches a pattern size that is no longer possible)
		if(pattSize >patternArr.size()/2) {
			String patternStr = makeKey(patternArr,repArr);
			codes.add(patternStr);

			//index of shortest code
			int minIndex = 0;

			//takes all possible String notations from list codes and finds the shortest one
			for(int i = 0; i<codes.size(); i++)
			{
				if(codes.get(i).length()<codes.get(minIndex).length())
					minIndex = i;
			}
			return codes.get(minIndex);
		}

		/*
		 * creates an array of the values in patternArr but expanded
		 * this helps to check for possible overlaps
		 */
		ArrayList<String> full = makeFullArray(patternArr,repArr);

		//iterates through all the elements in the array to see if there are any patterns of length pattSize
		for(int i = 0; i<patternArr.size(); i++)
		{
			//gets the corresponding starting point from the letter array to the full array of the pattern
			int fullIndexStart = getFullIndexStart(repArr,i);

			//if the pattern length is longer than the space left in the array, then add the part to the array
			if(i+pattSize-1 >patternArr.size()-1)
			{
				newPatternArr.add(patternArr.get(i));
				newRepArr.add(repArr.get(i));
			}

			//if the pattern can be completed, check to see if it is repeated
			else {

				//gets the corresponding ending point from the letter array to the full array of the pattern
				int fullIndexEnd = getFullIndexEnd(repArr,i+pattSize-1);

				//gets the repetitions from the full array
				int reps = getReps(full,fullIndexStart,fullIndexEnd);

				//check if pattern is repeated
				if(reps>1)
				{
					//builds notation for pattern
					ArrayList<String> newLetters = new ArrayList<String>();
					ArrayList<Integer> newReps = new ArrayList<Integer>();

					//String that holds notation
					String patternStr;

					//creates sub arrays of the pattern so that they can be inputed into makeKey()
					for(int j = i; j<i+pattSize; j++)
					{
						newLetters.add(patternArr.get(j));
						newReps.add(repArr.get(j));
					}
					//checks to see if a key is already in the hm
					if(keys.containsKey(newLetters))
						patternStr = keys.get(newLetters);
					//if not, make the key and add it to the hm
					else {
						patternStr = makeKey(newLetters,newReps);
						keys.put(newLetters,patternStr);
					}

					//add notation to newPatternArr and how many times that notation repeats to newRepArr
					newPatternArr.add(patternStr);
					newRepArr.add(reps);

					/*
					 * checks to see if the last element runs over the amount alloted in the pattern
					 *
					 * ex. if pattern is "ab" and string is "abababbb" it would be notated as "3(ab)2b"
					 * b would be the element that ran over the alloted amount in the pattern
					 */

					if(repArr.get(i+pattSize-1) <repArr.get(((i+(pattSize*reps)-1))))
					{
						int newRepsOverboard = repArr.get(((i+(pattSize*reps)-1)))-repArr.get(i+pattSize-1);
						newPatternArr.add(patternArr.get(((i+(pattSize*reps)-1))));
						newRepArr.add(newRepsOverboard);
					}

					//index is incremented so that the index is past the pattern already found (or the end of the array)
					i = i+((reps)*pattSize)-1;

				}

				/*
				 * checks to see if pattern could still exist with a starting point that is not the first when corresponding to the full array
				 *
				 * ex. with "aababab" it would be condensed to "ababab", but the pattern of "ab" would not be recognized because the "a" is repeated twice
				 * so it checks with the full array to see that the pattern does exist by taking off the first "a"
				 * that "a" is then put into the array before the pattern is notated
				 * the resulting notation would be "a3(ab)"
				 */
				else if(isPatternWithDifferentStartingPoint(patternArr, repArr,i,pattSize))
				{
					//finds the difference between the old starting point and the new starting point in the full array
					int difference = getDifference(repArr,i,pattSize);

					//based off of the difference, it finds the new starting point of the pattern
					fullIndexStart = getFullIndexStart(repArr,i)+difference;

					//finds how many times the pattern is repeated
					reps = getReps(full,fullIndexStart,fullIndexEnd);

					//splits up the element where the overlap occurs
					patternArr.add(i+1,patternArr.get(i));
					repArr.add(i+1,repArr.get(i)-difference);
					repArr.set(i, difference);
					i++;

					//same stuff as before
					if(reps>1)
					{
						newPatternArr.add(patternArr.get(i-1));
						newRepArr.add(repArr.get(i-1));

						ArrayList<String> newLetters = new ArrayList<String>();
						ArrayList<Integer> newReps = new ArrayList<Integer>();

						String patternStr;

						for(int j = i; j<i+pattSize; j++)
						{
							newLetters.add(patternArr.get(j));
							newReps.add(repArr.get(j));
						}

						if(keys.containsKey(newLetters))
						{
							patternStr = keys.get(newLetters);
						}
						else {
							patternStr = makeKey(newLetters,newReps);
							keys.put(newLetters,patternStr);
						}

						newPatternArr.add(patternStr);
						newRepArr.add(reps);

						if(repArr.get(i+pattSize-1) <repArr.get(((i+(pattSize*reps)-1))))
						{
							int newRepsOverboard = repArr.get(((i+(pattSize*reps)-1)))-repArr.get(i+pattSize-1);
							newPatternArr.add(patternArr.get(((i+(pattSize*reps)-1))));
							newRepArr.add(newRepsOverboard);
						}
						i = i+((reps)*pattSize)-1;
					}
				}
				else
				{
					newPatternArr.add(patternArr.get(i));
					newRepArr.add(repArr.get(i));
				}
			}
		}

		//creation of new empty ArrayLists for recursive calls
		ArrayList<String> p = new ArrayList<String>();
		ArrayList<Integer> nums = new ArrayList<Integer>();

		//if no patterns were found, then search for patterns again, but increase patternSize to search for longer patterns
		if(isEqual(patternArr,newPatternArr))
			return getNotation(codes,patternArr,repArr,pattSize+1,p,nums,keys);
		/*
		 * if a pattern was found:
		 * notate old arrays and add them to the ArrayList of notations (codes)
		 * search for patterns with: start patternSize with 1 again but on the new notation arrays
		 */
		else
		{
			String patternStr = makeKey(patternArr,repArr);
			codes.add(patternStr);

			return getNotation(codes,newPatternArr, newRepArr,1,p,nums,keys);
		}
	}

	//-------------------------------------------------------------------------------------------------------------------

	/**
	 * Determines if two ArrayLists carry equal values
	 *
	 * @param arr1
	 * @param arr2
	 * @return returns whether the ArrayLists are equal
	 */
	public static boolean isEqual(ArrayList<String> arr1, ArrayList<String> arr2)
	{
		if(arr2.size() != arr1.size())
			return false;

		for(int i = 0; i<arr1.size(); i++)
		{
			if(arr1.get(i).equals(arr2.get(i)) == false)
				return false;
		}
		return true;
	}

	/**
	 * Finds the corresponding starting index in the full array from the patternArr
	 *
	 * @param repArr
	 * @param letterIndex
	 * @return
	 */
	public static int getFullIndexStart(ArrayList<Integer> repArr, int letterIndex)
	{
		int index = 0;
		if(letterIndex == 0)
			return 0;

		for(int i = 0; i< letterIndex; i++)
		{
			index = index+(repArr.get(i));

		}
		return index;
	}

	/**
	 * Finds the corresponding ending index in the full array from the patternArr
	 *
	 * @param repArr
	 * @param letterIndex
	 * @return
	 */
	public static int getFullIndexEnd(ArrayList<Integer> repArr, int letterIndex)
	{
		int index = 0;
		for(int i = 0; i<=letterIndex; i++)
		{
			index = index+(repArr.get(i));

		}
		return index-1;
	}


	/**
	 * expands the patternArr so that all values are repeated once
	 *
	 * @param patternArr
	 * @param repArr
	 * @return
	 */
	public static ArrayList<String> makeFullArray(ArrayList<String> patternArr, ArrayList<Integer> repArr)
	{
		ArrayList<String> full = new ArrayList<String>();

		for(int i = 0; i<repArr.size(); i++)
		{
			for(int j = 0; j < repArr.get(i); j++)
			{
				full.add(patternArr.get(i));
			}
		}
		return full;
	}

	/**
	 *
	 * @param letters
	 * @param repCount
	 * @param start
	 * @param end
	 * @param counter
	 * @return
	 */
	public static boolean isPattern(ArrayList<String> letters, ArrayList<Integer> repCount, int start, int end, int counter)
	{
		int reps = getReps(letters,start,end);
		int reps2 = getRepsInt(repCount,start,end,(reps*counter));

		if(reps>1 && reps == reps2)
			return true;

		return false;

	}
	/**
	 *
	 * checks to see if pattern could still exist with a starting point that is not the first when corresponding to the full array
	 *
	 * ex. with "aababab" it would be condensed to "ababab", but the pattern of "ab" would not be recognized because the "a" is repeated twice
	 * so it checks with the full array to see that the pattern does exist by taking off the first "a"
	 * that "a" is then put into the array before the pattern is notated
	 * the resulting notation would be "a3(ab)"
	 *
	 * @param patternArr: current notation of original string array
	 * @param repsArr: number of repetitions for each element in patternArr
	 * @param i: pattern starting point in patternArr
	 * @param pattLength: length of pattern
	 * @return returns true if pattern could still exist with a starting point that is not the first when corresponding to the full array
	 */
	public static boolean isPatternWithDifferentStartingPoint(ArrayList<String> patternArr, ArrayList<Integer> repsArr, int i, int pattLength)
	{
		int reps = getReps(patternArr,i,i+pattLength-1);

		if(reps >1  && repsArr.get(i)>repsArr.get(i+pattLength))
		{
			int repRequirement = repsArr.get(i+pattLength);
			for(int o =1; o<reps; o++)
			{
				if(repsArr.get(o*pattLength+i) != repRequirement)
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param repsArr
	 * @param i: index in patternArr
	 * @param pattLength
	 * @return returns the difference between the old starting point and the new starting point in the full array
	 */
	public static int getDifference(ArrayList<Integer> repsArr, int i, int pattLength)
	{
		if(repsArr.get(i)>repsArr.get(i+pattLength))
		{
			return repsArr.get(i)-repsArr.get(i+pattLength);
		}
		return -1;
	}

	/**
	 *
	 * @param patternArr: ArrayList of notations that make up the original string
	 * @param repsArr: the amount of times each element in patternArr repeats
	 * @return returns the notation of the original string based off of patternArr and repsArr
	 */
	public static String makeKey(ArrayList<String> patternArr, ArrayList<Integer> repsArr)
	{
		//The string that will hold the notation of patternArr
		String patternStr = "";

		for(int i = 0; i<patternArr.size(); i++)
		{

			//if pattern is of length 2 and repeats 2 times
			if(patternArr.get(i).length() == 2 && repsArr.get(i) == 2)
				patternStr = patternStr +patternArr.get(i)+patternArr.get(i);
			//if pattern length is longer than one and repeats
			else if(patternArr.get(i).length()>1 && repsArr.get(i)>1)
				patternStr = patternStr +Integer.toString(repsArr.get(i))+"("+patternArr.get(i)+")";
			//if pattern length is longer than one and does NOT repeat
			else if(patternArr.get(i).length()>1 && repsArr.get(i)==1)
				patternStr = patternStr+patternArr.get(i);
			//if pattern of length one and repeats
			else if(patternArr.get(i).length() == 1 && repsArr.get(i)>1)
				patternStr = patternStr+Integer.toString(repsArr.get(i))+patternArr.get(i);
			//if pattern is longer than one and does NOT repeat
			else
				patternStr = patternStr+patternArr.get(i);
		}
		return patternStr;
	}


	// Takes in the full array of letters, and checks to see how many times
	// possPattern is a consecutive pattern in the array, starting at that index
	public static int getReps(char[] letters, int start, int end) {

		int patternLength = (end - start)+1;
		int reps = 1;

		for (int i = start+patternLength; i < letters.length; i = i + patternLength) {

			boolean isPattern = true;
			int patternIndex = 0;

			for (int j = i; j < i + patternLength; j++) {
				if (j < letters.length) {
					if (letters[j] != letters[patternIndex + start]) {
						isPattern = false;
						break;
					}
				}
				else if(patternIndex<patternLength)
				{
					isPattern = false;
					break;
				}
				patternIndex++;
			}
			if (isPattern == false)
				break;
			else
				reps++;
		}
		return reps;
	}


	// Takes in the full array of Strings, and checks to see how many times that String shows up consecutively
	// possPattern is a consecutive pattern in the array, starting at that index
	public static int getReps(ArrayList<String> letters, int start, int end) {

		int patternLength = (end - start)+1;
		int reps = 1;

		for (int i = start+patternLength; i < letters.size(); i = i + patternLength) {

			boolean isPattern = true;
			int patternIndex = 0;

			for (int j = i; j < i + patternLength; j++) {
				if (j < letters.size()) {
					if (!letters.get(j).equals(letters.get(patternIndex + start))) {
						isPattern = false;
						break;
					}
				}
				else if(patternIndex<patternLength)
				{
					isPattern = false;
					break;
				}
				patternIndex++;
			}
			if (isPattern == false)
				break;
			else
				reps++;
		}
		return reps;
	}

	public static int getRepsInt(ArrayList<Integer> arr, int start, int end, int ending) {

		int patternLength = (end - start)+1;
		int reps = 1;

		for (int i = start+patternLength; i < ending+1; i = i + patternLength) {

			boolean isPattern = true;
			int patternIndex = 0;

			for (int j = i; j < i + patternLength; j++) {
				if (j < arr.size()) {
					if (!arr.get(j).equals(arr.get(patternIndex + start))) {
						isPattern = false;
						break;
					}
				}
				else if(patternIndex<patternLength-1)
				{
					isPattern = false;
					break;
				}
				patternIndex++;
			}
			if (isPattern == false)
				break;
			else
				reps++;
		}
		return reps;
	}


	//---------------------------------------------------------------------------------------------------------------

	public static ArrayList<String> makeStringArray(String str) {

		ArrayList<String> arr = new ArrayList<String>();

		for(int i = 0; i<str.length(); i++)
		{
			arr.add(Character.toString(str.charAt(i)));
		}

		return arr;
	}

	public static char[] turnIntoCharArray(String str)
	{
		char[] array = new char[str.length()];
		for(int i = 0; i<str.length(); i++)
		{
			array[i] = str.charAt(i);
		}

		return array;
	}
}
