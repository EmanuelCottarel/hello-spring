package fr.diginamic.hello.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public abstract class AbstractCRUDController<T> {

    protected abstract String getPath();

    @PostMapping
    public T create(){
        return null;
    }

    @PutMapping
    public T update(){
        return null;
    }

    @DeleteMapping
    public T delete(){
        return null;
    }

    @GetMapping("/{id}")
    public T find(int id){
        return null;
    }

    @GetMapping
    public List<T> getAll(){
        return null;
    }


}
