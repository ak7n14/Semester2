import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TestAnagram {
	Anagram anagram;
	String word = "101";
	
	@Before
	public void setUp() {
		anagram = new Anagram(word);
	}

	@After
//	public void tearDown() {
//		// not currently used
//	}
//	
	@Test
	public void testForInvalidAnagram() {
		List<String> anagramList = anagram.generate();
		for(String anagram: anagramList)
			assertTrue(isAnagram(word, anagram));
	}
	
	@Test
	public void testForAnagramCount() {
		List<String> anagramList = anagram.generate();
		assertEquals("Anagram count", calcAnagramCount(word), anagramList.size());
	}
	
	@Test
	public void testForNull(){
		anagram = new Anagram(null);
		List<String> anagramList = anagram.generate();
		assertEquals("Anagram null", 0, anagramList.size());
	}
	
	@Test
	public void testForZeroCharacters(){
		anagram = new Anagram("");
		List<String> anagramList = anagram.generate();
		assertEquals("Anagram null", 0, anagramList.size());
	}
	
	@Test
	public void testForSingleCharacter(){
		anagram = new Anagram("a");
		List<String> anagramList = anagram.generate();
		assertEquals("Anagram null", 1, anagramList.size());
	}
	public static boolean isAnagram(String str1, String str2){

	    if(str1.length() != str2.length()) {return false;}

	    char[] a, b;
	    Arrays.sort(a = str1.toCharArray());
	    Arrays.sort(b = str2.toCharArray());
	    return Arrays.equals(a,b);
	}
	//Count Strings
	public static int calcAnagramCount(String anagram){
		int n = anagram.length();
		Set<Character> uniqueLetters = new HashSet<Character>();
		for(Character character: anagram.toCharArray())
			uniqueLetters.add(character);
		int r = uniqueLetters.size();
		
		if(r < n)
			return (fact(n)/(fact(r)*fact(n-r)));
		else
			return (fact(n)/fact(n-r));			
	}
	//Mathematical formula
	private static int fact (int input)
	{
	  int x, fact = 1;
	  for (x = input; x > 1; x--)
	     fact *= x;
	  return fact;
	}
}
