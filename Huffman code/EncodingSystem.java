import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EncodingSystem {
    private String[] texts;
    private String[] encodedTexts;
    private Huffman[] huffmans;

    public EncodingSystem(String[] texts) {
        this.texts = texts;
        this.encodedTexts = new String[texts.length];
        this.huffmans = new Huffman[texts.length];

        encodeAll();
    }

    private void encodeAll() {
        for (int i = 0; i < texts.length; i++) {
            huffmans[i] = new Huffman();
            encodedTexts[i] = huffmans[i].encode(texts[i]);
        }
    }

    public String highestCode() {
        if (encodedTexts.length == 0) {
            return "";
        }

        String highest = encodedTexts[0];

        for (String code : encodedTexts) {
            if (code.length() > highest.length()) {
                highest = code;
            }
        }

        return highest;
    }

    public String[] shuffleCodes() {
        List<String> list = new ArrayList<>(Arrays.asList(encodedTexts));
        Collections.shuffle(list);
        return list.toArray(new String[0]);
    }

    public void printAllCodes() {
        for (String code : encodedTexts) {
            System.out.println(code);
        }
    }

    public void stats() {
        for (int i = 0; i < texts.length; i++) {
            System.out.println("Stats for \"" + texts[i] + "\"");
            huffmans[i].printStats();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] texts = {
                "marcus fenix is a gear"
        };

        EncodingSystem system = new EncodingSystem(texts);

        System.out.println("Highest Code:");
        System.out.println(system.highestCode());

        System.out.println("\nShuffled Codes:");
        System.out.println(Arrays.toString(system.shuffleCodes()));

        System.out.println("\nStats:");
        system.stats();
    }
}
