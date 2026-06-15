"""
Quick Sort
Author: RIT CS

Perform an out of place quick sort on a native array of integers.

quick_sort (not in place):
    best=O(nlogn)
    worst=O(n^2)

To run:
    $ python3 quick_sort.py
"""


def _partition(data: list[int], pivot: int) \
        -> tuple[list[int], list[int], list[int]]:
    """
    Three way partition the data into smaller, equal and greater lists,
    in relationship to the pivot
    :param data: The data to be sorted (a list)
    :param pivot: The value to partition the data on
    :return: Three list: smaller, equal and greater
    """
    less, equal, greater = [], [], []
    for element in data:
        if element < pivot:
            less.append(element)
        elif element > pivot:
            greater.append(element)
        else:
            equal.append(element)
    return less, equal, greater


def quick_sort(data: list[int]) -> list[int]:
    """
    Performs a quick sort and returns a newly sorted list
    :param data: The data to be sorted (a list)
    :return: A sorted list
    """
    if len(data) == 0:
        return []
    else:
        pivot = data[0]
        less, equal, greater = _partition(data, pivot)
        return quick_sort(less) + equal + quick_sort(greater)


def main() -> None:
    """
    Test function for quick_sort
    """
    DATA = (
        (),
        (0,),
        (0, 1),
        (1, 0),
        (0, 1, 2),
        (0, 2, 1),
        (1, 0, 2),
        (1, 2, 0),
        (2, 0, 1),
        (2, 1, 0),
        (9, 5, 2, 6, 3, 8, 1, 4, 0, 7)
    )

    for data in DATA:
        # create two lists of the data
        sort_data, sorted = list(data), list(data)
        # merge sort is not in place and returns a new sorted list
        sort_data = quick_sort(sort_data)
        # use built in sort to compare against
        sorted.sort()
        # show the results of the comparison
        print('quick_sort:', list(data), 'result:', sort_data,
              'OK' if sort_data == sorted else 'FAIL!')


if __name__ == '__main__':
    main()
