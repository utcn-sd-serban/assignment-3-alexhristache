package ro.utcn.sd.alexh.assignment1.command;

import lombok.Data;
import org.springframework.stereotype.Component;
import ro.utcn.sd.alexh.assignment1.dto.DTO;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Invoker {

    private List<Command> commandHistory;

    public Invoker() {
        commandHistory = new ArrayList<>();
    }

    public DTO invoke(Command command) {
        commandHistory.add(command);
        return command.execute();
    }
}
