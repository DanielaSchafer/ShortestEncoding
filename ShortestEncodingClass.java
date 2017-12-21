import java.util.ArrayList;
import java.util.HashMap;

public class ShortestEncodingClass {

	public static void main(String[] args) {
		String str = "abcabc";
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
			int reps = isPattern(letters,i,i);
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
		System.out.println("counter " + counter);

		int remainderStart = (letters.size() - counter)+1;

		//base case: if the pattern is larger than half of
		if(counter >letters.size()/2) {
			String patternStr = "";

			for(int j = 0; j<letters.size(); j++)
			{
				//System.out.println("letters " +letters);
				if(j<letters.size()) {
					System.out.println("hello2 " + patternStr);
					if(letters.get(j).length() >1 && repsOfPatterns.get(j)>1)
						patternStr = patternStr +Integer.toString(repsOfPatterns.get(j))+ "("+letters.get(j)+")";
					else if(letters.get(j).length() == 1 && repsOfPatterns.get(j)>1)
						patternStr = patternStr+Integer.toString(repsOfPatterns.get(j))+letters.get(j);
					else if(repsOfPatterns.get(j) == 1 && letters.get(j).length() >1)
						patternStr = patternStr+letters.get(j);
					else
						patternStr = patternStr+letters.get(j);
				}
			}
			System.out.println("patt "+patternStr);
			return;
		}

		//iterates through all the elements in the array to see if there are any patterns of length counter
		for(int i = 0; i<letters.size(); i++)
		{

			//System.out.println("on letter: "+letters.get(i));
			System.out.println("startPoint " + i + " endPoint " + (counter+(i-1)));
			int reps = isPattern(letters,i,i+counter-1);
			int reps2 = isPatternInt(repsOfPatterns,i,i+counter-1,reps+i);
			System.out.println("reps "+reps + " reps2 " +reps2);
			if(reps>1 && reps == reps2)
			{
				System.out.println("REPETITION");
				String patternStr = "";
				for(int j = i; j<i+counter;j++)
				{
					if(j<letters.size()) {
						if(letters.get(j).length() >1) {
							patternStr = patternStr +Integer.toString(repsOfPatterns.get(j))+ "("+letters.get(j)+")";
						}
						else if(repsOfPatterns.get(j) == 1){
							patternStr = patternStr+letters.get(j);
						}
						else
							patternStr = patternStr+Integer.toString(repsOfPatterns.get(j))+letters.get(j);
					}
				}
				System.out.println("str " +patternStr);
				pattern.add(patternStr);
				repNums.add(reps);
				i = i+((reps)*counter)-1;
				//System.out.println("i: " +i +" reps: " +reps);
			}
			else
			{
				System.out.println("NO REPETITION");
				String patternStr = "";

				if(letters.get(i).length() >1) {
					//System.out.println(letters.get(i)+" bigger than one");
					patternStr = patternStr +Integer.toString(repsOfPatterns.get(i))+ "("+letters.get(i)+")";
				}
				else if(repsOfPatterns.get(i) == 1){
					//System.out.println(letters.get(i)+ " length one");
					patternStr = patternStr+letters.get(i);
				}
				else
					patternStr = patternStr+Integer.toString(repsOfPatterns.get(i))+letters.get(i);

				System.out.println("i: " +i +" reps: " +reps);
				System.out.println("str " +patternStr);
				pattern.add(patternStr);
				repNums.add(reps);
				System.out.println(repNums);
			}
		}
		ArrayList<String> p = new ArrayList<String>();
		ArrayList<Integer> nums = new ArrayList<Integer>();

		if(pattern.isEmpty())
		{
			//System.out.println("empty pattern" + pattern);
			getNotation(letters,repsOfPatterns,counter+1,p,nums);
		}
		else
		{
			//System.out.println("pattern not empty: "+pattern);
			getNotation(pattern, repNums, counter+1,p,nums);
		}
	}


	//---------------------------------------------------------------------------------------------------------------


	//end is inclusive
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

	}

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
	public static int isPattern(char[] letters, int start, int end) {
		System.out.println(" start "+start + " end "+end);
		int patternLength = (end - start)+1;
		System.out.println(patternLength);
		int reps = 1;

		for (int i = start+patternLength; i < letters.length; i = i + patternLength) {

			System.out.println("reps " +reps);

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


	// Takes in the full array of letters, and checks to see how many times
	// possPattern is a consecutive pattern in the array, starting at that index
	public static int isPattern(ArrayList<String> letters, int start, int end) {

		System.out.println("start " + start+ " end " +end);

		int patternLength = (end - start)+1;
		System.out.println(patternLength);
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

	public static int isPatternInt(ArrayList<Integer> arr, int start, int end, int ending) {
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
