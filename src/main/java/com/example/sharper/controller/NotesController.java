package com.example.sharper.controller;

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

    @GetMapping("/notes")
    public String showNotes(Model model){
        Iterable<Note> notes = noteRepo.findAll();

        model.addAttribute("notes", notes);

        return "main_page";
    }

    /**
     * Fetching notes by tag
     * @param filter
     * @param model
     * @return
     */
    @PostMapping("filter")
    public String filterNotes(@RequestParam(required = false, defaultValue = "") String filter,
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

    /**
     * Adding a note
     * @param tag
     * @param text
     * @return
     */
    @PostMapping("notes")
    public String addNote(@RequestParam(name = "tag") String tag,
                          @RequestParam(name = "text") String text){

        Note note = new Note(tag, text);

        noteRepo.save(note);

        return "redirect:/notes";

    }

}
