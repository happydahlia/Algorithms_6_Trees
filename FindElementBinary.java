public class FindElementBinary {
    public boolean findElement(int[] bt, int t) {
        return search(bt, 0, 1, t);
    }

    private boolean search(int[] bt, int i, int val, int t) {

        if (i >= bt.length || bt[i] == -1) {
            return false;
        }

        if (val == t) {
            return true;
        }

        int leftVal = 3 * val + 1;
        int rightVal = 2 * val + 5;


        return search(bt, 2*i + 1, leftVal, t) ||  search(bt, 2*i + 2, rightVal, t);
    }
}
