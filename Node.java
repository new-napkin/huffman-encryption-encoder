class Node implements Comparable<Node>{
	public char c;
	public int freq;
	public Node left; //0
	public Node right; //1

	public Node(){
		
	}

	public Node(char cc, int f){
		c = cc;
		freq =f;
	}

	public Node(Node l, Node r){
		left = l;
		right = r;
		c='^';
	}

	public int compareTo(Node other){
		if(this.freq == other.freq) return 0;
		else if(this.freq < other.freq) return-1;
		else return 1;
	}
}
