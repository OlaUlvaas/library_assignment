package se.lexicon.library.entity;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalDate;

@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name= "library_user_id")
    private LibraryUser loanTaker;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name= "book_id")
    private Book book;
    private LocalDate loanDay;
    @Column(name = "_terminated")
    private boolean terminated;




/*
    public Loan() {
    }

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDay, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDay = loanDay;
        this.terminated = terminated;
    }

    boolean extendLoan(int days){
        return false;
    }
    boolean isOverDue(){
        return false;
    }

    BigDecimal getFine(){
        return null;
    }

    public long getId() {
        return id;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDay() {
        return loanDay;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id && terminated == loan.terminated && Objects.equals(loanTaker, loan.loanTaker) && Objects.equals(book, loan.book) && Objects.equals(loanDay, loan.loanDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanTaker, book, loanDay, terminated);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDay=" + loanDay +
                ", terminated=" + terminated +
                '}';
    }*/
}
