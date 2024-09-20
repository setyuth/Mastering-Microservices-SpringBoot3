package com.setyuth.ecommerce.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
                              @NotNull(message = "Customer firstname is require")
                              String firstname,
                              @NotNull(message = "Customer lastname is require")
                              String lastname,
                              @NotNull(message = "Customer email is require")
                              @Email(message = "Customer email address is invalid")
                              String email,
                              Address address) {

}
