package org.fastcampus.user.domain;

public class UserRelationCounter {

    private int count;

    public UserRelationCounter() {
        count = 0;
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
}
