package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.example.exceptions.InvalidInputException;

import static org.example.constants.JsonProperties.ContactConstants.ADDRESS;
import static org.example.constants.JsonProperties.ContactConstants.FIRST_NAME;
import static org.example.constants.JsonProperties.ContactConstants.LAST_NAME;
import static org.example.constants.JsonProperties.ContactConstants.PHONE;

public class Contact {
    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String address;

    @JsonCreator
    public Contact(
            @JsonProperty(FIRST_NAME) String firstName,
            @JsonProperty(LAST_NAME) String lastName,
            @JsonProperty(PHONE) String phone,
            @JsonProperty(ADDRESS) String address
    ) throws InvalidInputException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        validate(firstName, phone, address);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    private void validate(String firstName, String phone, String address) throws InvalidInputException {
        if(StringUtils.isBlank(StringUtils.trimToEmpty(firstName))) {
            throw new InvalidInputException("First name can't be empty");
        }
        if(StringUtils.isBlank(StringUtils.trimToEmpty(phone))) {
            throw new InvalidInputException("Phone number can't be empty");
        }
        if(phone.length() != 10) {
            throw new InvalidInputException("Contact needs to be of 10 characters");
        }
        if(StringUtils.isBlank(StringUtils.trimToEmpty(address))) {
            throw new InvalidInputException("Address can't be empty");
        }
    }

}
