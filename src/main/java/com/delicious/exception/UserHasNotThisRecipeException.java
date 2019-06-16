package com.delicious.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserHasNotThisRecipeException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "This user has not the required resource";

    public UserHasNotThisRecipeException() {
        super(DEFAULT_MESSAGE);
    }
    public UserHasNotThisRecipeException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserHasNotThisRecipeException(String message) {
        super(message);
    }
    public UserHasNotThisRecipeException(Throwable cause) {
        super(cause);
    }

}
