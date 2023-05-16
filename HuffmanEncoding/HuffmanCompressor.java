import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HuffmanCompressor {
    public void compress(String fileName){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            int[] counts = new int[256];
            for(int j = br.read(); j != -1; j = br.read()) {
                counts[j]++;
            }
            HuffmanTree a = new HuffmanTree(counts);
            a.write(fileName + ".code");
            a.encode(new BitOutputStream(fileName + ".short"), fileName);
        } catch (Exception e) {
        }
    }

    public void expand(String codeFile, String fileName){
        HuffmanTree a = new HuffmanTree(codeFile);
        a.decode(new BitInputStream(codeFile), fileName);
    }
}
