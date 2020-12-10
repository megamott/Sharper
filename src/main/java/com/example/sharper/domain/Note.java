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

    // Many notes belong one author
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Note(String tag, String text, User user) {
        this.tag = tag;
        this.text = text;
        this.author = user;
    }

    /**
     * For notes that was add before adding new author field in {@link Note}
     * @return
     */
    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
}
