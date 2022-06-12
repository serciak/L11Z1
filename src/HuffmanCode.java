import java.util.*;

public class HuffmanCode {

    class HuffmanNode {

        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;
    }

    class MyComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y) {
            return x.data - y.data;
        }
    }

    public Map<Character, String> printCode(HuffmanNode root, String s, Map<Character, String> code) {
        if (root.left == null && root.right == null) {
            code.put(root.c, s);
            return code;
        }

        printCode(root.left, s + "0", code);
        printCode(root.right, s + "1", code);
        return code;
    }

    private Map<Character, Integer> frequency(String str) {
        Map<Character, Integer> freq = new HashMap<>();
        char[] chars = str.toCharArray();

        for(int i = 0; i < str.length(); i++) {

            if(!freq.containsKey(chars[i])) {
                freq.put(chars[i], 1);

                for(int j = i + 1; j < str.length(); j++) {
                    if(chars[i] == chars[j])
                        freq.replace(chars[i], freq.get(chars[i])+1);

                }
            }
        }
        return freq;
    }

    private Map<Character, String> code;
    private String str;
    private String encoded;

    private void encode(String str) {
        Map<Character, Integer> freq = frequency(str);
        Object[] chars = freq.keySet().toArray();
        int n = freq.size();
        this.str = str;

        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());

        for (int i = 0; i < n; i++) {

            HuffmanNode node = new HuffmanNode();

            node.c = (char) chars[i];
            node.data = freq.get((char) chars[i]);

            node.left = null;
            node.right = null;

            q.add(node);
        }

        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }

        code = new HashMap<>();
        printCode(root, "", code);
    }

    public void printCode(String str) {
        encode(str);
        for(Map.Entry<Character, String> entry : code.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public void printEncodedString(String str) {
        encode(str);
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        System.out.println();

        for(int i = 0; i < chars.length; i++)
            sb.append(code.get(chars[i]));
        encoded = sb.toString();
        System.out.println(encoded);
        System.out.println();
    }

    public void printDecodedString() {
        char[] chars = encoded.toCharArray();
        StringBuilder tmp = new StringBuilder();

        for(int i = 0; i < chars.length; i++) {
            tmp.append(chars[i]);

            if(code.containsValue(tmp.toString())) {
                System.out.print(getDecodedChar(tmp.toString()));
                tmp = new StringBuilder();
            }
        }
    }

    private char getDecodedChar(String enc) {
        for(Map.Entry<Character, String> entry : code.entrySet()) {
            if(entry.getValue().equals(enc))
                return entry.getKey();
        }
        return (char) 0;
    }
}
