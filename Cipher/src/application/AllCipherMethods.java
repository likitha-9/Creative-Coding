package application;

/**
 *
 * @author likitha-9
 *
 *         Raw implementations of all of the 31 cipher methods.
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
	 * a, b - MUST be relatively prime to m (# of alphabets - 26) p - number
	 * representing letter
	 *
	 * Ciphertext letter c = ap+b (mod m)
	 */
	static public void affine(String str, int a, int b) {
		if (a < 1 || a > 26 || b < 1 || b > 26)
			;// CipherController.writeMessage("This condition is violated: 1<= a,b <=
		// 26.\nTry again!");
	}

	static public void railFence(String str) {

	}

	static public void baconian(String str) {

	}

	static public void polybiusSquare(String str) {

	}

	static public void simpleSubstitution(String str) {

	}

	static public void codesAndNomenclators(String str) {

	}

	static public void columnarTransposition(String str) {

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
