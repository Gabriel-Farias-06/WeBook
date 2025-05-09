package com.webook.app.domain.Exceptions;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException() { super("Email já existe no sistema");
    }
}
