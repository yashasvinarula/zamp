package org.example.services;

import org.example.exceptions.DuplicatePhoneException;
import org.example.exceptions.InvalidInputException;
import org.example.models.Contact;

import java.util.List;
import java.util.Optional;

public interface AddressBook {

    void addContact(String firstName, String lastName, String phone, String address) throws InvalidInputException, DuplicatePhoneException;
    List<Contact> searchContactByName(String name);
    Optional<Contact> searchByPhone(String phone);

}
