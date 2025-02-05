package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DateTimeInfo;

public abstract class Content {
    protected String contentText;
    protected final DateTimeInfo dateTimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        this.dateTimeInfo = new DateTimeInfo();
    }

    public void updateContent(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        dateTimeInfo.updateEditDateTime();
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
