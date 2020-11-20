package gov.va.api.health.fhir.api;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.Collection;

/** Something with a display value, like a reference or coding. */
public interface HasDisplay {

  /**
   * Choose any display value if possible. The "first choice" will be used if not blank, otherwise
   * the first usable display in the list of "other choices".
   */
  static String anyDisplay(String firstChoice, Collection<? extends HasDisplay> otherChoices) {
    if (isNotBlank(firstChoice)) {
      return firstChoice;
    }
    if (otherChoices == null || otherChoices.isEmpty()) {
      return null;
    }
    for (var choice : otherChoices) {
      if (isNotBlank(choice.display())) {
        return choice.display();
      }
    }
    return null;
  }

  /** Return the display value for this object. This is human friendly representation. */
  String display();
}
