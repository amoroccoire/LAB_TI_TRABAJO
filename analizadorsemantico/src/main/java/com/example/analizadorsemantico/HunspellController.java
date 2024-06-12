package com.example.analizadorsemantico;

/**
 *
 * @author Usuario
 */
import com.atlascopco.hunspell.Hunspell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HunspellController {

    @Autowired
    private Hunspell hunspell;

    @GetMapping("/suggest")
    public List<String> getSuggestions(@RequestParam String word) {
        if (!hunspell.spell(word)) {
            return hunspell.suggest(word);
        } else {
            List<String> strings = new java.util.ArrayList<>();
            strings.add("La palabra está escrita correctamente.");
            return strings;
        }
    }
}

