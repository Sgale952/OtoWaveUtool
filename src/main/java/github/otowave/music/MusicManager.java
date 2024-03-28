package github.otowave.music;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class MusicManager {
    static String getToggledGenre(ToggleGroup genreToggleGroup) throws Exception {
        for(Toggle toggle : genreToggleGroup.getToggles()) {
            if (toggle.isSelected()) {
                RadioMenuItem item = (RadioMenuItem) toggle;
                return item.getText();
            }
        }

        throw new Exception("No genre selected");
    }
}
