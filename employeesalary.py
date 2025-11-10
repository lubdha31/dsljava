# Function for Selection Sort
def selection_sort(salaries):
    n = len(salaries)
    for i in range(n - 1):
        min_index = i
        for j in range(i + 1, n):
            if salaries[j] < salaries[min_index]:
                min_index = j
        salaries[i], salaries[min_index] = salaries[min_index], salaries[i]
    return salaries


# Function for Bubble Sort
def bubble_sort(salaries):
    n = len(salaries)
    for i in range(n):
        for j in range(0, n - i - 1):
            if salaries[j] > salaries[j + 1]:
                salaries[j], salaries[j + 1] = salaries[j + 1], salaries[j]
    return salaries


# Function to display top 5 highest salaries
def display_top_five(salaries):
    print("\nTop 5 Highest Salaries:")
    top_five = sorted(salaries, reverse=True)[:5]
    for i, salary in enumerate(top_five, start=1):
        print(f"{i}. ₹{salary:.2f}")


# Main Program
def main():
    salaries = []
    n = int(input("Enter number of employees: "))

    for i in range(n):
        salary = float(input(f"Enter salary of employee {i + 1}: ₹"))
        salaries.append(salary)

    print("\nOriginal Salaries:")
    print(salaries)

    # Selection Sort
    selection_sorted = selection_sort(salaries.copy())
    print("\nSalaries after Selection Sort (Ascending):")
    print(selection_sorted)
    display_top_five(selection_sorted)

    # Bubble Sort
    bubble_sorted = bubble_sort(salaries.copy())
    print("\nSalaries after Bubble Sort (Ascending):")
    print(bubble_sorted)
    display_top_five(bubble_sorted)


# Run Program
if __name__ == "__main__":
    main()
