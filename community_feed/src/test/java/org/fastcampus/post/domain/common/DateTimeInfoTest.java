package org.fastcampus.post.domain.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeInfoTest {

    @Test
    void 생성후_업데이트_업데이트상태변경() throws InterruptedException {
        // given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime now = dateTimeInfo.getDateTime();
        // when
        Thread.sleep(1L);
        dateTimeInfo.updateEditDateTime();
        // then
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(now, dateTimeInfo.getDateTime());
    }
}