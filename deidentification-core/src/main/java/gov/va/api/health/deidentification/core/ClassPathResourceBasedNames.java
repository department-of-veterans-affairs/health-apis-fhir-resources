package gov.va.api.health.deidentification.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * Using a class path resource as data, we provide a static set of names for the synthesis process
 * to choose from.
 *
 * <p>This resource is a large file, and {Resource}Deidentifiers are fleeting, 1 instance per
 * patient record. We create a static list of names, to prevent having to load this more than once.
 * Whenever a {@link SyntheticData} grabs a ClassPathResourceBasedNames instance, it will already
 * contain a reference to the loaded list of names.
 *
 * <p>We safely wrap the index of the list of names for ease of use. There could be some loss of
 * information in the wrapping, long -> int, but it does not matter in this case. We are only aiming
 * for repeatable access on the list with constant results.
 */
@AllArgsConstructor
public class ClassPathResourceBasedNames implements Names {

  private static final ClassPathResourceBasedNames FBN_INSTANCE =
      new ClassPathResourceBasedNames(loadList());

  List<String> names;

  public static ClassPathResourceBasedNames instance() {
    return FBN_INSTANCE;
  }

  @SneakyThrows
  private static List<String> loadList() {
    int namesCount = 32549;
    String sharedNamesClassPathResource = "yob2005.txt";
    List<String> list = new ArrayList<>(namesCount);
    BufferedReader reader =
        new BufferedReader(
            new InputStreamReader(
                ClassPathResourceBasedNames.class
                    .getClassLoader()
                    .getResourceAsStream(sharedNamesClassPathResource),
                StandardCharsets.UTF_8));
    reader.lines().forEach(line -> list.add(line.split(",", -1)[0]));
    reader.close();
    return Collections.unmodifiableList(list);
  }

  /** Safely handle wrapping any value into an array index. */
  @Override
  public String getName(long index) {
    return names.get((int) (index % names.size()));
  }
}
