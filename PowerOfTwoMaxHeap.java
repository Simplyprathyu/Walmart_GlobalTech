import java.util.ArrayList;

public class PowerOfTwoMaxHeap {
    private ArrayList<Integer> heap;
    private int childCount;  // This is 2^x

    public PowerOfTwoMaxHeap(int exponent) {
        this.childCount = (int) Math.pow(2, exponent);
        heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        percolateUp(heap.size() - 1);
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / childCount;
            if (heap.get(index) > heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int maxValue = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        percolateDown(0);
        return maxValue;
    }

    private void percolateDown(int index) {
        int childIndex;
        while ((childIndex = getChildIndexForMax(index)) < heap.size()) {
            if (heap.get(index) < heap.get(childIndex)) {
                swap(index, childIndex);
                index = childIndex;
            } else {
                break;
            }
        }
    }

    private int getChildIndexForMax(int index) {
        int bestChildIndex = index * childCount + 1;
        for (int i = 2; i <= childCount && index * childCount + i < heap.size(); i++) {
            int thisChildIndex = index * childCount + i;
            if (heap.get(thisChildIndex) > heap.get(bestChildIndex)) {
                bestChildIndex = thisChildIndex;
            }
        }
        return bestChildIndex;
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2); // x = 2 means each node can have up to 4 children

        // Insert elements
        heap.insert(10);
        heap.insert(15);
        heap.insert(20);
        heap.insert(17);
        heap.insert(8);

        // Print the max element and then remove it
        System.out.println("Max Element: " + heap.popMax()); // Should print the largest element

        // Insert more elements and test again
        heap.insert(22);
        heap.insert(5);

        // Print the max element and then remove it
        System.out.println("Max Element: " + heap.popMax()); // Should print the new largest element
    }
}
