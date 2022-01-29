import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two-word command. It returns the command
 * as an object of class Command.
 *
 * The reader has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes (1.0). Modified by Leighton Burgoyne (2.0)
 * @version 29/02/2016 (1.0), 10/12/2021 (2.0)
 */
public class CommandReader
{
    private Game game;
    private Scanner reader; // source of command input

    private String commandWord = null;
    public String word2 = null;

    /**
     * Create a parser to read from the terminal window.
     */
    public CommandReader(Game game) 
    {
        this.game = game;
        reader = new Scanner(System.in);
    }
    
    /**
     * @return The next command from the user.
     */
    public boolean getCommand() 
    {
        String inputLine;  
        
        System.out.print(" > ");
        inputLine = reader.nextLine().toLowerCase();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        
        if(tokenizer.hasNext()) 
        {
            commandWord = tokenizer.next();      // get first word
        
            if(tokenizer.hasNext()) 
            {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
            else word2 = null;
        }

        return executeCommand();
    }

    private boolean executeCommand()
    {
        // Go Command
        if(commandWord.equals(CommandWords.GO.word))
        {
            GoCommand go = new GoCommand(game, word2);
            go.execute();
        }

        // Take Command
        else if(commandWord.equals(CommandWords.TAKE.word))
        {
            TakeCommand take = new TakeCommand(game, word2);
            take.execute();
        }

        // Help Command
        else if(commandWord.equals(CommandWords.HELP.word))
        {
            HelpCommand help = new HelpCommand(game);
            help.execute();
        }

        // Inventory Command
        else if(commandWord.equals(CommandWords.INVENTORY.word))
        {
            InventoryCommand inventory = new InventoryCommand(game);
            inventory.execute();
        }

        // Notepad Command
        else if(commandWord.equals(CommandWords.NOTEPAD.word))
        {
            NotepadCommand notepad = new NotepadCommand(game);
            notepad.execute();
        }

        // Unlock Command
        else if(commandWord.equals(CommandWords.UNLOCK.word))
        {
            UnlockCommand unlock = new UnlockCommand(game, word2);
            unlock.execute();
        }

        // Quit Command
        else if(commandWord.equals(CommandWords.QUIT.word))
        {
            return true;  // game over
        }
        else // Invalid Input (not a command listed above)
        {
            System.out.println("Error: Input not recognised!");
        }

        // Return false means the game is not over
        return false;
    }
}
