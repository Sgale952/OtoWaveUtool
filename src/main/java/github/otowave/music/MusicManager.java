package github.otowave.music;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class MusicManager {
    static String getToggledGenre(ToggleGroup genreToggleGroup) {
        for(Toggle toggle : genreToggleGroup.getToggles()) {
            if (toggle.isSelected()) {
                RadioMenuItem item = (RadioMenuItem) toggle;
                return item.getText();
            }
        }
        //TODO: update status
        throw new NullPointerException("No genre selected");
    }
}
