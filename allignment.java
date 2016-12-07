import java.io.*;
import java.math.*;
class allign{
	public static String seq1 = "";
	public static String seq2 = "";
	public static void main(String[] args){
		boolean toggle = false;//String 1/String2 toggle
		int[][] matrix = LoadMatrix();
		//Einlesen der Datei und zerlegen in die Sequenzen
		File file = new File("/afs/informatik.uni-goettingen.de/user/o/oskar.wittkamp/BioAlgo/Sequences.txt");
		if (!file.canRead() || !file.isFile())
			System.exit(0);
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader (file));
			String zeile = null;
			while ((zeile = input.readLine()) != null) {
				if (zeile.charAt (0) != '>')
					if(toggle)
						seq1 += zeile;
					else
						seq2 += zeile;
				else
					toggle = !toggle;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {	
				if (input != null)
				input.close();
			} catch (IOException e) {
			}
		}
		System.out.println(seq1);
		System.out.println(seq2);
		globalallign(1);
	}
	
	public static int[][] LoadMatrix(){ //Verarbeitet díe Datei BLOSUM62 zu einem passenden int[][]
		int[][] array = new int[20][20];
		int i = 0;
		int j = 0;
		int wert = 0;
		String s = "GPAVLIMCFYWHKRQNEDST";	//"Indexstring" für Matrix G->0; P->1 etc
		File file = new File("/afs/informatik.uni-goettingen.de/user/o/oskar.wittkamp/BioAlgo/BLOSUM62.txt");
		if (!file.canRead() || !file.isFile())
			System.exit(0);
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader (file));
			String zeile = null;
			while ((zeile = input.readLine()) != null) {//Zerlegt die Zeile in char, char, int
				i = s.indexOf(zeile.charAt(1));
				j = s.indexOf(zeile.charAt(5));
				array[i][j] = Integer.parseInt(zeile.substring(7).replaceAll("\\s+", ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {	
				if (input != null)
				input.close();
			} catch (IOException e) {
			}
		}
		return array;
	}
	
	public static boolean CheckChars(String s){//checkt nen String auf die 20 basen-chars
		String t = "GPAVLIMCFYWHKRQNEDST";
		for (int i = 0; i < s.length(); i++)
			if (s.indexOf(s.charAt(i)) == -1)
				return false;
		return true;
	}
	
	public static Node Max(int g, int i, int j){
		String path;
		int maxvalue;
		if (i == 0 && j == 0){
			path = "";
			maxvalue = 0;
			Node n = new Node(maxvalue, path, i, j);
		}
		if (i == 0){
			Node n3 = Max(g, i, j-1);
			maxvalue = n3.getMax()-g;
			path = 2 + n3.getpath();
			Node n = new Node(maxvalue, path, i, j);
			return n;
		} 
		
		if(j == 0){
			Node n1 = Max(g, i-1, j);
			maxvalue = n1.getMax()-g;
			path = 0 + n1.getpath();
			Node n = new Node(maxvalue, path, i, j);
			return n;
		}
		Node n1 = Max(g, i-1, j);
		Node n2 = Max(g, i-1, j-1);//////////////////////////s fehlt
		Node n3 = Max(g, i, j-1);
		maxvalue = Maximum(n1.getMax()-g, n2.getMax(), n3.getMax()-g);
		if (maxvalue == n1.getMax()-g)
			path = 0 + n1.getpath();
		if (maxvalue == n2.getMax())
			path = 1 + n2.getpath();
		else 
			path = 2 + n3.getpath();
		Node n = new Node(maxvalue, path, 0, 0);
		return n;
	}
	
	public static int Maximum(int i, int j, int k){//Gibt den größten der 3 ints zurück
		if (i > j)
			if(i > k)
				return i;
		if (j > k)
				return j;
		return k;
			
	}	

	public static void globalallign(int g){
		//Node n = new Node(0, "", seq1.length(),seq2.length());
		Node m = Max(2, 2, 2);//
		System.out.println(m.getpath());
		return;
	}
}

class Node{
	public int max;
	public int i;
	public int j;
	public String path;
	
	public Node(){
		this.max = 0;
		this.path = "";
		this.i= 0;
		this.j = 0;
	}
	public Node(int max, String path, int i, int j){
		this.max = max;
		this.path = path;
		this.i = i;
		this.j = j;
		System.out.println(max + path + i + j);
	}
	public String getpath(){
		return this.path;
	}
	public int getMax(){
		return this.max;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
