package com.example.sharper.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tag;
    private String text;

    public Note(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }
}
