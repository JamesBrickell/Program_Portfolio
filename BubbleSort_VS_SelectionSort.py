"""
Author: James Brickell

Program: BubbleSort_VS_SelectionSort
"""
import random
import time


def make_random(n):
    """
    Summary Line.

    This function is responsible for making a random sorted
    list.

    :param n: the parameter is not only the size of the list but the top range for randInt range.

    :return: a list of random ints of length n with numbers in set 0:n.

    """
    the_list = []
    for i in range(0, n+1):
        random_num = random.randint(0, n)
        the_list.append(random_num)

    return the_list


def selection_sort(a_list):
    """
    Summary Line.

    Sorts a list of numbers by using the selection sort algorithm.

    :param a_list: The list desired to be sorted.

    :return: None, this function manipulates the original list.

    """
    n = len(a_list)

    for i in range(0, n-1):
        minimum = i
        for j in range(i+1, n):
            if a_list[j] < a_list[minimum]:
                minimum = j
        temp = a_list[i]
        a_list[i] = a_list[minimum]
        a_list[minimum] = temp


def bubble_sort(a_list):
    """
    Summary Line.

    Sorts a list of numbers using Bubble sort.

    :param a_list: The list of numbers.

    :return: None, this function manipulates the original function.

    """
    n = len(a_list)

    for i in range(0, n-1):
        for j in range(0, n - 1 - i):
            if a_list[j+1] < a_list[j]:
                temp = a_list[j]
                a_list[j] = a_list[j+1]
                a_list[j+1] = temp


def test(n):
    """
    Summary Line.

    This Function purpose is to test the
    difference in speed between bubble sort
    and selection sort.

    :param n: (int)n>100 The high end of the for loop.

    :return: None

    """
    for i in range(100, n+1, 145):
        some_list = make_random(i)
        copy_list = list(some_list)

        start = time.process_time()
        selection_sort(copy_list)
        end = time.process_time()
        final_time = end-start

        start_two = time.process_time()
        selection_sort(some_list)
        end_two = time.process_time()
        final_time_two = end_two-start_two

        if i == 100:
            print("N\tS_Sort_time\tB_Sort_time")
        print(i, '\t', final_time, '\t', final_time_two)


def main():
    print(test.__doc__)
    n = int(input("Please enter an upper bound: "))
    test(n)


if __name__ == "__main__":
    main()



