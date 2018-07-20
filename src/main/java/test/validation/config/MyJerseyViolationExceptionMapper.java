package test.validation.config;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yavor bachev
 * @since 0.1
 * 2018 Jul
 */
public class MyJerseyViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {

        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<?> violation = iterator.next();
            sb.append(String.format("Parameter %s %s (was %s), for %s",
                                    violation.getPropertyPath(),
                                    violation.getMessage(),
                                    violation.getInvalidValue(),
                                    violation.getRootBeanClass()));
            if (iterator.hasNext()) {
                sb.append("\n");
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(sb.toString()).type(MediaType.APPLICATION_JSON).build();
    }
}