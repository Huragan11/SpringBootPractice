package org.example.springbootpractice.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record BookRequest(@NotEmpty @Size(min = 2, max = 30) String title, String author, int yearOfPublication) {

}
