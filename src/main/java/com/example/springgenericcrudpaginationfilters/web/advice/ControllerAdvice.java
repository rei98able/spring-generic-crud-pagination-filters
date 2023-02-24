package com.example.springgenericcrudpaginationfilters.web.advice;

import com.example.springgenericcrudpaginationfilters.web.advice.exception.*;
import com.example.springgenericcrudpaginationfilters.web.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Common Controller Advice based on typical CRUD exceptions
 */
@RestControllerAdvice
public class ControllerAdvice {
    private static final Integer INTERNAL_SERVER_ERROR = 500;
    private static final Integer BAD_REQUEST = 400;
    private static final Integer FORBIDDEN = 403;
    private static final Integer NOT_FOUND = 404;

    /**
     * Exception while finding entity
     *
     * @param er  - error
     * @param req - current request
     * @return ErrorMessageDTO
     */
    @ExceptionHandler(EntityFindByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessageDTO> findException(EntityFindByIdException er, WebRequest req) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(
                NOT_FOUND,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception while getting all entities
     *
     * @param er  - error
     * @param req - current request
     * @return ErrorMessageDTO
     */
    @ExceptionHandler(EntityFindAllException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessageDTO> findAllException(EntityFindAllException er, WebRequest req) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception while saving entity
     *
     * @param er  - error
     * @param req - current request
     * @return ErrorMessageDTO
     */
    @ExceptionHandler(EntitySaveException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessageDTO> saveException(EntitySaveException er, WebRequest req) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception while updating entity
     *
     * @param er  - error
     * @param req - current request
     * @return ErrorMessageDTO
     */
    @ExceptionHandler(EntityUpdateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessageDTO> updateException(EntityUpdateException er, WebRequest req) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception while deleting entity
     *
     * @param er  - error
     * @param req - current request
     * @return ErrorMessageDTO
     */
    @ExceptionHandler(EntityDeleteException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessageDTO> deleteException(EntityDeleteException er, WebRequest req) {
        ErrorMessageDTO errorMessage = new ErrorMessageDTO(
                INTERNAL_SERVER_ERROR,
                new Date(),
                er.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
