package org.fastcampus.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserInfoTest {

    @Test
    void 이름_프로필사진url_객체생성_No에러() {
        // given
        String url = "https://www.fastcampus.com";
        String name = "홍길동";
        // when
        // then
        assertDoesNotThrow(() -> new UserInfo(name, url));
    }

    @Test
    void blank이름_프로필사진_객체생성_throwError() {
        // given
        String name = "";
        String url = "https://www.fastcampus.com";
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, url));
    }
}