import java.util.*;
import java.io.*;

class Main {
	public static HashMap<Character, String> keys = new HashMap<Character, String>();

	public static void printCodes(Node root, ArrayList<String> codes) { // LIFO
		if (root == null) {
			if (codes.size() > 1) codes.remove(codes.size() - 1);
			return;
		}
		if (root.left == null && root.right == null) {
			String code = "";
			for (String s : codes) code = code.concat(s);
			System.out.println(root.c + ": " + code);
			keys.put(root.c, code);
		}

		codes.add("0");
		printCodes(root.left, codes);

		codes.add("1");
		printCodes(root.right, codes);
		if (codes.size() > 0) codes.remove(codes.size() - 1);
	}

	public static void main(String[] args) throws Exception {
		// ============= GETTING INPUT MSG =============
		System.out.println("Your message to encode (replace spaces with _):");
		Scanner in = new Scanner(System.in);
		String message = in.next();

		// ============= FINDING LETTER FREQUENCY =============
		Map<Character, Integer> frequency = new HashMap<Character, Integer>();

		for (char c : message.toCharArray()) {
			if (frequency.containsKey(c)) {
				frequency.put(c, frequency.get(c) + 1);
			} else {
				frequency.put(c, 1);
			}
		}

		System.out.println("\n\nLetter frequency:");

		for (Character c : frequency.keySet()) {
			System.out.println(c + ": " + frequency.get(c));
		}

		// ============= FILLING PRIORITY QUEUE =============
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		for (Character c : frequency.keySet()) {
			queue.add(new Node(c, frequency.get(c)));
		}

		// ============= CONSTRUCTING HUFFMAN TREE =============
		while (queue.size() > 1) {
			Node one = queue.poll();
			Node two = queue.poll();
			Node head = new Node(one, two);
			head.freq = one.freq + two.freq;
			queue.add(head);
		}

		// ============= GETTING THE CODE =============
		System.out.println("\n\nEncryption key:");
		printCodes(queue.peek(), new ArrayList<String>());

		// ============= PRINTING ENCRYPTED MESSAGE =============
		System.out.println("\n\nEncoded message:");
		for(char c : message.toCharArray()){
			System.out.print(keys.get(c));
		}
	}
}
