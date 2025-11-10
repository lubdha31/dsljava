import java.util.Scanner;
import java.util.Stack;

public class TextEditorUndoRedo {

    // Stacks for Undo and Redo
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();

    // Current document content
    private String currentState = "";

    // Make a new change
    public void makeChange(String newText) {
        undoStack.push(currentState);   // Save current state for undo
        currentState = newText;         // Apply the new change
        redoStack.clear();              // Clear redo history after new change
        System.out.println("Change applied: \"" + newText + "\"");
    }

    // Undo the last change
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }
        redoStack.push(currentState);        // Save current state for redo
        currentState = undoStack.pop();      // Revert to last state
        System.out.println("Undo performed.");
    }

    // Redo the most recently undone change
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo!");
            return;
        }
        undoStack.push(currentState);        // Save current state for undo again
        currentState = redoStack.pop();      // Reapply last undone change
        System.out.println("Redo performed.");
    }

    // Display the current document
    public void display() {
        System.out.println("\n--- Document State ---");
        System.out.println(currentState.isEmpty() ? "[Document is empty]" : currentState);
    }

    // Main program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditorUndoRedo editor = new TextEditorUndoRedo();

        while (true) {
            System.out.println("\n--- TEXT EDITOR ---");
            System.out.println("1. Make a Change");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Document");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new document text: ");
                    String text = sc.nextLine();
                    editor.makeChange(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.display();
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
