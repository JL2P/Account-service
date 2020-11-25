package com.account.api.web.dto.todo;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoGalleryDto implements Serializable {
    private Long id;
    private String title;
    private String filePath;
    private Long todoId;
}