import java.util.*;

class Bucket {
    int localDepth;
    int capacity;
    Map<Integer, String> data;

    public Bucket(int localDepth, int capacity) {
        this.localDepth = localDepth;
        this.capacity = capacity;
        this.data = new HashMap<>();
    }

    boolean isFull() {
        return data.size() >= capacity;
    }
}

class ExtendibleHashTable {
    private int globalDepth;
    private int bucketCapacity;
    private List<Bucket> directory;

    public ExtendibleHashTable(int bucketCapacity) {
        this.globalDepth = 1;
        this.bucketCapacity = bucketCapacity;
        this.directory = new ArrayList<>();

        // Initialize with 2 buckets
        directory.add(new Bucket(1, bucketCapacity));
        directory.add(new Bucket(1, bucketCapacity));
    }

    private int hash(int key) {
        return key & ((1 << globalDepth) - 1);
    }

    public void insert(int key, String value) {
        int index = hash(key);
        Bucket bucket = directory.get(index);

        if (bucket.data.containsKey(key)) {
            bucket.data.put(key, value);
            return;
        }

        if (!bucket.isFull()) {
            bucket.data.put(key, value);
        } else {
            splitBucket(index);
            insert(key, value); // reinsert after split
        }
    }

    private void splitBucket(int index) {
        Bucket oldBucket = directory.get(index);
        int localDepth = oldBucket.localDepth;
        if (localDepth == globalDepth) {
            doubleDirectory();
        }

        // Create new bucket
        Bucket newBucket = new Bucket(localDepth + 1, bucketCapacity);
        oldBucket.localDepth++;

        // Reassign directory pointers
        for (int i = 0; i < (1 << globalDepth); i++) {
            if (directory.get(i) == oldBucket) {
                if (((i >> localDepth) & 1) == 1)
                    directory.set(i, newBucket);
            }
        }

        // Rehash all keys
        Map<Integer, String> tempData = new HashMap<>(oldBucket.data);
        oldBucket.data.clear();

        for (Map.Entry<Integer, String> entry : tempData.entrySet()) {
            insert(entry.getKey(), entry.getValue());
        }
    }

    private void doubleDirectory() {
        int size = 1 << globalDepth;
        for (int i = 0; i < size; i++) {
            directory.add(directory.get(i)); // duplicate pointers
        }
        globalDepth++;
    }

    public String search(int key) {
        int index = hash(key);
        Bucket bucket = directory.get(index);
        return bucket.data.getOrDefault(key, null);
    }

    public void delete(int key) {
        int index = hash(key);
        Bucket bucket = directory.get(index);
        if (bucket.data.containsKey(key)) {
            bucket.data.remove(key);
            System.out.println("Key " + key + " deleted.");
        } else {
            System.out.println("Key not found.");
        }
    }

    public void display() {
        System.out.println("\n--- Hash Table State ---");
        System.out.println("Global Depth: " + globalDepth);
        for (int i = 0; i < directory.size(); i++) {
            Bucket b = directory.get(i);
            System.out.println("Dir[" + i + "] -> " + b.data + " (Local Depth: " + b.localDepth + ")");
        }
    }
}

public class ExtendibleHashingDemo {
    public static void main(String[] args) {
        ExtendibleHashTable ht = new ExtendibleHashTable(2); // capacity per bucket = 2

        ht.insert(1, "PizzaA");
        ht.insert(2, "PizzaB");
        ht.insert(3, "PizzaC");
        ht.insert(4, "PizzaD");
        ht.insert(5, "PizzaE");

        ht.display();

        System.out.println("\nSearch(3): " + ht.search(3));
        System.out.println("Search(10): " + ht.search(10));

        ht.delete(3);
        ht.display();
    }
}
