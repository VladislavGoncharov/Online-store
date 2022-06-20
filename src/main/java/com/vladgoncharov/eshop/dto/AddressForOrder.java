package com.vladgoncharov.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressForOrder {
    String town = "";
    String street = "";
    String house = "";
    String flat = "";

    public String fullAddress() {
        if (town.length() > 0 && street.isEmpty() && house.isEmpty() && flat.isEmpty())
            return town;
        return "г." + uppercaseForFirstCharacter(town) +
                " ул." + uppercaseForFirstCharacter(street) +
                " " + house +
                " кв. " + flat;
    }

    private String uppercaseForFirstCharacter(String word) {
        if (word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
