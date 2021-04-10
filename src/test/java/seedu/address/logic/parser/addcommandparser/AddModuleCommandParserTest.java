package seedu.address.logic.parser.addcommandparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRemindMe.MOD_2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommand.AddModuleCommand;
import seedu.address.model.module.Title;


public class AddModuleCommandParserTest {
    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        String userInput = " " + PREFIX_MODULE + "MOD 2";
        assertParseSuccess(parser, userInput, new AddModuleCommand(MOD_2));
    }

    @Test
    public void parse_missingField_failure() {
        //missing module name
        String userInput = " " + PREFIX_MODULE;
        assertParseFailure(parser, userInput, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));

        //blank module name
        String userInput2 = " " + PREFIX_MODULE + "    ";
        assertParseFailure(parser, userInput2, String.format(Title.MESSAGE_CONSTRAINTS, "Modules"));
    }
}
