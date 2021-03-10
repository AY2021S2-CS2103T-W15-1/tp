package seedu.address.logic.commands.addcommand;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

public class AddModuleCommand extends Command {

    public static final String COMMNAD_WORD = "add";

    public static final String MESSAGE_USAGE = COMMNAD_WORD + ": Adds a module to RemindMe."
            + "Parameters: "
            + PREFIX_MODULE + "MODULE TITLE\n"
            + "Example: " + COMMNAD_WORD + " "
            + PREFIX_MODULE + "CS2103";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in RemindMe";

    private final Module toAdd;

    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddModuleCommand)
                && toAdd.equals(((AddModuleCommand) other).toAdd);
    }
}