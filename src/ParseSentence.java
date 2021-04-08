import java.util.StringTokenizer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class ParseSentence {

	public void parseSentence(String sentence, int sentenceCount) {

		// Made lower case to prevent interference with lexicon
		sentence = sentence.toLowerCase();

		// Tag the users input using the CoreNLP library
		MaxentTagger tagger = new MaxentTagger("src/core-nlp.tagger");
		String tagged = tagger.tagString(sentence);

		// Calls the print results class in order to print the
		// bracketed phrase structure and the tree architecture
		PrintResults printResults = new PrintResults();
		printResults.print(tagged, sentenceCount);

		// Calls the verify sentence class to check if the passed sentence
		// is a valid regular expression using tokens
		StringTokenizer tokens = new StringTokenizer(sentence);
		VerifySentence validateSentence = new VerifySentence();
		validateSentence.validate(tokens);
	}
}
