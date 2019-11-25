# argonaut-examples

Examples of argonaut resources.

## Example usage

The following pom.xml snippet shows the example library being used by swagger-examples-maven-plugin.

```
    <plugin>
      <groupId>gov.va.plugin.maven</groupId>
      <artifactId>swagger-examples-maven-plugin</artifactId>
      <version>1.0.1</version>
      <dependencies>
        <dependency>
          <groupId>gov.va.api.health</groupId>
          <artifactId>argonaut-examples</artifactId>
          <version>${argonaut.version}</version>
          <scope>runtime</scope>
        </dependency>
      </dependencies>
      <executions>
        <execution>
          <id>inject-examples</id>
          <phase>compile</phase>
          <goals>
            <goal>inject</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
```
