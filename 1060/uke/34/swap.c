#include <stdio.h>

void Swap(int *firstVal, int *secondVal)
{
  *firstVal = *secondVal + *firstVal; 
  *secondVal = *firstVal - *secondVal;
  *firstVal = *firstVal - *secondVal;
}

main()
{
  int valueA = 3;
  int valueB = 4;

  printf("Before Swap: valueA = %d and valueB = %d\n", valueA, valueB);
  Swap(&valueA, &valueB);
  printf("After Swap: valueA = %d and valueB = %d\n", valueA, valueB);
}


