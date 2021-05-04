package se.lexicon.library.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoanDto {
    private long id;
    private LibraryUserDto loanTakerDto;
    private BookDto bookDto;
    private LocalDate loanDay;
    private boolean terminated;
}
