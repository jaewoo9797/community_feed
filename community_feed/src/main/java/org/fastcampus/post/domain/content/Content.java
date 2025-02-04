package org.fastcampus.post.domain.content;

public abstract class Content {
    protected String contentText;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
    }

    protected abstract void checkText(String contentText);

    public String getContentText() {
        return contentText;
    }
}
