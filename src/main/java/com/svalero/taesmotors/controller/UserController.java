package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.domain.User;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.exception.ErrorMessage;
import com.svalero.taesmotors.exception.ExtraNotFoundException;
import com.svalero.taesmotors.exception.UserNotFoundException;
import com.svalero.taesmotors.service.EmployeeService;
import com.svalero.taesmotors.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam Map<String, String> data) {
        logger.info("GET Users");

        if (data.isEmpty()) {
            logger.info("END GET Users");
            return ResponseEntity.ok(userService.findAll());
        } else if (data.containsKey("name")) {
            List<User> users = userService.findByName(data.get("name"));
            logger.info("END GET Users");
            return ResponseEntity.ok(users);
        } else if (data.containsKey("surname")) {
            List<User> users = userService.findBySurname(data.get("surname"));
            logger.info("END GET Users");
            return ResponseEntity.ok(users);
        } else {
            logger.error("BAD REQUEST");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getEmployee(@PathVariable long userId) throws UserNotFoundException, NumberFormatException{
        logger.info("GET USER");
        User user = userService.findById(userId);
        logger.info("END GET User");
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> modifyUser(@PathVariable long userId, @Valid @RequestBody User user) throws UserNotFoundException, ExtraNotFoundException {
        logger.error("PUT USER");
        User newUser = userService.modifyUser(userId,user);
        logger.error("END PUT USER");
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<User> patchUser(@PathVariable long userId, @RequestBody Map<String, Object> updates) throws UserNotFoundException, ExtraNotFoundException {
        logger.error("PATCH USER");
        User updatedUser = userService.patchUser(userId, updates);
        logger.error("END PATCH USER");
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException unfe){
        logger.error("Employee not found");
        ErrorMessage errorMessage = new ErrorMessage(404, unfe.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        logger.error("Incorrect data");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error("Internal error " + e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
