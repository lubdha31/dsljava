import java.util.Scanner;

public class LinearProbingHashTable {
    private int[] table;
    private int size;
    private static final int EMPTY = -1;
    private static final int DELETED = -2;

    // Constructor
    public LinearProbingHashTable(int size) {
        this.size = size;
        table = new int[size];
        for (int i = 0; i < size; i++) {
            table[i] = EMPTY; // initialize all slots as empty
        }
    }

    // Hash function using division method
    private int hash(int key) {
        return key % size;
    }

    // Insert key
    public void insert(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index] == EMPTY || table[index] == DELETED) {
                table[index] = key;
                System.out.println("Inserted key " + key + " at index " + index);
                return;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Hash table is full! Cannot insert key " + key);
    }

    // Search key
    public boolean search(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index] == EMPTY)
                return false; // key not found
            if (table[index] == key)
                return true;
            index = (index + 1) % size;
        } while (index != startIndex);

        return false;
    }

    // Delete key
    public void delete(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index] == EMPTY) {
                System.out.println("Key " + key + " not found!");
                return;
            }
            if (table[index] == key) {
                table[index] = DELETED;
                System.out.println("Key " + key + " deleted from index " + index);
                return;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Key " + key + " not found!");
    }

    // Display hash table
    public void display() {
        System.out.println("\n--- Hash Table ---");
        for (int i = 0; i < size; i++) {
            if (table[i] == EMPTY)
                System.out.println("Index " + i + " : [EMPTY]");
            else if (table[i] == DELETED)
                System.out.println("Index " + i + " : [DELETED]");
            else
                System.out.println("Index " + i + " : " + table[i]);
        }
    }

    // Main method for user interaction
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of hash table: ");
        int size = sc.nextInt();

        LinearProbingHashTable hashTable = new LinearProbingHashTable(size);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    int keyInsert = sc.nextInt();
                    hashTable.insert(keyInsert);
                    break;
                case 2:
                    System.out.print("Enter key to search: ");
                    int keySearch = sc.nextInt();
                    System.out.println(hashTable.search(keySearch) ? "Key found!" : "Key not found!");
                    break;
                case 3:
                    System.out.print("Enter key to delete: ");
                    int keyDelete = sc.nextInt();
                    hashTable.delete(keyDelete);
                    break;
                case 4:
                    hashTable.display();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
