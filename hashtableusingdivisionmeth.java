import java.util.LinkedList;

class HashTable {
    // Each bucket will store a LinkedList of KeyValue pairs
    private LinkedList<KeyValue>[] table;
    private int size = 10; // fixed hash table size

    // Constructor
    public HashTable() {
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Inner class for storing key-value pairs
    class KeyValue {
        int key;
        String value;

        KeyValue(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Hash Function using Division Method
    private int hash(int key) {
        return key % size;
    }

    // Insert key-value pair
    public void insert(int key, String value) {
        int index = hash(key);
        for (KeyValue kv : table[index]) {
            if (kv.key == key) {
                kv.value = value; // update value if key exists
                return;
            }
        }
        table[index].add(new KeyValue(key, value)); // add new pair
    }

    // Search for a value using key
    public String search(int key) {
        int index = hash(key);
        for (KeyValue kv : table[index]) {
            if (kv.key == key) {
                return kv.value;
            }
        }
        return null; // key not found
    }

    // Delete a key-value pair
    public void delete(int key) {
        int index = hash(key);
        KeyValue toRemove = null;
        for (KeyValue kv : table[index]) {
            if (kv.key == key) {
                toRemove = kv;
                break;
            }
        }
        if (toRemove != null) {
            table[index].remove(toRemove);
            System.out.println("Key " + key + " deleted.");
        } else {
            System.out.println("Key " + key + " not found.");
        }
    }

    // Display the entire hash table
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            for (KeyValue kv : table[i]) {
                System.out.print("[" + kv.key + " : " + kv.value + "] -> ");
            }
            System.out.println("null");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HashTable ht = new HashTable();

        ht.insert(10, "Apple");
        ht.insert(20, "Banana");
        ht.insert(25, "Cherry");
        ht.insert(30, "Mango");
        ht.insert(42, "Peach");

        System.out.println("Hash Table after insertions:");
        ht.display();

        System.out.println("\nSearch key 25: " + ht.search(25));
        System.out.println("Search key 15: " + ht.search(15));

        ht.delete(20);
        ht.delete(100);

        System.out.println("\nHash Table after deletions:");
        ht.display();
    }
}
