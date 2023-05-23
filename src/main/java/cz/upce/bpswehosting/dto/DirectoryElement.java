package cz.upce.bpswehosting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DirectoryElement {
    private String name;
    private int type;
}
