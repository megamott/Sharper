package com.example.sharper.controllers;

import com.example.sharper.domain.Note;
import com.example.sharper.repos.NoteRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotesController {

    private final NoteRepo noteRepo;

    public NotesController(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping("/")
    public String showNotes(Model model){
        Iterable<Note> notes = noteRepo.findAll();

        model.addAttribute("notes", notes);

        return "main_page";
    }

    @GetMapping("/filter")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        Iterable<Note> notes = noteRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            notes = noteRepo.findByTag(filter);
        } else {
            notes = noteRepo.findAll();
        }

        model.addAttribute("notes", notes);

        return "main_page";
    }

    @PostMapping("/add-note")
    public String addNote(@RequestParam(name = "tag") String tag,
                          @RequestParam(name = "text") String text){

        Note note = new Note(tag, text);

        noteRepo.save(note);

        return "redirect:/";

    }

}
