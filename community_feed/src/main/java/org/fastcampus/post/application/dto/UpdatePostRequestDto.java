package org.fastcampus.post.application.dto;

import org.fastcampus.post.domain.PostPublicationState;

public record UpdatePostRequestDto(Long postId, String content, PostPublicationState state) {

}
