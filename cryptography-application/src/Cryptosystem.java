import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Moath Mohamed on 13-Nov-2018 At 19:32
 */

public class Cryptosystem {

    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private Key[] CreateKeyObjects(String keyword) {
        Key[] keys = new Key[keyword.length()];

        for (int i = 0; i < keyword.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++)
                if (String.valueOf(keyword.charAt(i)).equalsIgnoreCase(String.valueOf(alphabet.charAt(j))))
                    keys[i] = new Key(j,i);
        }

        return keys;
    }

    public String encryption(String plaintext, String keyword) throws Exception {

        Key[] keys = this.CreateKeyObjects(keyword);

        Arrays.sort(keys);

        plaintext = plaintext.replace(" ", "_");

        int row = (int) Math.ceil(plaintext.length() / (keyword.length() * 1.0));
        int column = keyword.length();

        char[][] array = new char[row][column];

        int counter = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (counter == plaintext.length()) {
                    array[i][j] = '%';
                }
                else {
                    array[i][j] = plaintext.charAt(counter++);
                }
            }
        }

        String ciphertext = "";

        counter = 0;

        while (counter < keys.length){

            int key = keys[counter].position;

            for (int i = 0; i < array.length; i++)
                ciphertext += array[i][key];

            counter ++;
        }

        return ciphertext;
    }

    public String decryption(String ciphrtext, String keyword) throws Exception {

        Key[] keys = this.CreateKeyObjects(keyword);

        Arrays.sort(keys);

        int pieces = ciphrtext.length() / keyword.length();

        String[] cipher = splitToNChar(ciphrtext, pieces);

        int row = ciphrtext.length() / keyword.length();
        int column = keyword.length();

        char[][] array = new char[row][column];

        int key;

        for (int i = 0; i < cipher.length; i++) {
            key = keys[i].position;
            for (int j = 0; j < cipher[i].length(); j++)
                array[j][key] = cipher[i].charAt(j);
        }

        String plaintext = "";

        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++)
                plaintext += array[i][j];
        }

        plaintext = plaintext.replace("%", "");
        plaintext = plaintext.replace("_", " ");

        return plaintext;
    }

    /**
     * Split text into n number of characters.
     *
     * @param text the text to be split.
     * @param size the split size.
     * @return an array of the split text.
     */
    private static String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }
}