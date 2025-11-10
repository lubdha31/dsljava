# Sample data
members_borrowing = {
    "M001": ["B001", "B002", "B003"],
    "M002": [],
    "M003": ["B002", "B003", "B003"],
    "M004": ["B001"],
    "M005": ["B003", "B004"]

   
}

def compute_average_borrowed(members_data):
    total_books = sum(len(books) for books in members_data.values())
    total_members = len(members_data)
    average = total_books / total_members if total_members > 0 else 0
    return average

def book_borrowing_counts(members_data):
    book_count = {}
    for books in members_data.values():
        for book in books:
            book_count[book] = book_count.get(book, 0) + 1
    return book_count

def highest_and_lowest_borrowed(book_counts):
    if not book_counts:
        return None, None

    highest = max(book_counts.items(), key=lambda x: x[1])
    lowest = min(book_counts.items(), key=lambda x: x[1])
    return highest, lowest

def count_members_with_no_borrowings(members_data):
    return sum(1 for books in members_data.values() if len(books) == 0)

def most_frequent_borrowed_books(book_counts):
    if not book_counts:
        return []
    max_count = max(book_counts.values())
    mode_books = [book for book, count in book_counts.items() if count == max_count]
    return mode_books



# Main execution
average = compute_average_borrowed(members_borrowing)
book_counts = book_borrowing_counts(members_borrowing)
highest, lowest = highest_and_lowest_borrowed(book_counts)
zero_borrowers = count_members_with_no_borrowings(members_borrowing)
most_frequent = most_frequent_borrowed_books(book_counts)

# Output
print(f"Average books borrowed per member: {average}")
print(f"Most borrowed book: {highest[0]} with {highest[1]} borrowings")
print(f"Least borrowed book: {lowest[0]} with {lowest[1]} borrowings")
print(f"Number of members who have not borrowed any books: {zero_borrowers}")
print(f"Most frequently borrowed book(s): {', '.join(most_frequent)}")
