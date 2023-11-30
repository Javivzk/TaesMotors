package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Customer;
import com.svalero.taesmotors.domain.Extra;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.CustomerNotFoundException;
import com.svalero.taesmotors.exception.ErrorMessage;
import com.svalero.taesmotors.exception.ExtraNotFoundException;
import com.svalero.taesmotors.service.CarService;
import com.svalero.taesmotors.service.ExtraService;
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
public class ExtraController {

    private final Logger logger = LoggerFactory.getLogger(ExtraController.class);

    @Autowired
    private ExtraService extraService;

    @GetMapping("/extras")
    public ResponseEntity<List<Extra>> getExtras(@RequestParam Map<String, String> data) {
        logger.info("GET Extras");

        if (data.isEmpty()) {
            logger.info("END GET Extras");
            return ResponseEntity.ok(extraService.findAll());
        } else if (data.containsKey("name")) {
            List<Extra> extras = extraService.findByName(data.get("name"));
            logger.info("END GET Extras");
            return ResponseEntity.ok(extras);
        } else {
            logger.error("BAD REQUEST");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/extras/{extraId}")
    public ResponseEntity<Extra> getExtra(@PathVariable long extraId) throws ExtraNotFoundException, NumberFormatException{
        logger.info("GET EXTRA");
        Extra extra = extraService.findById(extraId);
        logger.info("END GET EXTRA");
        return ResponseEntity.ok(extra);
    }

    @PostMapping("/extras")
    public ResponseEntity<Extra> addExtra(@Valid @RequestBody Extra extra) {
        Extra newExtra = extraService.addExtra(extra);
        return new ResponseEntity<>(newExtra,HttpStatus.CREATED);
    }

    @PutMapping("/extras/{extraId}")
    public ResponseEntity<Extra> modifyExtra(@PathVariable long extraId, @Valid @RequestBody Extra extra) throws ExtraNotFoundException {
        logger.error("PUT EXTRA");
        Extra newExtra = extraService.modifyExtra(extraId,extra);
        logger.error("END PUT EXTRA");
        return ResponseEntity.status(HttpStatus.OK).body(newExtra);
    }

    @PatchMapping("/extras/{extraId}")
    public ResponseEntity<Extra> patchExtra(@PathVariable long extraId, @RequestBody Map<String, Object> updates) throws ExtraNotFoundException {
        logger.error("PATCH EXTRA");

        Extra updatedExtra = extraService.patchExtra(extraId, updates);

        logger.error("END PATCH EXTRA");
        return ResponseEntity.status(HttpStatus.OK).body(updatedExtra);
    }

    @DeleteMapping(value = "/extras/{extraId}")
    public ResponseEntity<?> deleteExtra(@PathVariable long extraId) throws ExtraNotFoundException {
        extraService.deleteExtra(extraId);
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(ExtraNotFoundException.class)
    public ResponseEntity<?> handleExtraNotFoundException(ExtraNotFoundException enfe){
        logger.error("Extra not found");
        ErrorMessage errorMessage = new ErrorMessage(404, enfe.getMessage());
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
