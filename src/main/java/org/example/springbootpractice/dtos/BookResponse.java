package org.example.springbootpractice.dtos;

import lombok.Builder;

@Builder
public record BookResponse(String title, String author, int yearOfPublication) {

}
