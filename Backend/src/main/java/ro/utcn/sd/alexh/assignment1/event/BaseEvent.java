package ro.utcn.sd.alexh.assignment1.event;

import lombok.Data;

@Data
public class BaseEvent {
    private final EventType type;
}
