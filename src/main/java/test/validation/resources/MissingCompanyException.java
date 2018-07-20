package test.validation.resources;

import test.validation.dropwizard.exceptions.ErrorCodeResponseException;

import javax.ws.rs.core.Response;

/**
 * @author yavor bachev
 * @since 0.1
 * 2018 Jul
 */
public class MissingCompanyException extends ErrorCodeResponseException {
    public MissingCompanyException(String orgId) {
        super("Missing Company reference for Organization: " + orgId,
              Response.Status.NOT_IMPLEMENTED);
    }
}