package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.module.TitleContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ModulePlanner modulePlanner;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyModulePlanner remindMeApp,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(remindMeApp, userPrefs);

        logger.fine("Initializing with RemindMe: " + remindMeApp + " and user prefs " + userPrefs);

        this.modulePlanner = new ModulePlanner(remindMeApp);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.modulePlanner.getPersonList());
        filteredModules = new FilteredList<>(this.modulePlanner.getModuleList());
    }

    public ModelManager() {
        this(new ModulePlanner(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }


    @Override
    public Path getRemindMeFilePath () {
        return userPrefs.getRemindMeFilePath();
    }

    @Override
    public void setRemindMeFilePath(Path remindMeFilePath) {
        requireNonNull(remindMeFilePath);
        userPrefs.setRemindMeFilePath(remindMeFilePath);
    }

    //=========== RemindMe Person ================================================================================


    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return modulePlanner.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        modulePlanner.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        modulePlanner.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        modulePlanner.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return modulePlanner.equals(other.modulePlanner)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredModules.equals(other.filteredModules);
    }

    //=========== RemindMe =============================================================

    @Override
    public void setRemindMe(ModulePlanner modulePlanner) {
        this.modulePlanner.resetData(modulePlanner);
    }

    @Override
    public ReadOnlyModulePlanner getRemindMe() {
        return modulePlanner;
    }

    @Override
    public void updateFilteredModuleList(TitleContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return modulePlanner.hasModule(module);
    }

    @Override
    public boolean hasModule(int index) {
        return modulePlanner.hasModule(index);
    }

    @Override
    public void addModule(Module module) {
        requireNonNull(module);
        modulePlanner.addModule(module);
    }

    @Override
    public void editModule(int index, Title title) {
        requireNonNull(title);
        modulePlanner.editModule(index, title);
    }

    @Override
    public boolean hasAssignment(Module module, Assignment assignment) {
        requireAllNonNull(module, assignment);
        return modulePlanner.hasAssignment(module, assignment);
    }

    @Override
    public void addAssignment(Module module, Assignment assignment) {
        requireAllNonNull(module, assignment);
        modulePlanner.addAssignment(module, assignment);
    }

    @Override
    public boolean hasExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        return modulePlanner.hasExam(module, exam);
    }

    @Override
    public void addExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        modulePlanner.addExam(module, exam);
    }
}
