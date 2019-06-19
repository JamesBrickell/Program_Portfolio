"""
Author: James
program: Quicksort v Mergsort
date: 3/17/2019
purpose: To test the difference between quicksort
and mergsort.
"""
import math
import random
import time


def mergsort(an_array):
    """
    Summary Line:

    Sort an array form decreasing to increasing.

    :param an_array: The array.

    :return: The sorted array.

    """
    A = an_array
    n = len(A)
    mid = math.floor(n/2)
    
    if n > 1:
        B = list(A[0:mid])
        C = list(A[mid:])
        mergsort(B)
        mergsort(C)
        return merge(B, C, A)


def merge(b, c, a):
    """
    Summary line:
        Merges two halves of a list into one sorted.

    :param b: One half of the list.
    :param c: Second half of a list.
    :param a: The list.

    :return: The merged list.

    """
    p = len(b)
    q = len(c)
    i = 0
    j = 0
    k = 0
    
    while i < p and j < q:
        
        if b[i] <= c[j]:
            a[k] = b[i]
            i += 1
        
        else:
            a[k] = c[j]
            j += 1
        
        k += 1
    
    if i == p:
        
        for n in range(q - j):
            a[k] = c[j]
            j += 1
            k += 1
    
    else:
        
        for n in range(p - i):
            a[k] = b[i]
            k += 1
            i += 1


def hoare_partition(A, left, right):
    """
    Summary Line:
        Partitions a list.

    :param A: The list
    :param left: lower bound
    :param right: upper bound

    :return: The new index of the partition list.
    """
    
    p = A[left]
    i = left - 1
    j = right + 1
    
    while True:
        
        while True:
            i += 1
            
            if A[i] >= p:
                break
        
        while True:
            j -= 1
            
            if A[j] <= p:
                break
        A[i], A[j] = A[j], A[i]
        
        if i >= j:
            break

    A[i], A[j] = A[j], A[i]
    A[left], A[j] = A[j], A[left]

    return j


def quick_sort(an_array, left, right):
    """
    Summary Line:
        Sorts an in array in increasing order.

    :param an_array: The Array wanting to be sorted.
    :param left:  The lower bound.
    :param right: The upper bound.

    :return: A sorted list.
    """
    
    if left < right:
        
        s = hoare_partition(an_array, left, right)
        
        quick_sort(an_array, left, s)
        
        quick_sort(an_array, s+1, right)


def make_sorted_array(n):
    """
    Summary Line:
        Makes a sorted array from 0 to n

    :param n: the upper bound.

    :return: a list.
    """
    a = []

    for i in range(n):
        a.append(i)

    return a


def make_array(n):
    """
    Summary Line:
        A list of numbers from 0 to n
        shuffled.

    :param n: The upper bound.

    :return: A shuffled list.
    """
    a = []

    for i in range(n):
        a.append(i)

    random.shuffle(a)
    
    return a


def test():
    """
    Summary Line:
        The purpose of this function is to test 
        the difference in time complexity between 
        mergsort and quicksort.
    :return: NONE 
    """
    N = int(input("Enter an upper bound: "))
    
    for i in range(0, N, 25):
        a = make_array(i)
        b = list(a)

        start = time.process_time()
        mergsort(a)
        end = time.process_time()
        finish = end - start

        start_one = time.process_time()
        quick_sort(b, 0, len(b)-1)
        end_one = time.process_time()
        finish_one = end_one-start_one
        
        if i == 0:
            print("Mergesort V Quicksort")
            
            print("N\t Mergesort time\t Quicksort time")
            
            print("%d\t%f\t%f" % (i, finish, finish_one))
        
        else:
            print("%d\t%f\t%f" % (i, finish, finish_one))


def main():
    print(test.__doc__)
    test()


if __name__ == "__main__":
    main()



