package org.melusky.bookbash.model.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mikem on 7/10/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private Integer status;
    private Integer code;
    private String message;
}
