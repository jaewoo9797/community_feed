package org.fastcampus.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PostContentTest {
    @Test
    void 글_길이_정상범위_정상생성() {
        // given
        String text = "this is a test";
        // when
        PostContent postContent = new PostContent(text);
        // then
        assertEquals(postContent.getContentText(),text);
    }

    @Test
    void 글_길이_오버범위_에러throw() {
        // given
        String text = "a".repeat(501);
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @Test
    void 글_길이_언더범위_에러throw() {
        // given
        String text = "a".repeat(4);
        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void writeHereTestName(String text) {
        // given

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }
}