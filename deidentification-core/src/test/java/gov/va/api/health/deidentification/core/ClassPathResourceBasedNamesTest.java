package gov.va.api.health.deidentification.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ClassPathResourceBasedNamesTest {

  private List<String> testNames = List.of("Washington", "Adams", "Jefferson", "Monroe", "Madison");

  @Test
  void getName() {
    var actual = names();
    for (int i = 0; i < 5; i++) {
      assertThat(actual.getName(i)).isEqualTo(testNames.get(i));
    }
    assertThat(actual.getName(8)).isEqualTo("Monroe");
    assertThat(actual.getName(10)).isEqualTo("Washington");
  }

  @Test
  void loadList() {
    var instance = ClassPathResourceBasedNames.instance();
    for (int i = 0; i < 32549; i++) {
      assertThat(instance.getName(i)).isNotNull();
    }
  }

  ClassPathResourceBasedNames names() {
    return new ClassPathResourceBasedNames(testNames);
  }
}
