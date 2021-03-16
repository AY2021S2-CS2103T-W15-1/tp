package seedu.address.logic.parser.editcommandparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.editcommand.EditCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments, identifies which Edit Command it refers to and
 * creates the corresponding Edit Command.
 */
public class EditCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of an Edit Command
     * and returns a Command object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String args) throws ParseException {
        requireNonNull(args);
        Command command;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME, PREFIX_TAG);

        if (editModuleCondition(argMultimap)) {
            command = new EditModuleCommandParser().parse(args);
        } else if (editPersonCondition(argMultimap)) {
            command = new EditPersonCommandParser().parse(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCommand.MESSAGE_USAGE));
        }
        return command;
    }

    private boolean editModuleCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && !argMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argMultimap, PREFIX_NAME);
    }

    private boolean editPersonCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_NAME)
                && !argMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argMultimap, PREFIX_MODULE);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    protected static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

