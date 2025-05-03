package com.webook.app.domain.Exceptions;

public class EmailAlreadyExists extends DomainException {
    public EmailAlreadyExists() { super("Email já existe no sistema");
    }
}
