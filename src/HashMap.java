public class HashMap {
    private final int DEFAULT_TABLE_SIZE = 1999;
    private final int RADIX = 256;
    private final double LOAD_FACTOR = 0.25;
    private int tableSize;
    private int numElements;
    private String[] keys;
    private String[] values;

    public HashMap() {
        numElements = 0;
        tableSize = DEFAULT_TABLE_SIZE;
        keys = new String[DEFAULT_TABLE_SIZE];
        values = new String[DEFAULT_TABLE_SIZE];
    }

    public int hash(String key) {
        int hash = 0;
        for (int i =0; i <key.length(); i++) {
            hash = (hash * RADIX + key.charAt(i)) % tableSize;
        }
        return hash;
    }

    public void add(String key, String value) {
        numElements++;
        // Resize table if the number of elements is larger than LOAD_FACTOR % of the table size
        if (numElements > tableSize * LOAD_FACTOR)
            resize();
        int hash = hash(key);

        // Deal with collisions, go to the right until an empty space is found
        while (keys[hash] != null) {
            // Modulo accounts for overflow
            hash = (hash + 1) % tableSize;
        }
        keys[hash] = key;
        values[hash] = value;
    }

    public String get(String key) {
        int hash = hash(key);
        // If we come across a blank index when we are searching for a key, the key doesn't exist
        while (keys[hash] != null && !keys[hash].equals(key)) {
            hash = (hash + 1) % tableSize;
        }
        return values[hash];
    }

    public void resize() {
        String[] keysCopy = keys;
        String[] valuesCopy = values;
        keys = new String[tableSize*2];
        values = new String[tableSize*2];

        tableSize *= 2;
        numElements = 0;
        // Recompute new hashes for existing values
        for (int i =0; i <tableSize/2; i++) {
            if (keysCopy[i] != null)
                add(keysCopy[i], valuesCopy[i]);
        }
    }
}
