package org.example.services;

import org.apache.commons.lang3.StringUtils;
import org.example.exceptions.DuplicatePhoneException;
import org.example.exceptions.InvalidInputException;
import org.example.models.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class AddressBookImpl implements AddressBook {

    private final Map<String, Contact> phoneToContact;
    private final Map<String, List<Contact>> nameToContactMap;

    public AddressBookImpl() {
        this.phoneToContact = new HashMap<>();
        this.nameToContactMap = new HashMap<>();
    }

    @Override
    public void addContact(String firstName, String lastName, String phone, String address) throws InvalidInputException, DuplicatePhoneException {
        if(phoneToContact.containsKey(phone)) {
            throw new DuplicatePhoneException();
        }
        Contact contact = new Contact(firstName, lastName, phone, address);
        phoneToContact.put(phone, contact);
        String name = getName(firstName, lastName);
        nameToContactMap.computeIfAbsent(name, k -> new ArrayList<>()).add(contact);
    }

    @Override
    public List<Contact> searchContactByName(String name) {
        name = name.toLowerCase();
        return nameToContactMap.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public Optional<Contact> searchByPhone(String phone) {
        Contact contact = phoneToContact.getOrDefault(phone, null);
        return Optional.ofNullable(contact);
    }

    private String getName(String firstName, String lastName) {
        String name = "";
        name += firstName.toLowerCase();
        if(StringUtils.isNotBlank(StringUtils.trimToEmpty(lastName))) {
            name += " ";
            name += lastName.toLowerCase();
        }
        return name;
    }


}
