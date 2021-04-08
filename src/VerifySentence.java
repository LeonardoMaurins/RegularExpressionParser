import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class VerifySentence {

	public void validate(StringTokenizer tokens) {

		Scanner fileScanner = null;
		ArrayList<String> categoryList = new ArrayList<String>();

		// While traversing the tokens from sentences compare them to lexicon text file
		while (tokens.hasMoreTokens()) {

			fileScanner = new Scanner(getClass().getResourceAsStream("Lexicon.txt"));
			String thisToken = tokens.nextToken();

			// While traversing the lines in the text file
			// Tokenizes the word for each line
			while (fileScanner.hasNextLine()) {
				String scan = fileScanner.nextLine().toString();
				StringTokenizer lexiconTokens = new StringTokenizer(scan);

				// While traversing the created tokens, adds them to
				// the POS categories list for verification
				while (lexiconTokens.hasMoreTokens()) {
					if (thisToken.equals(lexiconTokens.nextToken())) {
						String thisLexToken = lexiconTokens.nextToken();
						categoryList.add(thisLexToken);
					}
				}
			}
		}
		fileScanner.close();

		// Add each POS to a concatenated string
		String posTags = "";
		for (int i = 0; i < categoryList.size(); i++) {
			posTags += categoryList.get(i) + " ";
		}

		// Checks if the order of POS categories is valid
		// for the sentence and either accepts or rejects it
		if (posTags.equals("DTS NN VBZ DT JJ NN ") ||
			posTags.equals("DT NN VBZ DT JJ NN ") || 
			posTags.equals("DT NNS IN DT JJ NN ")) {
			System.out.println("Accepted as a Regular Expression!");
		} else {
			System.out.println("Rejected as a Regular Expression!");
		}
	}
}
