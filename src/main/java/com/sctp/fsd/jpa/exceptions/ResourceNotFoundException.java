package com.sctp.fsd.jpa.exceptions;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(String id) {
        super("Could not find Customer: " + id);
    }
}


