package cz.upce.bpswehosting.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DirectoryElement {
    private String name;
    private String type;
    @JsonUnwrapped
    private List<DirectoryElement> children;

    public DirectoryElement(String name, String type) {
        this.name = name;
        this.type = type;
        this.children = new ArrayList<>();
    }
}
