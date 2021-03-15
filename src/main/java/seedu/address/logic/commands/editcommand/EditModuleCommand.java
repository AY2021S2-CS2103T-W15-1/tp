package seedu.address.logic.commands.editcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a module in RemindMe."
            + "Parameters: "
            + PREFIX_MODULE + "MODULE TITLE "
            + PREFIX_MODULE + "NEW MODULE TITLE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103 "
            + PREFIX_MODULE + "CS2103T";

    public static final String MESSAGE_SUCCESS = "Module added: %1$s";
    public static final String MESSAGE_NO_MODULE = "This module does not exists in RemindMe";

    private final Module toEdit;
    private final Title edit;

    /**
     * Creates an EditModuleCommand to edit the specified {@code Module}.
     */
    public EditModuleCommand(Module module, Title title) {
        requireNonNull(module);
        toEdit = module;
        edit = title;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(toEdit)) {
            throw new CommandException(MESSAGE_NO_MODULE);
        }

        model.editModule(toEdit, edit);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EditModuleCommand)
                && toEdit.equals(((EditModuleCommand) other).toEdit);
    }
}
