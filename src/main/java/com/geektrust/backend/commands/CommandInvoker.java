package com.geektrust.backend.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.exceptions.NoSuchCommandException;

public class CommandInvoker {
    private final Map<String, ICommand> commandMap = new HashMap<>();

    // Registering the command into the HashMap
    public void register(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }

    // Getting the registered Command
    private ICommand get(String commandName){
        return commandMap.get(commandName);
    }

    // Executing the registered Command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        ICommand command = get(commandName);
        if(command == null){
            // Handling Exception
            throw new NoSuchCommandException("No such command: " + commandName);
        }
        command.execute(tokens);
    }
}
