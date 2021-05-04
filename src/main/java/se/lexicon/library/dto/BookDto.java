package se.lexicon.library.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class BookDto {
    private int id;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;
}
