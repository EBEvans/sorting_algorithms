#!/usr/bin/python3
from random import randint
from timeit import default_timer as timer

def verifySorts(array, numElements):
   for n in range(0, numElements-1):
      if (array[n] > array[n+1]):
         return False
   return True

def bubbleSort(array, numElements):
   n = numElements
   for i in range(0, n-1):
      for j in range(0, n-1-i):
         if (array[j] > array[j+1]):
            temp = array[j]
            array[j] = array[j+1]
            array[j+1] = temp

def selectionSort(array, numElements):
   n = numElements
   for fill in range(0, n-1):
      posMin = fill
      for _next in range(fill+1, n):
         if (array[_next] < array[posMin]):
            posMin = _next
      temp = array[fill]
      array[fill] = array[posMin]
      array[posMin] = temp

def insertionSort(array, numElements):
   for nextPos in range(1, numElements):
      nextVal = array[nextPos]
      while ((nextPos > 0) and (nextVal < array[nextPos-1])):
         array[nextPos] = array[nextPos-1]
         nextPos -= 1
      array[nextPos] = nextVal

def conductRun(numElements):
   array1 = []
   array2 = []
   array3 = []
   results = []
   for n in range(0, numElements):
      temp = int(randint(1, numElements*2))
      array1.append(temp)
      array2.append(temp)
      array3.append(temp)

   print("  Conducting Bubble Sort: ")
   start = timer()
   bubbleSort(array1, numElements)
   end = timer()
   results.append(end - start)
   if (verifySorts(array1, numElements)):
      print("     Sort successful")
      print("     " + str(end - start))
   else:
      print("     Sort failed")

   print("  Conducting Selection Sort: ")
   start = timer()
   selectionSort(array2, numElements)
   end = timer()
   results.append(end - start)
   if (verifySorts(array2, numElements)):
      print("     Sort successful")
      print("     " + str(end - start))
   else:
      print("     Sort failed")

   print("  Conducting Insertion Sort:" )
   start = timer()
   insertionSort(array3, numElements)
   end = timer()
   results.append(end - start)
   if (verifySorts(array3, numElements)):
      print("     Sort successful")
      print("     " + str(end - start))
   else:
      print("     Sort failed")

   return results
   
def main():
   numElements = 5000
   print("Sorting " + str(numElements) + " random integers")
   print("Conducting first run: ")
   results1 = conductRun(numElements)
   print("Conducting second run: ")
   results2 = conductRun(numElements)
   print("Conducting third run: ")
   results3 = conductRun(numElements)
   averages = []
   averages.append((results1[0] + results2[0] + results3[0])/3)
   averages.append((results1[1] + results2[1] + results3[1])/3)
   averages.append((results1[2] + results2[2] + results3[2])/3)
   print("Average times were: ")
   print("  Bubble sort: " + str(averages[0]))
   print("  Selection sort: " + str(averages[1]))
   print("  Insertion sort: " + str(averages[2]))
   

main()
