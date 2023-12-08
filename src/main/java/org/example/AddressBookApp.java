package org.example;

import org.apache.commons.lang3.StringUtils;
import org.example.exceptions.DuplicatePhoneException;
import org.example.exceptions.InvalidInputException;
import org.example.models.Contact;
import org.example.services.AddressBook;
import org.example.services.AddressBookImpl;
import org.example.utils.DataLoader;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;


@SuppressWarnings("java:S106")
public class AddressBookApp {

    private static final String CONTACTS_JSON = "contacts.json";

    public static void main(String[] args) {
        AddressBookApp addressBookApp = new AddressBookApp();
        addressBookApp.initiateAddressBook();
    }

    public void initiateAddressBook() {
        AddressBook addressBook = new AddressBookImpl();
        // for preloading some data from json file
        loadData(addressBook);
        Scanner scanner = new Scanner(System.in);
        String shouldContinue = "y";
        while (Objects.equals(shouldContinue, "y") || Objects.equals(shouldContinue, "Y")) {
            String choice;
            System.out.println("Select an option:");
            System.out.println("1. Add new contact");
            System.out.println("2. Search by name");
            System.out.println("3. Search by phone number");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addContact(scanner, addressBook);
                    break;
                case "2":
                    searchByName(scanner, addressBook);
                    break;
                case "3":
                    searchByContact(scanner, addressBook);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1/2/3");
                    break;
            }
            System.out.println("Should continue ? (y/n)");
            shouldContinue = scanner.nextLine();
        }
    }

    private void loadData(AddressBook addressBook) {
        List<Contact> contacts = DataLoader.loadData(CONTACTS_JSON, Contact.class) ;
        for(Contact c: contacts) {
            String firstName = c.getFirstName();
            String lastName = c.getLastName();
            String phone = c.getPhone();
            String address = c.getAddress();
            try {
                addressBook.addContact(firstName, lastName, phone, address);
            } catch (InvalidInputException | DuplicatePhoneException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addContact(Scanner scanner, AddressBook addressBook) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter phone number (10 characters): ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        try {
            addressBook.addContact(firstName, lastName, phone, address);
        } catch (InvalidInputException | DuplicatePhoneException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchByName(Scanner scanner, AddressBook addressBook) {
        System.out.println("Enter name to search for");
        String name = scanner.nextLine();
        if(StringUtils.isNotBlank(StringUtils.trimToEmpty(name))) {
            List<Contact> contacts = addressBook.searchContactByName(name);
            if(!contacts.isEmpty()) {
                System.out.println(contacts.size() + " contacts were found with given name");
                for (Contact c: contacts) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No contacts were found for the given name");
            }
        } else {
            System.out.println("Name cant be empty");
        }
    }

    private void searchByContact(Scanner scanner, AddressBook addressBook) {
        System.out.println("Enter phone number to search for");
        String phone = scanner.nextLine();
        if(StringUtils.isNotBlank(StringUtils.trimToEmpty(phone))) {
            Optional<Contact> contact = addressBook.searchByPhone(phone);
            if (contact.isPresent()) {
                System.out.println(contact.get());
            } else {
                System.out.println("No contact found with given phone number");
            }
        } else {
            System.out.println("Phone number cant be empty");
        }
    }

}
