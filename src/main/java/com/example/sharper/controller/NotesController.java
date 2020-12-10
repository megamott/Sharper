package com.example.sharper.controller;

import com.example.sharper.domain.Note;
import com.example.sharper.domain.User;
import com.example.sharper.repos.NoteRepo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String showNotes(@AuthenticationPrincipal User user,
                            Model model){
        Iterable<Note> notes = noteRepo.findAll();

        model.addAttribute("user", user);
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
     * @param user author - security
     * @param tag
     * @param text
     * @return
     */
    @PostMapping("notes")
    public String addNote(@AuthenticationPrincipal User user,
                          @RequestParam(name = "tag") String tag,
                          @RequestParam(name = "text") String text){

        Note note = new Note(tag, text, user);

        noteRepo.save(note);

        return "redirect:/notes";

    }

}
