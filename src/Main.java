

public class Main {

    public static void main(String[] args) {

        HuffmanCode huffman = new HuffmanCode();
        String str = "czy tata czyta cytaty tacyta";

        huffman.printCode(str);
        huffman.printEncodedString(str);
        huffman.printDecodedString();
    }
}
