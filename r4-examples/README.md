# r4-examples

Examples of r4 resources.

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
          <artifactId>r4-examples</artifactId>
          <version>${r4.version}</version>
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
