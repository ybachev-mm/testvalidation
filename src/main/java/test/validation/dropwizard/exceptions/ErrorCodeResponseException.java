package test.validation.dropwizard.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author yavor bachev
 * @since 0.1
 * 2018 Jul
 */
public class ErrorCodeResponseException extends WebApplicationException {
    public ErrorCodeResponseException(String message, Response.Status status) {
        super(message, status);
    }

    public static WebApplicationException buildCustomException(int statusCode){
        Status fromStatusCode = Status.fromStatusCode(statusCode);
        if(fromStatusCode == null){
            return new WebApplicationException(String.format(" Application error, undefined status code used - %s", statusCode), 500);
        }
        String reason = fromStatusCode.getReason();
        return new WebApplicationException(reason, statusCode);
    }

    public static WebApplicationException buildCustomException(int statusCode, String message){
        return new WebApplicationException(message, statusCode);
    }


    public static WebApplicationException buildCustomException(Status statusCode){
        String reason = statusCode.getReason();
        return new WebApplicationException(reason, statusCode.getStatusCode());
    }

    public static WebApplicationException buildCustomException(Status statusCode, String ... substitute ){
        String reason = statusCode.getReason();
        String message = String.format(reason, substitute);
        return new WebApplicationException(message, statusCode.getStatusCode());
    }



    public static enum Status implements ResponseStatus {

        EXPECTATION_FAILED(17, "Custom Expectation Failed!"),
        WRONG_PARAMETERS(42, "Wrong Params in method - %s, %s"),
        UNPROCESSABLE_ENTITY(422, "Unprocessable Entity");

        @Override
        public int getStatusCode() {
            return this.code;
        }

        private final int code;
        private final String reason;

        private Status(int statusCode, String reasonPhrase) {
            this.code = statusCode;
            this.reason = reasonPhrase;
        }

        public String getReason() {
            return this.toString();
        }

        public String toString() {
            return this.reason;
        }

        public static Status fromStatusCode(int statusCode) {
            Status[] arr$ = values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Status s = arr$[i$];
                if (s.code == statusCode) {
                    return s;
                }
            }

            return null;
        }
    }

    public interface ResponseStatus {
        int getStatusCode();
    }

    }