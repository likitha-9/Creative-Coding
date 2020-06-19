package application;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author likitha-9
 *
 *         Raw implementations of all of the cipher methods.
 *
 */
public class AllCipherMethods {

	/**
	 * Atbash Cipher - a monoalphabetic substitution that takes the alphabet and
	 * maps to its reverse. Ex: A->Z, B->Y, C->X, and so on.
	 */
	static public void atbash(String str) {
		StringBuilder cipher = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {

			// Atbash is applicable to only letter. All other chars are ignored.
			if (!Character.isLetter(str.charAt(i))) {
				cipher.append(str.charAt(i));
				continue;
			} else {
				int ascii = str.charAt(i); // convert current char to ascii value

				char coded_char;
				// in case of lowercase
				if (Character.isLowerCase(str.charAt(i))) {
					int numeric = 123 + (96 - ascii);
					coded_char = (char) numeric;
				}
				// in case of uppercase
				else {
					int numeric = 91 + (64 - ascii);
					coded_char = (char) numeric;
				}

				cipher.append(coded_char);
			}
		}

		System.out.println(cipher);
	}

	/**
	 * ROT13 Cipher - substitution cipher where letters are offset by 13. Ex: A->N,
	 * B->O, etc.
	 */
	static public void rot13(String str) {
		StringBuilder cipher = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {

			// Ignore anything that's not a letter.
			if (!Character.isLetter(str.charAt(i))) {
				cipher.append(str.charAt(i));
				continue;
			} else {
				boolean flag = false; // to keep track of whether letter is lower/upper (avoid redundancy)
				char ch;

				// convert non-uppercase letters to upper and then re-convert at the end
				if (!Character.isUpperCase(str.charAt(i))) {
					flag = true;
					ch = Character.toUpperCase(str.charAt(i));
				} else
					ch = str.charAt(i);

				int numeric = ch, new_ascii;
				int calc = (numeric + 13) % 90;
				if (calc < 65) // take care of rotating as well
					new_ascii = 64 + calc;
				else
					new_ascii = calc;

				char coded_char = (char) new_ascii;
				if (flag) // char had been lowercase before; so convert it back
					coded_char = Character.toLowerCase(coded_char);

				cipher.append(coded_char);
			}
		}
		System.out.println(cipher);
	}

	/**
	 * Caesar Cipher - substitution cipher where letters are shifted a number of
	 * places. Ex: if offset is 1, A->B, B->C, etc. This method uses 1 shift.
	 *
	 * (ROT13 is a Caesar with an offset of 13.)
	 */
	static public void caesar(String str) {
		StringBuilder cipher = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {

			// Ignore anything that's not a letter.
			if (!Character.isLetter(str.charAt(i))) {
				cipher.append(str.charAt(i));
				continue;
			} else {
				boolean flag = false; // to keep track of whether letter is lower/upper (avoid redundancy)
				char ch;

				// convert non-uppercase letters to upper and then re-convert at the end
				if (!Character.isUpperCase(str.charAt(i))) {
					flag = true;
					ch = Character.toUpperCase(str.charAt(i));
				} else
					ch = str.charAt(i);

				int numeric = ch, new_ascii;
				int calc = (numeric + 1) % 90;
				if (calc < 65) // take care of rotating as well
					new_ascii = 64 + calc;
				else
					new_ascii = calc;

				char coded_char = (char) new_ascii;
				if (flag) // char had been lowercase before; so convert it back
					coded_char = Character.toLowerCase(coded_char);

				cipher.append(coded_char);
			}
		}
		System.out.println(cipher);
	}

	/**
	 * Affine Cipher - a special case of the more general monoalphabetic
	 * substitution cipher. The 'key' for the Affine cipher consists of 2 numbers:
	 * a, b.
	 *
	 * a MUST be relatively prime to m (# of alphabets - 26)
	 *
	 * p - number representing letter
	 *
	 * Ciphertext letter c = ap+b (mod m)
	 */
	static public void affine(String str, int a, int b) {
		StringBuilder cipher = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isLetter(str.charAt(i))) {
				cipher.append(str.charAt(i));
				continue;
			} else {
				boolean flag = false; // to keep track whether letter is lower/uppercase
				int p;

				if (!Character.isUpperCase(str.charAt(i))) {
					p = Character.toUpperCase(str.charAt(i)) - 65; // a->0, b->1, etc,
					flag = true;
				} else
					p = str.charAt(i) - 65;

				char coded_char = (char) (((a * p + b) % 26) + 65);

				// check flag and revert back to the original case
				if (flag)
					cipher.append(Character.toLowerCase(coded_char));
				else
					cipher.append(coded_char);
			}
		}
		System.out.println(cipher);
	}

	/**
	 * Rail-Fence Cipher - a transposition cipher that follows a simple rule for
	 * mixing up the characters in the Plaintext to form the Ciphertext.
	 *
	 * The key for the railfence cipher is just the number of rails. To encrypt a
	 * piece of text, e.g. "defend the east wall of the castle",
	 *
	 * d . . . n . . . e . . . t . . . l . . . h . . . s . . . <br>
	 * . e . e . d . h . e . s . w . l . o . t . e . a . t . e <br>
	 * . . f . . . t . . . a . . . a . . . f . . . c . . . l . <br>
	 *
	 * Ciphertext: dnetlhseedheswloteateftaafcl
	 *
	 * ***ONLY SPACES ARE IGNORED. DIGITS, SPECIAL CHARS, etc. WILL NOT BE
	 * DISREGARDED FOR THIS ENCRYPTION.
	 */
	static public void railFence(String str, int key) {
		String cipher = new String(""), cleanedCipher = new String("");
		TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
		int rails; // size of treeMap (# of keys)

		if (key <= str.length()) // compare key & str's length; assign shorter one for # of rails
			rails = key;
		else
			rails = str.length();

		// initializing treeMap
		for (int i = 0; i < rails; i++) {
			treeMap.put(i, "");
		}

		int i = 0, position = 0;
		boolean flag = true; // traverse down treeMap if true; else, traverse back up
		while (i < str.length()) {

			if (str.charAt(i) == ' ') {
				i++;
				continue;
			}

			if (position == 0)
				flag = true;
			if (position == rails - 1)
				flag = false;

			if (flag) {
				treeMap.put(position, treeMap.get(position) + str.charAt(i));
				position++;
			} else {
				treeMap.put(position, treeMap.get(position) + str.charAt(i));
				position--;
			}
			i++;
		}
		// Displaying the TreeMap
		// System.out.println("TreeMap: " + treeMap);

		for (int j = 0; j < rails; j++) {
			cipher += treeMap.get(j);
		}

		// remove spaces & line breaks
		for (int j = 0; j < cipher.length(); j++) {
			if (cipher.charAt(j) != ' ' && cipher.charAt(j) != '\n')
				cleanedCipher += cipher.charAt(j);
		}

		System.out.println(cleanedCipher);
	}

	/**
	 * Baconian Cipher - each letter is assigned to a string of five binary digits.
	 * These could be the letters 'A' and 'B', the numbers 0 and 1 or whatever else.
	 * A = aaaaa I/J = abaaa R = baaaa <br>
	 * B = aaaab K = abaab S = baaab <br>
	 * C = aaaba L = ababa T = baaba <br>
	 * D = aaabb M = ababb U/V = baabb <br>
	 * E = aabaa N = abbaa W = babaa <br>
	 * F = aabab O = abbab X = babab <br>
	 * G = aabba P = abbba Y = babba <br>
	 * H = aabbb Q = abbbb Z = babbb <br>
	 *
	 * To encipher a message, e.g. 'STRIKE NOW', each letter is replaced:<br>
	 * S T R I K E N O W <br>
	 * baaab baaba baaaa abaaa abaab aabaa abbaa abbab babaa
	 *
	 * Note: Numbers, spaces, & special chars in plaintext are ignored. Only letters
	 * are considered.
	 */
	static public void baconian(String str, String char1, String char2) {
		// Method will treat char1 as 0 && char2 as 1, disregarding # of chars in each

		ArrayList<String> binary = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			String s = new String("");
			int count = i;
			for (int j = 0; j < 5; j++) {
				// decimal->binary conversation (via manual division & mod)
				if (count % 2 == 0)
					s = '0' + s;
				else
					s = '1' + s;
				count /= 2;
			}
			binary.add(s);
		}
		binary.add(8, binary.get(8)); // add a duplicate for I/J
		binary.add(19, binary.get(19)); // add a duplicate for U/V

		// for plaintext, replace 0s with char1 & 1s with char2
		StringBuilder cipher = new StringBuilder("");
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i))) {
				int ch = Character.toUpperCase(str.charAt(i)); // doesn't matter if letter is lower/uppercase
				String coded_chars = new String("");
				for (int j = 0; j < 5; j++) {
					if (binary.get(ch - 65).charAt(j) == '0')
						coded_chars += char1;
					else
						coded_chars += char2;
				}
				cipher.append(coded_chars);
			}
		}
		System.out.println(cipher);
	}

	/**
	 * Polybius Square Cipher - keys usually consist of a 25-letter 'key square'.
	 * e.g. (the letters along the top and side can be chosen arbitrarily): <br>
	 * <t>A B C D E<br>
	 * A| p h q g m<br>
	 * B| e a y l n<br>
	 * C| o f d x k<br>
	 * D| r c v s z<br>
	 * E| w b u t i<br>
	 *
	 * Plaintext: d e f e n d t h e e a s t w a l l o f t h e c a s t l e <br>
	 * Ciphertext: CCBACBBABECC EDABBA BABBDDED EABBBDBD CACB EDABBA DBBBDDEDBDBA
	 *
	 * Note: ADFGVX cipher uses a 6x6 version of the polybius square as the first
	 * step in its encryption.
	 *
	 * Note(2): Spaces, digits, special characters, anything else that's not a
	 * letter is ignored.
	 */
	static public void polybiusSquare(String str, String key, String cipherChars) {

		// add padding characters to key
		if (key.length() != 25) {
			ArrayList<Integer> chars = new ArrayList<Integer>();
			for (int i = 0; i < key.length(); i++)
				chars.add((int) Character.toLowerCase(key.charAt(i)));
			for (int i = 97; i < 123; i++) {
				if ((chars.contains(105) && (i == 106)) /* I is present */
						|| (chars.contains(106) && (i == 105))) /* J is present */
					continue;
				if (chars.contains(i))
					continue;
				else {
					chars.add(i);
					key += Character.toLowerCase((char) i); // add letters that are not present
				}
			}
		}

		// build ciphertext
		StringBuilder cipher = new StringBuilder("");
		for (int i = 0; i < str.length(); i++) {

			if (Character.isLetter(str.charAt(i))) { // str.charAt(i) has to be a letter first; others are ignored
				boolean flag = false;
				char ch = Character.toUpperCase(str.charAt(i));
				for (int j = 0; j < 25; j++) {
					if (Character.toUpperCase(key.charAt(j)) == ch) {
						int row = j / 5, column = j % 5;
						cipher.append(cipherChars.charAt(row));
						cipher.append(cipherChars.charAt(column));
						flag = true;
						break;
					}
				}
				if (!flag) // letter was not found in key
					cipher.append("\u00a5\u00a5");
			} else
				cipher.append(str.charAt(i)); // optional (you can leave out spaces/numbers/etc. or append them as is)
		}
		System.out.println(cipher);
	}

	/**
	 * Simple Substitution Cipher - keys usually consist of 26 letters. Each
	 * character in the Plaintext is replaced with the corresponding letter in the
	 * cipher alphabet. Very little security & super easy to break
	 *
	 * Ex: plain alphabet : abcdefghijklmnopqrstuvwxyz <br>
	 * cipher alphabet: phqgiumeaylnofdxjkrcvstzwb
	 *
	 * plaintext : defend the east wall of the castle <br>
	 * ciphertext: giuifg cei iprc tpnn du cei qprcni
	 */
	static public void simpleSubstitution(String str, String key) {
		StringBuilder cipher = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isLetter(str.charAt(i))) // only letters are encrypted
			{
				char ch = Character.toUpperCase(str.charAt(i));
				int index = ch;
				cipher.append(key.charAt(index - 65));
			} else
				cipher.append(str.charAt(i));
		}
		System.out.println(cipher);
	}

	/**
	 * Columnar Transposition Cipher - a transposition cipher that follows a simple
	 * rule for mixing up the characters in the Plaintext to form the Ciphertext.
	 *
	 * Ex: Keyword: GERMAN (row length that is used is the same as the length of the
	 * keyword), Padding character: x<br>
	 * Plaintext: defend the east wall of the castle <br>
	 *
	 * G E R M A N <br>
	 * ----------- <br>
	 * d e f e n d <br>
	 * t h e e a s <br>
	 * t w a l l o <br>
	 * f t h e c a <br>
	 * s t l e x x <br>
	 *
	 * This becomes:
	 *
	 * A E G M N R <br>
	 * -----------<br>
	 * n e d e d f<br>
	 * a h t e s e<br>
	 * l w t l o a<br>
	 * c t f e a h<br>
	 * x t s e x l<br>
	 *
	 *
	 * Ciphertext: nalcxehwttdttfseeleedsoaxfeahl
	 */
	static public void columnarTransposition(String str, String key, String paddingChar) {
		String modifiedStr = new String(); // remove spacing/line break chars
		for (int i = 0; i < str.length(); i++)
			if (Character.isLetter(str.charAt(i)))
				modifiedStr += str.charAt(i);
		// adding padding characters to str first

		if (modifiedStr.length() % key.length() != 0) {
			int x = modifiedStr.length() / key.length();
			int numOfPaddingCharsToAdd = (x + 1) * key.length() - modifiedStr.length();
			for (int i = 0; i < numOfPaddingCharsToAdd; i++)
				modifiedStr += paddingChar;
		}
		System.out.println(modifiedStr);
		StringBuilder cipher = new StringBuilder();
		//String[][] matrix = new String[key.length()][modifiedStr.length() / key.length()];
		TreeMap<String, String> matrix = new TreeMap<String, String>();

		//add keys into TreeMap
		for(int i=0;i<key.length();i++) {

		}

		for (int i = 0; i < modifiedStr.length(); i++) {

		}
	}

	static public void autokey(String str) {

	}

	static public void beaufort(String str) {

	}

	static public void porta(String str) {

	}

	static public void runningKey(String str) {

	}

	static public void vigenereAndGronsfeld(String str) {

	}

	static public void homophonicSubstitution(String str) {

	}

	static public void fourSquare(String str) {

	}

	static public void hill(String str) {

	}

	static public void playfair(String str) {

	}

	static public void adfgvx(String str) {

	}

	static public void adfgx(String str) {

	}

	static public void bifid(String str) {

	}

	static public void straddleCheckerboard(String str) {

	}

	static public void trifid(String str) {

	}

	static public void base64(String str) {

	}

	static public void fractionatedMorse(String str) {

	}

}
