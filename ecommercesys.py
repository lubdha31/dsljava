def linear_search(arr, target):
    for i in arr:
        if i == target:
            return True
    return False

def binary_search(arr, target):
    low, high = 0, len(arr) - 1
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] == target:
            return True
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return False

customer_ids = [101, 203, 305, 409, 512, 678, 789, 890]
target = int(input("Enter customer account ID to search: "))

print("Using Linear Search:")
print("Found" if linear_search(customer_ids, target) else "Not Found")

print("Using Binary Search:")
customer_ids.sort()
print("Found" if binary_search(customer_ids, target) else "Not Found")
