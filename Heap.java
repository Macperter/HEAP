package Heap;

import java.util.ArrayList;
import java.util.List;

public class Heap<T> {
    private List<T> nodes = null;
    public static boolean GREATER = false;// 从小到大
    public static boolean LESS = true; // 从大到小
    private boolean isless = true;
    private CMPt<T> cmp = null;
    private int len = 0;

    public Heap() {
        nodes = new ArrayList<>();
    }

    public Heap(CMPt<T> t, boolean k) {
        this();
        this.isless = k;
        this.cmp = t;
    }

    private boolean isCmp(T a, T b) {
        return cmp.Smaller(a, b) ^ (!isless);
    }

    public boolean Empty() {
        if (len == 0)
            return true;
        return false;
    }

    public void push(T a) {
        nodes.add(a);
        int son = len;
        while (son != 0) {
            int part = (son - 1) >> 1;
            if (isCmp(nodes.get(part), nodes.get(son))) {// part < son
                swap(part, son);
                /*
                 * T tmp = nodes.get(part); nodes.set(part, nodes.get(son)); nodes.set(son,
                 * tmp);
                 */
            } else {
                len++;
                return;
            }

            son = part;

        }
        len++;
    }

    public void pop() {
        if (Empty())
            return;
        nodes.set(0, nodes.get(len - 1));
        nodes.remove(--len);
        int pos = 0;
        int maxi = maxindex(pos);
        while (maxi != -1) {
            if (isCmp(nodes.get(pos), nodes.get(maxi))) {
                swap(pos, maxi);
            } else
                return;
            pos = maxi;
            maxi = maxindex(pos);
        }

    }

    public T top() {
        if (Empty())
            return null;
        return nodes.get(0);
    }

    public void swap(int a, int b) {
        T tmp = nodes.get(a);
        nodes.set(a, nodes.get(b));
        nodes.set(b, tmp);
    }

    private int maxindex(int index) {
        int son1 = 0, son2 = 0;
        son1 = (index << 1) + 1;
        son2 = son1 + 1;
        if (son2 <= len - 1) {
            int max = son1;
            if (isCmp(nodes.get(son1), nodes.get(son2)))
                max = son2;
            return max;
        } else if (son1 <= len - 1)
            return son1;
        return -1;

    }
};

interface CMPt<T> {
    boolean Smaller(T a, T b); // is a<b ?
};
