import java.util.*;
import tester.Tester;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */
public class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random(1); // Generated with seed

  // Create a new instance of the encoder/decoder with a new permutation code
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> tempAlpha = new ArrayList<Character>(26);
    tempAlpha.addAll(this.alphabet);
    int nextIndex = 0;
    int codePlace = 0;
    while (!(tempAlpha.isEmpty())) {
      nextIndex = rand.nextInt(tempAlpha.size());
      this.code.add(codePlace, tempAlpha.get(nextIndex));
      tempAlpha.remove(nextIndex);
    }
    return this.code;
  }

  // produce an encoded String from the given String
  String encode(String source) {
    String encoded = "";
    int sourceLength = source.length();
    int alphaPlace = 0;
    for (int i = 0; i < sourceLength; i++) {
      // Returns the position of this char in the alphabet based on ASCII Values
      alphaPlace = source.charAt(i) - 97;
      encoded += this.code.get(alphaPlace);
    }
    return encoded;
  }

  // produce a decoded String from the given String
  String decode(String code) {
    String decoded = "";
    int codeLength = code.length();
    int codePlace = 0;
    for (int i = 0; i < codeLength; i++) {
      for (int j = 0; j < this.code.size(); j++) {
        if (code.charAt(i) == this.code.get(j)) {
          codePlace = j;
        }
      }
      decoded += this.alphabet.get(codePlace);
    }
    return decoded;
  }
}

// Testing class
class ExamplesPermutationCode {
  ArrayList<Character> code1;
  ArrayList<Character> code2;
  PermutationCode ceaserCipher;
  PermutationCode oppositeAlpha;
  PermutationCode noCode;

  void initData() {
    code1 = new ArrayList<Character>(Arrays.asList('b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'a'));
    ceaserCipher = new PermutationCode(code1);

    code2 = new ArrayList<Character>(Arrays.asList('z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q',
        'p', 'q', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'));
    oppositeAlpha = new PermutationCode(code2);
    noCode = new PermutationCode();
  }

  void testEncode(Tester t) {
    initData();
    t.checkExpect(ceaserCipher.encode("apple"), "bqqmf");
    t.checkExpect(ceaserCipher.encode("fundamentalsofcs"), "gvoebnfoubmtpgdt");
    t.checkExpect(oppositeAlpha.encode("hello"), "svqql");
    t.checkExpect(noCode.encode("hello"), "zmkkx");
  }

  void testDecode(Tester t) {
    initData();
    t.checkExpect(ceaserCipher.decode("bqqmf"), "apple");
    t.checkExpect(ceaserCipher.decode("gvoebnfoubmtpgdt"), "fundamentalsofcs");
    t.checkExpect(oppositeAlpha.decode("svqql"), "hello");
    t.checkExpect(noCode.decode("zmkkx"), "hello");
  }

  void testInitEncoder(Tester t) {
    ArrayList<Character> encodeResult = new ArrayList<Character>(
        Arrays.asList('p', 'b', 'w', 'd', 'm', 'f', 's', 'z', 'j', 'e', 'c', 'k', 'i', 'g', 'x',
            'a', 'v', 'l', 'u', 't', 'q', 'y', 'o', 'h', 'n', 'r'));

    t.checkExpect(new PermutationCode().code, encodeResult);
  }
}