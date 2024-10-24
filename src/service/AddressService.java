package service;

import dao.AddressDAO;
import model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    private static AddressDAO addressDAO;
    private static List<Address> addressCache; // Cache to store Address records

    static {
        addressDAO = AddressDAO.getInstance();
        addressCache = new ArrayList<>();
        loadAddresses(); // Load all Address records from the database into the cache on initialization
    }

    // Load the list of Address records from the database into the cache
    private static void loadAddresses() {
        addressCache = addressDAO.selectAll();
    }

    public int addAddress(Address address) {
        int result = addressDAO.insert(address);
        if (result > 0) {
            addressCache.add(address); // Update cache if insertion is successful
        }
        return result;
    }

    public int updateAddress(Address address) {
        int result = addressDAO.update(address);
        if (result > 0) {
            // Update the Address in the cache
            for (int i = 0; i < addressCache.size(); i++) {
                if (addressCache.get(i).getAddressID().equals(address.getAddressID())) {
                    addressCache.set(i, address);
                    break;
                }
            }
        }
        return result;
    }

    public int deleteAddress(String addressID) {
        Address address = new Address();
        address.setAddressID(addressID);
        int result = addressDAO.delete(address);
        if (result > 0) {
            // Remove Address from cache
            addressCache.removeIf(a -> a.getAddressID().equals(addressID));
        }
        return result;
    }

    public List<Address> getAllAddresses() {
        return new ArrayList<>(addressCache); // Return a copy of the list of Addresses
    }

    public Address getAddressById(String addressID) {
        // Find Address in the cache
        for (Address address : addressCache) {
            if (address.getAddressID().equals(addressID)) {
                return address;
            }
        }
        return null; // If not found
    }

    public List<Address> getAddressesByCondition(String condition) {
        // Filter Address records by condition in the cache
        List<Address> filteredAddresses = new ArrayList<>();
        for (Address address : addressCache) {
            // Add filtering logic here, e.g.:
            if (address.getCity().contains(condition) || address.getStreet().contains(condition)) {
                filteredAddresses.add(address);
            }
        }
        return filteredAddresses;
    }
}
