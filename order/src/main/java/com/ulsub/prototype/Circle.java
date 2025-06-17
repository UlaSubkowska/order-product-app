package com.ulsub.prototype;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Circle implements Shape {

    private String color;

    public Circle(String color) {
        this.color = color;
    }
    @Override
    public Shape clone() {
        return new Circle(this.color);
    }

    @Override
    public void draw() {
        log.info("Drawing a "+ color + " circle.");
    }
}
