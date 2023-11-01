//package lk.ijse.travle.userservice.exeption;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.dao.DuplicateEntryException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//
///**
// * @Author : Sudeera Madushan
// * @Date : 10/15/2023
// * @Project : TravelPlanningSystem
// */
//
//@Order(Ordered.HIGHEST_PRECEDENCE)
//@RestControllerAdvice
//public class ExceptionHandler extends ResponseEntityExceptionHandler {
////    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
////    public ResponseEntity<Error> handleGlobalException(Exception ex, WebRequest request) {
////        Error error = new Error(
////                HttpStatus.INTERNAL_SERVER_ERROR,
////                ex.getMessage(),
////                request.getDescription(false),
////                LocalDateTime.now()
////        );
////        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
////    }
//    @org.springframework.web.bind.annotation.ExceptionHandler(lk.ijse.travle.userservice.exeption.DuplicateEntryException.class)
//    public ResponseEntity<Error> handleDuplicateException(
//            lk.ijse.travle.userservice.exeption.DuplicateEntryException ex, WebRequest request) {
//        Error error = new Error(
//                HttpStatus.CONFLICT,
//                ex.getMessage(),
//                request.getDescription(false),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
//    public ResponseEntity<Error> handleServiceException(ServiceException ex, WebRequest request) {
//        Error error = new Error(
//                ex.getHttpStatus(),
//                ex.getMessage(),
//                request.getDescription(false),
//                LocalDateTime.now()
//        );
//        return new ResponseEntity<>(error, ex.getHttpStatus());
//    }
//
//}
