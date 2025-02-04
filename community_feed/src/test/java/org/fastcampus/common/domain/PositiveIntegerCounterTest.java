package org.fastcampus.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PositiveIntegerCounterTest {

    @Test
    void 객체생성_증가메서드호출_count는1() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        // when
        counter.increase();
        // then
        assertEquals(1, counter.getCount());
    }

    @Test
    void 객체생성_증가후감소메서드호출_count는0() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        // when
        counter.increase();
        counter.decrease();
        // then
        assertEquals(0, counter.getCount());
    }

    @Test
    void 객체생성_증가하지않고감소메서드호출_count는0() {
        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        // when
        counter.decrease();
        // then
        assertEquals(0, counter.getCount());
    }
}