using System;
using System.Linq;

namespace AiSDTest
{
    class Program
    {
        public static int[] mass = new int[10];
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            Random rnd = new Random();
            for (int i = 0; i < mass.Length; i++)
            {
                mass[i] = rnd.Next(0, 10);
            }
            //QuickSort(mass, 0, mass.Length - 1);
            //mass = CountingSort(mass, 10);
            mass = GnomeSort(mass);
            foreach (var m in mass)
                Console.WriteLine(m);
            Console.ReadLine();
        }
        //Быстрая сортировка
        #region QuickSort
        public static void QuickSort(int[] array, int start, int end)
        {
            if (start >= end)
            {
                return;
            }
            int pivot = Partition(array, start, end);
            QuickSort(array, start, pivot - 1);
            QuickSort(array, pivot + 1, end);
        }
        public static int Partition(int[] array, int start, int end)
        {
            int marker = start;
            for (int i = start; i <= end; i++)
            {
                if (array[i] <= array[end])
                {
                    Swap(array, marker, i);
                    marker += 1;
                }
            }
            return marker - 1;
        }
        public static void Swap(int[] arr, int i, int j)
        {
            int k = arr[i];
            arr[i] = arr[j];
            arr[j] = k;
        }
        #endregion
        //Сортировка слиянием
        #region MergeSort
        static int[] Merge_Sort(int[] massive)
        {
            if (massive.Length == 1)
                return massive;
            int mid_point = massive.Length / 2;
            return Merge(Merge_Sort(massive.Take(mid_point).ToArray()), Merge_Sort(massive.Skip(mid_point).ToArray()));
        }

        static int[] Merge(int[] mass1, int[] mass2)
        {
            int a = 0, b = 0;
            int[] merged = new int[mass1.Length + mass2.Length];
            for (int i = 0; i < mass1.Length + mass2.Length; i++)
            {
                if (b < mass2.Length && a < mass1.Length)
                    if (mass1[a] > mass2[b])
                        merged[i] = mass2[b++];
                    else //if int go for
                        merged[i] = mass1[a++];
                else if (b < mass2.Length)
                    merged[i] = mass2[b++];
                else
                    merged[i] = mass1[a++];
            }
            return merged;
        }
        #endregion
        //Сортировка подсчетом !! Работает только с неотрицательными числами и разброс рандома значений должен быть не больше указанного вторым параметром числа.
        #region CountingSort
        public static int[] CountingSort(int[] array, int k)
        {
            int[] countArray = new int[k];

            for (int i = 0; i < array.Length; i++)
            {
                countArray[array[i]]++;
            }

            for (int i = 1; i < k; i++)
            {
                countArray[i] = countArray[i] + countArray[i - 1];
            }

            int[] resultArray = new int[array.Length];

            for (int i = array.Length - 1; i >= 0; i--)
            {
                resultArray[countArray[array[i]] - 1] = array[i];
                countArray[array[i]] = countArray[array[i]] - 1;
            }

            return resultArray;
        }
        #endregion
        //Гномья сортировка
        #region GnomeSort
        static int[] GnomeSort(int[] array)
        {
            int i = 1;
            int j = 2;
            while (i < array.Length)
            {
                if (array[i - 1] < array[i])
                {
                    i = j;
                    j += 1;
                }
                else
                {
                    Swap(array, i - 1, i);
                    i -= 1;
                    if (i == 0)
                    {
                        i = j;
                        j += 1;
                    }
                }
            }
            return array;
        }
        #endregion
    }
}
