import java.util.ArrayList;
import java.util.HashMap;

public class ShortestEncodingClass {

	public static void main(String[] args) {
		String expected3 = "3(abc)";
		String expected =  "25(jdfjd8(jf)jsf2(jfjd)jfdjfjdjg2(djfjdjf)5(djfdjfj)dfj3(djf)jdjf2(djfjdfjdjfdjfjdj)fdjfjdfjdjdjff2(j3(djf))jdfj4(djf)jdjf3(djfdjfj)dfjd)";
		String str3 =      "aaabbbaaabbbaaabbbaaabbb";
		String str =     "jdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjdjdfjdjfjfjfjfjfjfjfjfjsfjfjdjfjdjfdjfjdjgdjfjdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdjfdjfjdjfdjfjdfjdjfdjfdjfjdjfdjfjdfjdjfdjfjdjdjfjdfjdjfdjfjdjfdjfjdfjdjdjffjdjfdjfdjfjdjfdjfdjfjdfjdjfdjfdjfdjfjdjfdjfdjfjdjfdjfjdjfdjfjdfjd ";
		String str2 = "aaabaabaab";
		String expected2 = "a3(aab)";
		char[] letters = turnIntoCharArray(str);

		//System.out.println(isPattern(letters,0,1));

		ArrayList<String> patternList = new ArrayList<String>();
		ArrayList<Integer> numOfAppearances = new ArrayList<Integer>();
		getNotationFirstTime(letters, patternList, numOfAppearances);
		//System.out.println(patternList);
		//System.out.println(numOfAppearances);

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
			System.out.println("true");
		else
			System.out.println("false");
	}




	//gets the repetition of single characters
	public static void getNotationFirstTime(char[] letters, ArrayList<String> patternArr, ArrayList<Integer> repArr)
	{
		for(int i = 0; i<letters.length; i++)
		{
			int reps = getReps(letters,i,i);
			if(reps >1)
			{
				String patternStr = "";
				for(int j = i; j<i+1; j++)
				{
					patternStr = patternStr+letters[j];
				}
				patternArr.add(patternStr);
				repArr.add(reps);
				i=i+(1*(reps-1));
			}
			else
			{
				patternArr.add(Character.toString(letters[i]));
				repArr.add(reps);
			}
		}
	}

	//notation of everything after single characters
	public static String getNotation(ArrayList<String> codes, ArrayList<String> patternArr, ArrayList<Integer> repArr, int pattSize, ArrayList<String> newPatternArr, ArrayList<Integer> newRepArr, HashMap<ArrayList<String>, String> keys)
	{
		//System.out.println(patternArr+"\n"+repArr);

		//base case: if the pattern is larger than half of patternArr length
		if(pattSize >patternArr.size()/2) {
			String patternStr = makeKey(patternArr,repArr);
			codes.add(patternStr);

			System.out.println(patternStr);

			int minIndex = 0;

			for(int i = 0; i<codes.size(); i++)
			{
				if(codes.get(i).length()<codes.get(minIndex).length())
					minIndex = i;
			}
			System.out.println("codes "+codes);
			return codes.get(minIndex);
		}

		//creates an array of the values in patternArr but expanded
		ArrayList<String> full = makeFullArray(patternArr,repArr);

		//iterates through all the elements in the array to see if there are any patterns of length pattSize
		for(int i = 0; i<patternArr.size(); i++)
		{
			int fullIndexStart = getFullIndexStart(patternArr, repArr, full, i);
			//int fullIndexEnd = getFullIndex(patternArr, repArr, full, i+pattSize-1);
			//System.out.println("Index " + i+" fullIndex "+fullIndexStart);

			//if the pattern length is longer than the space left in the array, then add the part to the array
			if(i+pattSize-1 >patternArr.size()-1)
			{
				newPatternArr.add(patternArr.get(i));
				newRepArr.add(repArr.get(i));
			}
			//if the pattern can be completed, check to see if it is repeated
			else {
				int fullIndexEnd = getFullIndexEnd(patternArr, repArr, full, i+pattSize-1);

				int reps = getReps(full,fullIndexStart,fullIndexEnd);

				//check if pattern is repeated
				if(reps>1)
				{

					//checks for overlaps
					int numOfRepsFull = getReps(full,fullIndexStart,fullIndexStart);
					int numOfReps = repArr.get(i);

					if(numOfRepsFull != numOfReps)
					{
						System.out.println("overlap");
						overlap(patternArr,repArr,full,newPatternArr, newRepArr, i);
					}
					else if(i == 0 && fullIndexStart != 0)
					{
						System.out.println("overlap");
						overlap(patternArr,repArr,full,newPatternArr, newRepArr, i);
					}

					//builds notation for pattern
					ArrayList<String> newLetters = new ArrayList<String>();
					ArrayList<Integer> newReps = new ArrayList<Integer>();

					String patternStr;

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
					 * ex. if pattern is "ab" and string is "abababbb" it would be notated as "3(ab)2b"
					 * b would be the element that ran over the alloted amount in the pattern
					 */
					if(repArr.get(i+pattSize-1) <repArr.get(((i+(pattSize*reps)-1))))
					{
						int newRepsOverboard = repArr.get(((i+(pattSize*reps)-1)))-repArr.get(i+pattSize-1);
						newPatternArr.add(patternArr.get(((i+(pattSize*reps)-1))));
						newRepArr.add(newRepsOverboard);
					}

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
					int difference = getNewStartingPoint(patternArr,repArr,i,pattSize);
					fullIndexStart = getFullIndexStart(patternArr, repArr, full, i)+difference;
					reps = getReps(full,fullIndexStart,fullIndexEnd);

					patternArr.add(i+1,patternArr.get(i));
					repArr.add(i+1,repArr.get(i)-difference);
					repArr.set(i, difference);
					i++;

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

		//creation of new empty ArrayLists
		ArrayList<String> p = new ArrayList<String>();
		ArrayList<Integer> nums = new ArrayList<Integer>();

		if(isEqual(patternArr,newPatternArr))
			return getNotation(codes,patternArr,repArr,pattSize+1,p,nums,keys);
		else
		{
			String patternStr = makeKey(patternArr,repArr);
			codes.add(patternStr);
			return getNotation(codes,newPatternArr, newRepArr,1,p,nums,keys);
		}
	}

	//-------------------------------------------------------------------------------------------------------------------

	public static void overlap(ArrayList<String> letters,ArrayList<Integer> repsOfPatterns,ArrayList<String> full, ArrayList<String> pattern, ArrayList<Integer> repNums, int i)
	{
		letters.add(i+1,letters.get(i));
		repsOfPatterns.add(i+1,1);
		repsOfPatterns.set(i, repsOfPatterns.get(i)-1);
		if(repsOfPatterns.get(i) == 0)
		{
			letters.remove(i);
			repsOfPatterns.remove(i);
		}
		full = makeFullArray(letters,repsOfPatterns);

		pattern.add(letters.get(i));
		repNums.add(repsOfPatterns.get(i));
		i++;
	}

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

	public static int getFullIndexStart(ArrayList<String> letters, ArrayList<Integer> repCount, ArrayList<String> full, int letterIndex)
	{
		int index = 0;
		if(letterIndex == 0)
			return 0;

		for(int i = 0; i< letterIndex; i++)
		{
			index = index+(repCount.get(i));

		}
		return index;
	}

	public static int getFullIndexEnd(ArrayList<String> letters, ArrayList<Integer> repCount, ArrayList<String> full, int letterIndex)
	{
		int index = 0;
		for(int i = 0; i<=letterIndex; i++)
		{
			index = index+(repCount.get(i));

		}
		return index-1;
	}


	public static ArrayList<String> makeFullArray(ArrayList<String> letters, ArrayList<Integer> repCount)
	{
		ArrayList<String> full = new ArrayList<String>();

		for(int i = 0; i<repCount.size(); i++)
		{
			for(int j = 0; j < repCount.get(i); j++)
			{
				full.add(letters.get(i));
			}
		}
		return full;

	}

	public static boolean isPattern(ArrayList<String> letters, ArrayList<Integer> repCount, int start, int end, int counter)
	{
		int reps = getReps(letters,start,end);
		int reps2 = getRepsInt(repCount,start,end,(reps*counter));

		if(reps>1 && reps == reps2)
			return true;

		return false;

	}

	public static boolean isPatternWithDifferentStartingPoint(ArrayList<String> letters, ArrayList<Integer> repsOfPatterns, int i, int counter)
	{
		int reps = getReps(letters,i,i+counter-1);
		//System.out.println("reps "+reps);
		if(reps >1  && repsOfPatterns.get(i)>repsOfPatterns.get(i+counter))
		{
			int repRequirement = repsOfPatterns.get(i+counter);
			for(int o =1; o<reps; o++)
			{
				if(repsOfPatterns.get(o*counter+i) != repRequirement)
				{
					return false;
				}
			}

			return true;
		}
		return false;
	}

	public static int getNewStartingPoint(ArrayList<String> letters, ArrayList<Integer> repsOfPatterns, int i, int counter)
	{
		if(repsOfPatterns.get(i)>repsOfPatterns.get(i+counter))
		{
			return repsOfPatterns.get(i)-repsOfPatterns.get(i+counter);
		}
		return -1;
	}


	public static String makeKey(ArrayList<String> letters, ArrayList<Integer> reps)
	{
		String patternStr = "";

		for(int i = 0; i<letters.size(); i++)
		{

			//if pattern is of length 2 and repeats 2 times
			if(letters.get(i).length() == 2 && reps.get(i) == 2)
				patternStr = patternStr +letters.get(i)+letters.get(i);
			//if pattern length is longer than one and repeats
			else if(letters.get(i).length()>1 && reps.get(i)>1)
				patternStr = patternStr +Integer.toString(reps.get(i))+"("+letters.get(i)+")";
			//if pattern length is longer than one and does NOT repeat
			else if(letters.get(i).length()>1 && reps.get(i)==1)
				patternStr = patternStr+letters.get(i);
			//if pattern of length one and repeats
			else if(letters.get(i).length() == 1 && reps.get(i)>1)
				patternStr = patternStr+Integer.toString(reps.get(i))+letters.get(i);
			//if pattern is longer than one and does NOT repeat
			else
				patternStr = patternStr+letters.get(i);
		}
		return patternStr;
	}

	//---------------------------------------------------------------------------------------------------------------
	public static Character[] makeCharArray(char[] array, int start, int end) {
		int length = (end - start) + 1;
		Character[] newArray = new Character[length];
		int index = start;
		for (int i = 0; i < length; i++) {
			newArray[i] = array[index];
		}
		return newArray;
	}

	public static ArrayList<String> makeStringArray(String str) {

		ArrayList<String> arr = new ArrayList<String>();

		for(int i = 0; i<str.length(); i++)
		{
			arr.add(Character.toString(str.charAt(i)));
		}

		return arr;
	}

	public static void print(char[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	public static void print(char[] array,int start, int end) {
		for (int i = start; i <=end; i++) {
			System.out.print(array[i] + " ");
		}
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

	public static String turnIntoString(char[] arr)
	{
		String str = "";
		for(int i = 0; i<arr.length; i++)
		{
			str = str+arr[i];
		}
		return str;
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

		//printArraySec(letters,start,end);
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
		//System.out.println("reps: "+reps);
		return reps;
	}

	public static int getRepsInt(ArrayList<Integer> arr, int start, int end, int ending) {
		//System.out.println("s "+start+ " e " +end);
		//System.out.println("arr length " + arr.size() + " ending "+ ending);
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

	public static void printArraySec(ArrayList<String> arr, int s, int e)
	{
		System.out.println();
		System.out.print("arraySection: ");
		for(int i = s; i<=e; i++)
		{
			if(i<arr.size())
				System.out.print(arr.get(i)+" ");
		}
		System.out.println();
	}
}
