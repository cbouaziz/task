package com.example.task.dto;

public class UserDto {

    private Long id;
    private String name;
    private boolean terminated;

    public UserDto(Long id, String name, boolean terminated) {
        this.id = id;
        this.name = name;
        this.terminated = terminated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}
