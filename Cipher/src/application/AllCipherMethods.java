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
			}
			// If str.charAt(i)==alphabet, modify it.*/
			else {
				int ascii = str.charAt(i); // convert current char to ascii value

				char coded_char;
				// in case of lowercase
				if (Character.isLowerCase(str.charAt(i))) {
					int numeric = 123 + (96 - ascii);
					coded_char = (char) numeric;
				}
				// in case of uppercase
				else {
					int numeric = 91 - (64 - ascii);
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

		}
	}

	static public void caesar(String str) {

	}

	static public void affine(String str) {

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
