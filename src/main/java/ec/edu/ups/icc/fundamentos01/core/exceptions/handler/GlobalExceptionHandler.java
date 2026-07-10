package ec.edu.ups.icc.fundamentos01.core.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ec.edu.ups.icc.fundamentos01.core.exceptions.base.ApplicationException;
import ec.edu.ups.icc.fundamentos01.core.exceptions.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ============== EXCEPCIONES DE NEGOCIO ==============

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(
            ApplicationException ex,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                ex.getStatus(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    // ============== EXCEPCIONES DE VALIDACIÓN ==============

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Datos de entrada inválidos",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(
            BindException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Parámetros de consulta inválidos",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    // ============== EXCEPCIONES DE SEGURIDAD ==============

    /*
     * PRÁCTICA 11
     * Credenciales inválidas en /auth/login.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Email o contraseña incorrectos",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    /*
     * PRÁCTICA 12
     * Maneja AuthorizationDeniedException (Spring Security 6.x+).
     *
     * Se lanza cuando @PreAuthorize evalúa a false.
     * Ejemplo: usuario con ROLE_USER intenta acceder a un endpoint
     * protegido con hasRole('ADMIN').
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationDeniedException(
            AuthorizationDeniedException ex,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.FORBIDDEN,
                "No tienes permisos para acceder a este recurso",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    /*
     * PRÁCTICA 12
     * Maneja AccessDeniedException (fallback / validaciones manuales).
     *
     * Se usará también en la Práctica 13 para validación de ownership
     * lanzada manualmente desde los servicios.
     */
    /*
        * PRÁCTICA 13
        * Maneja errores de acceso denegado (rol insuficiente u ownership).
        *
        * Usa el mensaje específico lanzado desde el servicio, por ejemplo:
        * "No puedes modificar productos ajenos"
        */
        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ErrorResponse> handleAccessDeniedException(
                AccessDeniedException ex,
                HttpServletRequest request
        ) {
        String message = ex.getMessage();

        if (message == null || message.isBlank()) {
                message = "Acceso denegado";
        }

        ErrorResponse response = new ErrorResponse(
                HttpStatus.FORBIDDEN,
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

    /*
     * PRÁCTICA 12
     * Maneja AuthenticationException genérica (fallback de autenticación).
     *
     * Nota: JwtAuthenticationEntryPoint ya maneja la mayoría de los casos
     * de token ausente o inválido antes de llegar aquí. Este handler
     * cubre casos residuales de AuthenticationException no capturados
     * por el entry point.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException ex,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNAUTHORIZED,
                "Credenciales inválidas o sesión expirada",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // ============== EXCEPCIONES GENERALES ==============

    /*
     * Debe ser el último manejador (más genérico).
     * Spring busca primero el manejador más específico.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception ex,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
