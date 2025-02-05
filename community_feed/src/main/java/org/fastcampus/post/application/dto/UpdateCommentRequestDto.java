package org.fastcampus.post.application.dto;

public record UpdateCommentRequestDto(
    Long commentId,
    String updateContent
) {

}
