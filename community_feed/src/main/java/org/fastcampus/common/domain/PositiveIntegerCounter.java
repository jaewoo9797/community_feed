package org.fastcampus.common.domain;

public class PositiveIntegerCounter {

    private int count;

    public PositiveIntegerCounter() {
        count = 0;
    }

    public PositiveIntegerCounter(int count) {
        this.count = count;
    }

    public void increase() {
        count++;
    }

    public void decrease() {
        if (count <= 0) {
            return;
        }
        count--;
    }

    public int getCount() {
        return count;
    }
}
