package se.lexicon.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.library.dto.LoanDto;
import se.lexicon.library.exception.DataNotFoundException;
import se.lexicon.library.service.LoanService;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/v1/loan")
public class LoanController {



    LoanService loanService;


    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }




    @GetMapping("/")
    public ResponseEntity<List<LoanDto>> findAll(){
        return ResponseEntity.ok(loanService.findAll());
    }



    @GetMapping("/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable("id")Long id) throws DataNotFoundException {
        if (id==null && id<1) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(loanService.findById(id));
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<List<LoanDto>> findByBookId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(loanService.findByBookId(id));
    }


    @GetMapping("/loanTaker/{id}")
    public ResponseEntity<List<LoanDto>> findByUserId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(loanService.findByUserId(id));
    }



    @Transactional
    @PostMapping("/")
    public ResponseEntity<LoanDto> save(@RequestBody LoanDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.create(dto));
    }


    @Transactional
    @PutMapping("/")
    public ResponseEntity<LoanDto> update(@RequestBody LoanDto dto) throws DataNotFoundException {
        return ResponseEntity.ok(loanService.update(dto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long loanId) throws DataNotFoundException {
        loanService.delete(loanId);
        return ResponseEntity.ok().build();

    }


}
