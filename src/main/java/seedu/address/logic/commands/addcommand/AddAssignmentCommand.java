package seedu.address.logic.commands.addcommand;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Module;

public class AddAssignmentCommand extends AddCommand {

    public static final String MESSAGE_USAGE =
            "Missing necessary prefixes: m/, a/, and by/\n"
            + "Assignment: add m/TITLE a/DESCRIPTION by/DEADLINE\n"
            + "Example: add m/MOD1 a/ASSGN1 by/03/02/2021 2359";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the module";
    public static final String MESSAGE_MODULE_NOT_FOUND = "Module for assignment has to be "
            + "created first";

    private final Module target;
    private final Assignment toAdd;

    /**
     * Creates an AddPersonCommand to add the specified {@code Person}
     */
    public AddAssignmentCommand(Module module, Assignment assignment) {
        requireNonNull(assignment);
        target = module;
        toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModule(target)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }
        if (model.hasAssignment(target, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(target, toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAssignmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAssignmentCommand) other).toAdd));
    }
}
