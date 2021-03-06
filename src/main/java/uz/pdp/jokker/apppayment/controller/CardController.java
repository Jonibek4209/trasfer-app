package uz.pdp.jokker.apppayment.controller;

import org.springframework.http.HttpStatus;
import uz.pdp.jokker.apppayment.payload.ApiResponse;
import uz.pdp.jokker.apppayment.payload.CardDto;
import uz.pdp.jokker.apppayment.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody CardDto dto, HttpServletRequest httpServletRequest) {
        ApiResponse response = cardService.add(dto, httpServletRequest);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CardDto cardDto, HttpServletRequest httpServletRequest) {
        ApiResponse response = cardService.edit(id, cardDto, httpServletRequest);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse response = cardService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response.getObject());
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse response = cardService.getAll();
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response.getObject());
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deledet(@PathVariable Integer id) {
        ApiResponse response = cardService.deleted(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }


}
