package com.strajja.database3.controllers;


import com.strajja.database3.domain.dto.AuthorDto;
import com.strajja.database3.domain.entities.AuthorEntity;
import com.strajja.database3.mappers.Mapper;
import com.strajja.database3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {


    private AuthorService authorService;

    private Mapper<AuthorEntity, AuthorDto> authorMapper;


    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(path="/authors")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author){

        AuthorEntity authorEntity=authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity=authorService.createAuthor(authorEntity);
        return new ResponseEntity<>( authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);


    }

    @GetMapping(path="/authors")
    public List<AuthorDto> getAllAuthors(){

        List<AuthorEntity> authors=authorService.findAll();
        return authors.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path="/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id){
      Optional <AuthorEntity> foundAuthor=authorService.findOne(id);
      return foundAuthor.map(authorEntity -> {
         AuthorDto authorDto=authorMapper.mapTo(authorEntity);
         return new ResponseEntity<>(authorDto, HttpStatus.OK);
      }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
