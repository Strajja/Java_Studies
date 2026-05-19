package com.strajja.training2.impl;

import com.strajja.training2.animals.Animals;
import com.strajja.training2.animals.Cat;
import com.strajja.training2.animals.Dog;
import com.strajja.training2.animals.Parrot;
import org.springframework.stereotype.Component;

@Component
public class AnimalsImpl implements Animals {

    private Cat cat;

    private Dog dog;

    private Parrot parrot;

    public AnimalsImpl(Cat cat, Dog dog, Parrot parrot) {
        this.cat = cat;
        this.dog = dog;
        this.parrot = parrot;
    }

    @Override
    public String AnimalSounds(){
        return "A cat said: "+cat.meow()+", dog said: "+dog.bark()+" and a parrot said: "+parrot.caw();
    }

}
