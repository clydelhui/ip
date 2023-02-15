package elems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ModifyCommand;
import commands.VoidCommand;
import dukeexceptions.IllegalCommandException;

/**
 * A Pasrser that parses String input from the user and
 * generates an appropriate <code>Command</code>
 * @author clydelhui
 */
public class Parser {

    private static final HashSet<String> addKeywords = new HashSet<>(Arrays.asList("todo",
            "deadline", "event"));
    private static final HashSet<String> deleteKeywords = new HashSet<>(Arrays.asList("delete"));
    private static final HashSet<String> voidKeywords = new HashSet<>(Arrays.asList("list", "bye", "forcequit"));
    private static final HashSet<String> modifyKeywords = new HashSet<>(Arrays.asList("mark", "unmark"));

    /**
     * Returns an appropriate <code>Command</code> given the user's <code>String</code> input
     * @param input The String input from the user
     * @return The <code>Command</code> associated with the given String input
     * @throws IllegalCommandException when an invalid input has been given
     */
    public Command parse(String input) throws IllegalCommandException {
        String[] commandSplit = input.split(" ", 2);
        String keyword = commandSplit[0];
        String paramChain = commandSplit.length == 1 ? "" : commandSplit[1];

        String[] parsedParams = paramChain.split("/");

        ArrayList<String> params = new ArrayList<>(Arrays.asList(parsedParams));

        Command parsedCommand;

        if (addKeywords.contains(keyword)) {
            parsedCommand = new AddCommand(keyword, params);
        } else if (deleteKeywords.contains(keyword)) {
            parsedCommand = new DeleteCommand(keyword, params);
        } else if (voidKeywords.contains(keyword)) {
            parsedCommand = new VoidCommand(keyword, params);
        } else if (modifyKeywords.contains(keyword)) {
            parsedCommand = new ModifyCommand(keyword, params);
        } else {
            throw new IllegalCommandException("You have entered an invalid command");
        }

        return parsedCommand;

    }
}
