package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.Content;

public class PostContent extends Content {

    public PostContent(String content) {
        super(content);
    }

    @Override
    protected void checkText(String contentText) {
        if (contentText == null || contentText.isEmpty() || contentText.length() > 500) {
            throw new IllegalArgumentException();
        }
    }

}
