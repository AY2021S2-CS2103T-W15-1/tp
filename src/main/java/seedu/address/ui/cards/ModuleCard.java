package seedu.address.ui.cards;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ModuleCard extends UiPart<Region> {
    private static final String FXML = "ModuleListCard.fxml";
    private final Module module;
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label assignments;
    @FXML
    private Label exams;
    @FXML
    private Label tag;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module} and index to display.
     */
    public ModuleCard(Module module, int displayIndex) {
        super(FXML);
        requireNonNull(module);
        this.module = module;
        title.setWrapText(true);
        title.setMaxWidth(600);
        title.setText(displayIndex + ". " + module.getTitle().modTitle + ": ");
        assignments.setWrapText(true);
        assignments.setText(module.getAssignments().toString());
        assignments.setMaxWidth(300);
        exams.setWrapText(true);
        exams.setText(module.getExams().toString());
        exams.setMaxWidth(300);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return title.getText().equals(card.title.getText())
                && module.equals(card.module);
    }
}
