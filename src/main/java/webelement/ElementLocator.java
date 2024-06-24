package webelement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementLocator {
    private String locatorType;
    private String locatorValue;
    private String locatorName;
}