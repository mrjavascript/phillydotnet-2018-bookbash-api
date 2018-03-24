package org.melusky.bookbash.model.type.backlog;

import lombok.Data;

@Data
public class BacklogStatusModel {
    private Long typeId;
    private String typeName;
    private String typeDescription;
}
