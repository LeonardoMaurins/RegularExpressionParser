import java.util.ArrayList;
import java.util.List;

public class Parser {

	public static void main(String[] args) {

		// Creates a list of strings to be checked for acceptable regular expressions
		List<String> sentences = new ArrayList<String>();
		sentences.add("The men like the green dog"); // Accepted
		sentences.add("The woman pet the blue dog"); // Rejected
		sentences.add("A woman bites the green dog"); // Accepted
		sentences.add("The man liked the invisible dog"); // Rejected

		ParseSentence parseSentence = new ParseSentence();
		String sentence;
		int sentenceCount = 1;
		
		// Loops through the sentences to be parsed
		for(int i = 0; i < sentences.size(); i++) {
			sentence = sentences.get(i);
			parseSentence.parseSentence(sentence, sentenceCount);
			sentenceCount++;
		}
	}
}
