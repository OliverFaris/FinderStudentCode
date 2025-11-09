import java.io.BufferedReader;
import java.io.IOException;
/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Oliver Faris
 **/

public class Finder {

    private static final String INVALID = "INVALID KEY";
    private HashMap hashMap;

    public Finder() {
        hashMap = new HashMap();
    }

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        String line = br.readLine();
        while (line != null) {
            // Add key & value to hashmap
            String[] row = line.split(",");
            hashMap.add(row[keyCol], row[valCol]);
            line = br.readLine();
        }

        br.close();
    }

    public String query(String key){
        String value = hashMap.get(key);
        if (value == null)
            return INVALID;
        return value;
    }
}