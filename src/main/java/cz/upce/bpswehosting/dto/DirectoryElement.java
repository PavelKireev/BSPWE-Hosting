package cz.upce.bpswehosting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DirectoryElement {
    private String name;
    private String type;
    private List<DirectoryElement> children;

    public DirectoryElement(String name, String type) {
        this.name = name;
        this.type = type;
        this.children = new ArrayList<>();
    }
}
