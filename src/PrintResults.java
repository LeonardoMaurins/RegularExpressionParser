import java.util.ArrayList;
import java.util.StringTokenizer;

import org.barfuin.texttree.api.DefaultNode;
import org.barfuin.texttree.api.TextTree;
import org.barfuin.texttree.api.TreeOptions;
import org.barfuin.texttree.api.style.TreeStyles;

public class PrintResults {

	ArrayList<String> posTags, words;

	public void print(String sentence, int sentenceCount) {

		// Tagged output from the MaxentTagger is tokenized
		StringTokenizer taggedTokens = new StringTokenizer(sentence);

		// Arraylists of the posTags and words are split for parsing
		posTags = new ArrayList<String>();
		words = new ArrayList<String>();

		// Separates the tagged regular expresisons from the stanford library
		// Splits posTags and words into different arraylists
		while (taggedTokens.hasMoreTokens()) {
			String pTag = taggedTokens.nextToken();
			String sTag = pTag;
			pTag = pTag.replaceAll("[a-z]+_", "");
			sTag = sTag.replaceAll("_[A-Z]+", "");
			posTags.add(pTag);
			words.add(sTag);
		}
		displayBracketStructure(sentenceCount);
		displayTreeStructure(sentenceCount, posTags, words);
	}

	// Parses the words and nouns, adjectives, verbs etc then sorts them into a
	// bracketed phrasal structure
	public void displayBracketStructure(int sentenceCount) {
		String output = "[ S" + sentenceCount + " [ S [ NP [ " + posTags.get(0) + " [ " + words.get(0) + " ] "
				+ posTags.get(1) + " [ " + words.get(1) + " ] ] VP ";
		for (int i = 2; i < posTags.size(); i++) {
			output += "[ " + posTags.get(i) + " [ " + words.get(i) + " ] ] ] ";
		}

		System.out.println(output);
	}

	// Creates a tree architecture using passed POS tags and words from parsing
	// We build the tree using a java library "text-tree"
	public void displayTreeStructure(int sentenceCount, ArrayList<String> posTags, ArrayList<String> words) {

		DefaultNode tree = new DefaultNode("S" + sentenceCount);
		DefaultNode node1 = new DefaultNode("S");
		DefaultNode node2 = new DefaultNode("NP");
		DefaultNode node3 = new DefaultNode("VP");
		DefaultNode node4 = new DefaultNode(posTags.get(0));
		DefaultNode node5 = new DefaultNode(posTags.get(1));
		DefaultNode node6 = new DefaultNode(posTags.get(2));
		DefaultNode node7 = new DefaultNode(posTags.get(3));
		DefaultNode node8 = new DefaultNode(posTags.get(4));
		DefaultNode node9 = new DefaultNode(posTags.get(5));

		tree.addChild(node1);
		node1.addChild(node2);
		node1.addChild(node3);
		node2.addChild(node4);
		node2.addChild(node5);
		node2.addChild(node6);
		node3.addChild(node7);
		node3.addChild(node8);
		node3.addChild(node9);

		node4.addChild(new DefaultNode(words.get(0)));
		node5.addChild(new DefaultNode(words.get(1)));
		node6.addChild(new DefaultNode(words.get(2)));
		node7.addChild(new DefaultNode(words.get(3)));
		node8.addChild(new DefaultNode(words.get(4)));
		node9.addChild(new DefaultNode(words.get(5)));

		TreeOptions options = new TreeOptions();
		options.setStyle(TreeStyles.UNICODE_ROUNDED);
		String rendered = TextTree.newInstance(options).render(tree);

		System.out.println(rendered);
	}
}
