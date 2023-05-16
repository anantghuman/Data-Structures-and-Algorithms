import java.io.*;
import java.util.*;

public class HuffmanTree {
    private Node root;
    private static HashMap<Integer, String> binary = new HashMap<>();;

    public HuffmanTree(int[] counts) {
        PriorityQueue<Node> tree = new PriorityQueue<Node>();
        for (int i = 0; i < counts.length; i++)
            if (counts[i] > 0)
                tree.offer(new Node(counts[i], (char) i));
        tree.offer(new Node(1, (char) 256));
        while(tree.size() != 1) {
            Node i = tree.poll();
            Node j = tree.poll();
            Node temp = new Node(i.val + j.val, null, i, j);
            tree.offer(temp);
        }
        buildMap(tree.peek(),"");
        root = tree.poll();
    }

    private void buildMap(Node temp, String c){
        if(temp.left == null && temp.right == null){
            binary.put((int)temp.letter, c);
            return;
        }
        else {
            if (temp.left != null)
                buildMap(temp.left, c + "0");
            if (temp.right != null)
                buildMap(temp.right, c + "1");
        }
    }

    public Node getRoot() {
        return root;
    }

    void write(String filename) {
        try {
            PrintWriter pw = new PrintWriter(new File(filename));
            for(Map.Entry<Integer, String> set: binary.entrySet()) {
                pw.println(set.getKey());
                pw.println(set.getValue());
            }
            pw.close();
        } catch (Exception ex) {
        }
    }

    void encode(BitOutputStream out, String fileName) {
        BufferedReader br = null;
        StringBuffer  bf  = new StringBuffer() ;
       
        try {
        br = new BufferedReader(new FileReader(fileName));
        
        
        for (int c = br.read(); c != -1; c = br.read()){  	
            String p = binary.get(c);
            bf.append(p);
           
              
           for(char bit : p.toCharArray()) {	   
                if(bit == '0') 
                    out.writeBit(0);
                else
                    out.writeBit(1);
            }
        }
        
    
        out.close();
        
        FileWriter fw = new FileWriter("encoded.txt");
        fw.write(bf.toString());
        fw.close();
     } catch (Exception e) {
    
    }
    }

    public HuffmanTree(String codeFile) {
        try{
            Scanner console = new Scanner(new File(codeFile));
            root = new Node(0, null);
            buildTree(root, console.nextInt(),"", console);
        } catch (Exception e) {
        }
    }
    private void buildTree(Node curr, int charV, String str, Scanner console){
        if(str.equals(binary.get(console.nextLine()))) {
            curr.letter = (char) charV;
            binary.put(charV, str); //builds map
        }
        else {
            curr.left = new Node(0, null);
            curr.right = new Node(0, null);
            buildTree(curr.left, charV, str + "0", console);
            buildTree(curr.right, console.nextInt(), str + "1", console);
        }
    }

 
    public  void decode(BitInputStream in, String outFile) {
        Node curr = root;
        boolean firstByteRead = false ;
        try (PrintWriter pw = new PrintWriter(outFile)) {
            while (true) {
            	
            	
            	
            	
            	
                if (curr.letter != null) {
                    if (curr.letter == 256) { 
                        break;
                    } else {
                        pw.print((char) curr.letter); 
                        curr = root;
                    }
                }
              
                
                int bit = in.readBit();
                System.out.print(bit + ":");
                if (bit == -1) { // end of input file
                    break;
                }
                curr = (bit == 0) ? curr.left : curr.right;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        int[] a = new int[256];
        a[32] = 2;
        a[97] = 1;
        a[104] = 3;
        a[105] = 1;
        a[111] = 1;
        a[112] = 4;
        a[121] = 1;
       
        
      
        HuffmanTree j = new HuffmanTree(a);
        TreePrinter i = new TreePrinter();
        i.printTree(j.getRoot());
        j.write("ascii.txt");
     
        BitOutputStream out = new BitOutputStream("encoded.short");
        j.encode(out, "input.txt");
        out.close();
        
        
        BitInputStream l = new BitInputStream("encoded.short");
        j.decode(l, "decoded.new");
        l.close();
    }
}