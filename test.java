import java.util.*;

class bullshit{
	public static void main(String[] args){
		List<Integer> array = new ArrayList<Integer>();
		for (int i = 0; i<10; i++)
			array.add(i);
		Collections.shuffle(array);
		Integer[] a = array.toArray(new Integer[array.size()]);//?
		for (int i=0; i < a.length; i++)
			System.out.print(a[i]);
		
		//System.out.println("Quicksort: ");
		//randomquicksort.soort(array);
		
		System.out.println();
		System.out.println("Insertionsort: ");
		System.out.println(sort.insertionsort(a));
		
		System.out.println();
		System.out.println("Heapsort: ");
		System.out.println(sort.heapsort(a));
	}
}
class sort{	
	
	public static <E extends Comparable<E>> int insertionsort (E[] array){
		int count = 0;
		for (int i = 1; i < array.length; i++){
				E a = array[i];
			int j = i;
			while(j > 0 && array[j-1].compareTo(a) > 0){
				array[j] = array[j-1];
				j--;
				count++;
			}
			count++;
			array[j] = a;
		}
		return count;
	}

	public static <E extends Comparable<E>> int heapsort (E[] array){
		int count = 0;
		if (array.length <= 1)
			return 0;
		count += buildheap(array);
		int heapsize = array.length;
		while (heapsize >=2){
			exchange(array, 0, heapsize -1);
			heapsize -= 1;
			count += reheap(array, 0, heapsize);
		}
		return count;

	}
	
	public static <E extends Comparable<E>> int buildheap(E[] array){
		int count = 0;
		for (int j = array.length/2-1; j>=0; j--)
			count += reheap(array, j,array.length);
		return count;
	}
	public static <E extends Comparable<E>> int reheap (E[] array, int k, int l){
		int count = 0;
		int maxson;
		if (2*k+1 >= l)
			return 0;
		if (2*k+1 == l-1)
			maxson = 2*k+1;
		else{
			if (array[2*k+1].compareTo(array[2*k+2]) >0 )
				maxson = 2*k+1;
			else
				maxson = 2*k+2;
			count++;
		}

		count++;
		if (array[k].compareTo(array[maxson]) >=0)
			return count;
		exchange(array, k, maxson);
		return count + reheap(array, maxson, l);	             
	}
	public static <E extends Comparable<E>> void exchange(E[] array, int i, int j){
		E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

////////////////////////////////////////////////////////////////////////////////////////////
	public static <E extends Comparable<E>> int quicksort(E[] array){
		return sort(array, 0, array.length-1);
	}
	
	public static <E extends Comparable<E>> int sort(E[] array, int l, int r){
		int count = 0;
		if (l < r){
			int teiler = part(array, l, r);
			count += sort(array, l, teiler -1);
			count += sort(array, teiler+1, r);
		}
		return count;
	}
	
	public static <E extends Comparable<E>> int part(E[] array, int l, int r){
		int count = 0;
		int i = l;
		int j = r-1;
		E pivot = array[r];
		do{
			while (array[i].compareTo(pivot) <=0 && i < r){
				i++;
				count++;
				}
			
			while (array[j].compareTo(pivot) >=0 && j > l){
				j--;
				count++;
				}
			count+=2;
			if (i < j)
				exchange(array,i,j);
		
		}while(i < j);
		count += 1;
		if (array[i].compareTo(pivot) == 1)
			exchange(array,i, r);
		return i;
	
	}
	
}







