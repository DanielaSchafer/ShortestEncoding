import java.util.ArrayList;
import java.util.HashMap;

public class ShortestEncodingClass {

	public static void main(String[] args) {
		String str = "abcbcbca";
		char[] letters = turnIntoCharArray(str);

		//System.out.println(isPattern(letters,0,1));

		ArrayList<String> patternList = new ArrayList<String>();
		ArrayList<Integer> numOfAppearances = new ArrayList<Integer>();
		getNotationFirstTime(letters, patternList, numOfAppearances);
		System.out.println(patternList);
		System.out.println(numOfAppearances);

		ArrayList<String> pList = new ArrayList<String>();
		ArrayList<Integer> numReps = new ArrayList<Integer>();
		getNotation(patternList,numOfAppearances,2,pList,numReps);
		System.out.println(pList);
		System.out.println(numReps);

		//getNotation(letters, 2,patternList, numOfAppearances);

		//System.out.println(isPattern(letters, 0, 3));


		//HashMap<Character[],String> notations = new HashMap<Character[],String>();
		//System.out.println("key "+findKey(letters,0,letters.length-1,2,-1,-1));
	}




	//gets the repetition of single characters
	public static void getNotationFirstTime(char[] letters, ArrayList<String> pattern, ArrayList<Integer> numOfApp)
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
				pattern.add(patternStr);
				numOfApp.add(reps);
				i=i+(1*(reps-1));
			}
			else
			{
				pattern.add(Character.toString(letters[i]));
				numOfApp.add(reps);
			}
		}
	}

	//notation of everything after single characters
	public static void getNotation(ArrayList<String> letters, ArrayList<Integer> repsOfPatterns, int counter, ArrayList<String> pattern, ArrayList<Integer> repNums)
	{
		System.out.println( letters);
		System.out.println(repsOfPatterns);

		//base case: if the pattern is larger than half of
		if(counter >letters.size()/2) {
			String patternStr = makeKey(letters,repsOfPatterns);
			System.out.println("patt "+patternStr);
			return;
		}

		ArrayList<String> full = makeFullArray(letters,repsOfPatterns);

		System.out.println("full    "+full);
		System.out.println("letters "+letters);

		//iterates through all the elements in the array to see if there are any patterns of length counter
		for(int i = 0; i<letters.size(); i++)
		{
			System.out.println(letters+"\n"+repsOfPatterns);
			System.out.println("length " + +letters.size()+ " i "+ i+ " -- "+(i+counter-1));
			int fullIndexStart = getFullIndex(letters, repsOfPatterns, full, i);
			int fullIndexEnd = getFullIndex(letters, repsOfPatterns, full, i+counter-1);

			int reps = getReps(full,fullIndexStart,fullIndexEnd);

			if(i == 0 && fullIndexStart != 0)
			{
				System.out.println("OVERLAP");
				letters.add(i+1,letters.get(i));
				repsOfPatterns.add(i+1,1);
				repsOfPatterns.set(i, repsOfPatterns.get(i)-1);
				if(repsOfPatterns.get(i) == 0)
				{
					letters.remove(i);
					repsOfPatterns.remove(i);
				}
				System.out.println("full1 " +full);
				full = makeFullArray(letters,repsOfPatterns);
				System.out.println("full2 " +full);

				pattern.add(letters.get(i));
				repNums.add(repsOfPatterns.get(i));
				System.out.println("new2 "+ repsOfPatterns);
				System.out.println("repNums "+repNums);
				i++;

			}

			System.out.println("reps "+reps);

			if(reps>1)
			{
				System.out.println("PATTERN");
				String patternStr = "";

				for(int j = i; j<i+counter;j++)
				{
					if(j<letters.size()) {
						//if the length of the pattern is 2 and it repeats 2 times
						//write out the whole pattern
						if(letters.get(j).length() == 2 && reps == 2)
							patternStr = patternStr +letters.get(j)+letters.get(j);
						//if the length of the pattern is greater than one
						//put a number and give it parenthesis
						else if(full.get(j).length() >1) {
							patternStr = patternStr +Integer.toString(repsOfPatterns.get(j))+ "("+letters.get(j)+")";
						}
						//if the length of the pattern is one
						//put a number in front of it
						else if(letters.get(j).length() == 1 && repsOfPatterns.get(j) >1){
							patternStr = patternStr+Integer.toString(repsOfPatterns.get(j))+letters.get(j);
						}
						else
							patternStr = patternStr+letters.get(j);
					}
				}


				pattern.add(patternStr);
				repNums.add(reps);
				System.out.println(repNums);
				i = i+((reps)*counter)-1;

				System.out.println("pattern "+pattern);

			}
			else
			{
				pattern.add(letters.get(i));
				repNums.add(1);
			}
		}
		ArrayList<String> p = new ArrayList<String>();
		ArrayList<Integer> nums = new ArrayList<Integer>();

		if(pattern.isEmpty())
		{
			getNotation(letters,repsOfPatterns,counter+1,p,nums);
		}
		else
		{
			getNotation(pattern, repNums, counter+1,p,nums);
		}
	}

	public static int getFullIndex(ArrayList<String> letters, ArrayList<Integer> repCount, ArrayList<String> full, int letterIndex)
	{
		int index = 0;
		for(int i = 0; i<= letterIndex; i++)
		{
			index = index+(repCount.get(i));

		}


		return index-1;
	}

	public static void makeNewArray(ArrayList<String> letters, ArrayList<Integer> repCount, ArrayList<String> full, int patStart, int patEnd)
	{

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
		System.out.println("reps2 "+reps2 + " reps "+reps);

		if(reps>1 && reps == reps2)
			return true;

		/*if(reps == 2)
		{
			if(repCount.get(start) >= repCount.get(start+counter))
				return true;
		}

		if(reps > 2)
		{
			int firstElementRep = repCount.get(start);
			int secondElementRep = repCount.get(start+counter);
			if(firstElementRep >= secondElementRep)
			{
				for(int i = 2; i<=reps; i++)
				{
					if(repCount.get(start+(counter*i)) != secondElementRep)
						return false;
				}
				return true;
			}
		}*/

		return false;

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


	/*//end is inclusive
	public static String findKey(char[] letters, int start, int end, int counter, int lastPatternStart, int lastPatternEnd)
	{
		//print(letters,start,end);


		int patternLength = (int)(((end-start)+1)/counter);
		int ending = (patternLength+start)-1;
		int remainder = (end - (patternLength*counter))-1;
		String remainingLetters = "";
		int reps = isPattern(letters,start,ending);

		System.out.println();
		print(letters,start,ending);
		System.out.println();
		System.out.println("counter: "+counter);
		System.out.println("reps: " + reps);



		if(remainder>0) {
			for(int i = end-remainder; i<=end; i++) {
				System.out.println("i " + i + " end " + end + " remainder " +remainder);
				System.out.println();
				print(letters);
				remainingLetters = remainingLetters+letters[i];
			}
		}

		//if it is a single letter with a pattern return the previous pattern
		if(patternLength == 1 && reps == 1 && lastPatternStart != -1)
		{
			String lastPattern = "";
			for(int i = lastPatternStart; i<lastPatternEnd+1; i++)
				lastPattern = lastPattern+letters[i];
			return lastPattern;
		}//if it is a single letter without a pattern return that letter
		else if(patternLength == 1 && reps == 1 && lastPatternStart == -1)
		{
			return Character.toString(letters[start]);
		}

		System.out.println("reps*patternLength "+reps*patternLength);
		System.out.println("letterlength "+letters.length);



		//if it finds a repetition
		if( reps > 1)
		{
			//if it finds a pattern that spans the whole array, return the amount of repetitions across the string
			if(reps>1 &&(reps*patternLength == letters.length || letters.length-patternLength <= (reps*patternLength)))
			{
				if(letters.length-patternLength == 1)
					return reps + "(" + findKey(letters,start,ending,2,start,ending)+")"+letters[letters.length-1];
				else if(letters.length-patternLength < (reps*patternLength))
					return reps + "(" + findKey(letters,start,ending,2,start,ending)+")"+findKey(letters,end-remainder+1,end,2,-1,-1);
				else
					return reps + "(" + findKey(letters,start,ending,2,start,ending)+")";
			}

			System.out.println("reps "+reps);
			if(patternLength == 1) {
				if(((end-start)+1)%2 == 1)
					return Integer.toString(reps)+ending+remainingLetters;
				else
					return Integer.toString(reps)+ending;
			}
			if(reps == 2 && patternLength <= 3) {
				if(((end-start)+1)%2 == 1)
					return findKey(letters,start,ending,2,start,ending) + remainingLetters;
				else
					return findKey(letters,start,ending,2,start,ending);
			}
			if(((end-start)+1)%2 == 1)
				return Integer.toString(reps)+"("+findKey(letters,start,ending,2,start,ending)+")"+remainingLetters;
			else
				return Integer.toString(reps)+"("+findKey(letters,start,ending,2,start,ending)+")";
		}
		else
		{
			String total = "";

			for(int i = 0; i<counter; i++)
			{
				total = total+findKey(letters,start+(i*patternLength)-i,end,counter+1,-1,-1);
			}

			if(((end-start)+1)%2 == 1)
			{
				return total+remainingLetters;
			}
			else
				return total;
		}

	}*/

	public static Character[] makeCharArray(char[] array, int start, int end) {
		int length = (end - start) + 1;
		Character[] newArray = new Character[length];
		int index = start;
		for (int i = 0; i < length; i++) {
			newArray[i] = array[index];
		}
		return newArray;
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

	public static void print(Character[] array) {
		for (int i = 0; i < array.length; i++) {
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

		printArraySec(letters,start,end);
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
		System.out.println("reps: "+reps);
		return reps;
	}

	public static int getRepsInt(ArrayList<Integer> arr, int start, int end, int ending) {
		System.out.println("s "+start+ " e " +end);
		System.out.println("arr length " + arr.size() + " ending "+ ending);
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


/*
 * // return notation of letters
	public static String findKey(HashMap<Character[], String> notations, char[] letters, int start, int end) {

		if((end-start)+1 >3)
			return notations.get(turnIntoString(letters));


		int ending = ((end - start) + 1) / 2;
		System.out.println(start+" " + ending + " " + end);


		int reps = isPattern(letters, start, ending);
		System.out.println(reps);

		if (reps > 1) {

			Character[] pattern = makeCharArray(letters, start, ending);

			notation = reps + "(" + findKey(notations, letters, start, ending) + ")" + letters[letters.length - 1];

			notations.put(pattern, notation);

			return notation;
		} else {
			return (findKey(notations, letters, start, (end / 2)) + findKey(notations, letters, (end / 2) + 1, end));
		}
	}*/
