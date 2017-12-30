import java.util.ArrayList;
import java.util.HashMap;

public class ShortestEncodingClass {

	public static void main(String[] args) {
		String str =      "fdjfdjdjfdjdjfdj";
		String expected = "f3(djfdj)";
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
		String notation = getNotation(makeStringArray(str),patternList,numOfAppearances,2,pList,numReps,keys);
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
	public static String getNotation(ArrayList<String> realFull, ArrayList<String> letters, ArrayList<Integer> repsOfPatterns, int counter, ArrayList<String> pattern, ArrayList<Integer> repNums, HashMap<ArrayList<String>, String> keys)
	{

		//base case: if the pattern is larger than half of
		if(counter >letters.size()/2) {
			String patternStr = makeKey(letters,repsOfPatterns);
			return patternStr;
		}

		ArrayList<String> full = makeFullArray(letters,repsOfPatterns);
		System.out.println(letters+"\n"+repsOfPatterns+"\n"+full+"\n"+counter+"\n");

		if(full.size()>16)
			try {
				Thread.sleep(1000000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		//iterates through all the elements in the array to see if there are any patterns of length counter
		for(int i = 0; i<letters.size(); i++)
		{
			System.out.println("i "+i+" size "+ letters.size());
			int fullIndexStart = getFullIndex(letters, repsOfPatterns, full, i);
			System.out.println("full index start"+fullIndexStart);

			if(fullIndexStart+counter > full.size()-1 || i+counter-1 >letters.size()-1)
			{
				pattern.add(full.get(fullIndexStart));
				repNums.add(1);
			}
			else {

				int fullIndexEnd = getFullIndex(letters, repsOfPatterns, full, i+counter-1);

				int reps = getReps(full,fullIndexStart,fullIndexEnd);

				if(reps>1)
				{
					int numOfRepsFull = getReps(full,fullIndexStart,fullIndexStart);
					int numOfReps = repsOfPatterns.get(i);

					if(numOfRepsFull != numOfReps)
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
						full = makeFullArray(letters,repsOfPatterns);

						pattern.add(letters.get(i));
						repNums.add(repsOfPatterns.get(i));
						i++;
					}

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
						full = makeFullArray(letters,repsOfPatterns);

						pattern.add(letters.get(i));
						repNums.add(repsOfPatterns.get(i));
						i++;
					}

					System.out.println("PATTERN");

					ArrayList<String> newLetters = new ArrayList<String>();
					ArrayList<Integer> newReps = new ArrayList<Integer>();

					String patternStr;

					for(int j = i; j<i+counter; j++)
					{
						newLetters.add(letters.get(j));
						newReps.add(repsOfPatterns.get(j));
					}
					//System.out.println(newLetters+ "\n"+reps);

					if(keys.containsKey(newLetters))
					{
						System.out.println("CONTAINS KEY");
						patternStr = keys.get(newLetters);
					}
					else {
						patternStr = makeKey(newLetters,newReps);
						keys.put(newLetters,patternStr);
					}

					pattern.add(patternStr);
					repNums.add(reps);
					i = i+((reps)*counter)-1;
				}
				else
				{
					pattern.add(letters.get(i));
					repNums.add(repsOfPatterns.get(i));
				}
			}
		}
		ArrayList<String> p = new ArrayList<String>();
		ArrayList<Integer> nums = new ArrayList<Integer>();

		//System.out.println(keys);

		if(isEqual(letters,pattern))
		{
			return getNotation(full,letters,repsOfPatterns,counter+1,p,nums,keys);
		}
		else
		{
		/*	ArrayList<Integer> fullReps = new ArrayList<Integer>();
			for(int i = 0; i<full.size(); i++)
			{
				fullReps.add(1);
			}
			String notFull = getNotation(full,pattern, repNums,1,p,nums,keys);
			String fullRedo = getNotation(full,full,fullReps,counter+1,p, nums,keys);
			if(notFull.length()<fullRedo.length())
				return notFull;
			else
				return fullRedo;*/
			return getNotation(full,pattern, repNums,1,p,nums,keys);
		}
	}

	//-------------------------------------------------------------------------------------------------------------------


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

	public static int getFullIndex(ArrayList<String> letters, ArrayList<Integer> repCount, ArrayList<String> full, int letterIndex)
	{
		//System.out.println("GET FULL INDEX");
		//System.out.println(letters+"\n"+repCount);
		//System.out.println(letterIndex);
		//System.out.println(letters.size());
		int index = 0;
		for(int i = 0; i<= letterIndex; i++)
		{
			index = index+(repCount.get(i));//*letters.get(i).length());

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
				/*if(letters.get(i).length()>1)
				{
					for(int k = 0; k<letters.get(i).length(); k++)
					{
						full.add(Character.toString(letters.get(i).charAt(k)));
					}
				}else*/
					full.add(letters.get(i));
			}
		}
		return full;

	}

	public static boolean isPattern(ArrayList<String> letters, ArrayList<Integer> repCount, int start, int end, int counter)
	{
		int reps = getReps(letters,start,end);
		int reps2 = getRepsInt(repCount,start,end,(reps*counter));
		//System.out.println("reps2 "+reps2 + " reps "+reps);

		if(reps>1 && reps == reps2)
			return true;

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
