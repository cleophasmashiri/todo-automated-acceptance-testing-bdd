# todo-automated-acceptance-testing-bdd
A recipe for Automated-Acceptance-Testing with Cucumber and Serenity with feature files

Steps for quickly setting up automated-acceptance-testing with cucumber, selenium and serenity.


1. Setup a Maven project, add the following to your pom.

```
<properties>
 			<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
 			<serenity.version>1.8.3</serenity.version>
 			<serenity.maven.version>1.8.3</serenity.maven.version>
 			<serenity.cucumber.version>1.6.6</serenity.cucumber.version>
 			<parallel.tests>4</parallel.tests>
 			<webdriver.base.url>http://todomvc.com/examples/angularjs/#/</webdriver.base.url>
 			<encoding>UTF-8</encoding>
 			<serenity.test.root></serenity.test.root>
 			<tags></tags>
 			<webdriver.driver>firefox</webdriver.driver>
 		</properties>

 		<repositories>
 			<repository>
 				<snapshots>
 					<enabled>false</enabled>
 				</snapshots>
 				<id>central</id>
 				<name>bintray</name>
 				<url>http://jcenter.bintray.com</url>
 			</repository>
 		</repositories>
 		<pluginRepositories>
 			<pluginRepository>
 				<snapshots>
 					<enabled>false</enabled>
 				</snapshots>
 				<id>central</id>
 				<name>bintray-plugins</name>
 				<url>http://jcenter.bintray.com</url>
 			</pluginRepository>
 		</pluginRepositories>

 		<dependencies>
 			<dependency>
 				<groupId>net.serenity-bdd</groupId>
 				<artifactId>serenity-core</artifactId>
 				<version>${serenity.version}</version>
 			</dependency>
 			<dependency>
 				<groupId>net.serenity-bdd</groupId>
 				<artifactId>serenity-junit</artifactId>
 				<version>${serenity.version}</version>
 			</dependency>
 			<dependency>
 				<groupId>net.serenity-bdd</groupId>
 				<artifactId>serenity-screenplay</artifactId>
 				<version>${serenity.version}</version>
 			</dependency>
 			<dependency>
 				<groupId>net.serenity-bdd</groupId>
 				<artifactId>serenity-screenplay-webdriver</artifactId>
 				<version>${serenity.version}</version>
 			</dependency>
 			<dependency>
 				<groupId>net.serenity-bdd</groupId>
 				<artifactId>serenity-cucumber</artifactId>
 				<version>${serenity.cucumber.version}</version>
 			</dependency>
 			<dependency>
 				<groupId>org.slf4j</groupId>
 				<artifactId>slf4j-simple</artifactId>
 				<version>1.7.7</version>
 			</dependency>
 			<dependency>
 				<groupId>junit</groupId>
 				<artifactId>junit</artifactId>
 				<version>4.12</version>
 				<scope>test</scope>
 			</dependency>
 			<dependency>
 				<groupId>org.assertj</groupId>
 				<artifactId>assertj-core</artifactId>
 				<version>3.6.2</version>
 			</dependency>
 			<dependency>
 				<groupId>com.googlecode.lambdaj</groupId>
 				<artifactId>lambdaj</artifactId>
 				<version>2.3.3</version>
 			</dependency>
 			<dependency>
 				<groupId>org.hamcrest</groupId>
 				<artifactId>hamcrest-all</artifactId>
 				<version>1.3</version>
 			</dependency>
 		</dependencies>
 		<build>
 			<plugins>
 				<plugin>
 					<groupId>org.apache.maven.plugins</groupId>
 					<artifactId>maven-surefire-plugin</artifactId>
 					<version>2.18.1</version>
 					<configuration>
 						<skip>true</skip>
 					</configuration>
 				</plugin>
 				<plugin>
 					<artifactId>maven-failsafe-plugin</artifactId>
 					<version>2.18.1</version>
 					<configuration>
 						<includes>
 							<include>**/features/**/*.java</include>
 							<include>**/jbehave/**/*.java</include>
 							<include>**/cucumber/*.java</include>
 						</includes>
 						<systemPropertyVariables>
 							<webdriver.base.url>${webdriver.base.url}</webdriver.base.url>
 						</systemPropertyVariables>
 						<excludes>
 							<exclude>**/*WithFailures.java</exclude>
 						</excludes>
 						<parallel>classes</parallel>
 						<threadCount>${parallel.tests}</threadCount>
 						<forkCount>${parallel.tests}</forkCount>
 					</configuration>
 					<executions>
 						<execution>
 							<goals>
 								<goal>integration-test</goal>
 								<goal>verify</goal>
 							</goals>
 						</execution>
 					</executions>
 				</plugin>
 				<plugin>
 					<groupId>org.apache.maven.plugins</groupId>
 					<artifactId>maven-compiler-plugin</artifactId>
 					<version>3.2</version>
 					<configuration>
 						<source>1.8</source>
 						<target>1.8</target>
 					</configuration>
 				</plugin>
 				<plugin>
 					<groupId>net.serenity-bdd.maven.plugins</groupId>
 					<artifactId>serenity-maven-plugin</artifactId>
 					<version>${serenity.maven.version}</version>
 					<configuration>
 						<tags>${tags}</tags>
 					</configuration>
 					<executions>
 						<execution>
 							<id>serenity-reports</id>
 							<phase>post-integration-test</phase>
 							<goals>
 								<goal>aggregate</goal>
 							</goals>
 						</execution>
 					</executions>
 				</plugin>
 			</plugins>
 		</build>
 		<profiles>
 			<profile>
 				<id>failing</id>
 				<build>
 					<plugins>
 						<plugin>
 							<artifactId>maven-failsafe-plugin</artifactId>
 							<version>2.18.1</version>
 							<configuration>
 								<includes>
 									<include>**/*WithFailures.java</include>
 								</includes>
 							</configuration>
 							<executions>
 								<execution>
 									<goals>
 										<goal>integration-test</goal>
 										<goal>verify</goal>
 									</goals>
 								</execution>
 							</executions>
 						</plugin>
 					</plugins>
 				</build>
 			</profile>
 			<profile>
 				<id>cucumber</id>
 				<activation>
 					<activeByDefault>true</activeByDefault>
 				</activation>
 				<build>
 					<plugins>
 						<plugin>
 							<artifactId>maven-failsafe-plugin</artifactId>
 							<version>2.18.1</version>
 							<configuration>
 								<includes>
 									<include>**/cucumber/*.java</include>
 								</includes>
 							</configuration>
 							<executions>
 								<execution>
 									<goals>
 										<goal>integration-test</goal>
 										<goal>verify</goal>
 									</goals>
 								</execution>
 							</executions>
 						</plugin>
 					</plugins>
 				</build>
 			</profile>
 		</profiles>
```

2. Add serenity.properties to root as:

```
serenity.test.root = net.serenitybdd.demos.todos.screenplay.features
  #serenity.linked.tags = issue
  #webdriver.base.url=http://localhost:8080/examples
  #simplified.stack.traces=false
  #serenity.use.unique.browser=true
  #my.property=foo
  #thucydides.activate.highlighting=true
  #browserstack.browserName=firefox
  #serenity.take.screenshots=FOR_FAILURES
  #webdriver.wait.for.timeout=20000
  #serenity.browser.width=400
  #serenity.browser.height=400
  #enable.markdown=story,narrative,scenario,step
  serenity.browser.maximized = true
  #serenity.issue.tracker.url = http://my.issue.tracker/MY-PROJECT/browse/{0}
  #gecko.firefox.options={"binary": "/Applications/Firefox-49.1.app/Contents/MacOS/firefox-bin","log":{"level":"debug"}}
```

3 Add serenity.conf to src/test/resources, and the following:

```
webdriver {
   base.url = "http://todomvc.com/examples/angularjs/#/"
   driver = firefox
 }
 firefox {
   preferences = "browser.download.dir=/tmp;browser.helperApps.alwaysAsk.force=false;browser.download.manager.showWhenStarting=false;browser.download.useDownloadDir=true;browser.download.folderList=2;"
 }
 chrome.switches=--headless
 serenity {
   project.name = "Demo Project using Serenity and Cucumber"
   take.screenshots = BEFORE_AND_AFTER_EACH_STEP
   test.root = "net.serenitybdd.demos.todos.features"
   tag.failures = "true"
   linked.tags = "issue"
   #  logging = "VERBOSE"
   restart.browser.for.each = scenario
 }
 ```

 4. Add cucumber test runner as RecordTodo.java to src/test, under package 'com.demo.todos'

```
@RunWith(CucumberWithSerenity.class)
   @CucumberOptions(features = "src/test/resources/features/record_todos")
   public class RecordTodos {}
```

5. Add feature file add_new_items_to_the_todo_list.feature to src/test/resources/features/record_todos.

```
@cucumber
@adding
Feature: Add new todos

  In order to avoid having to remember things that need doing
  As a forgetful person
  I want to be able to record what I need to do in a place where I won't forget about them

  Scenario: Adding an item to an empty list in Cucumber
    Given that James has an empty todo list
    When he adds 'Buy some milk' to his list
    Then 'Buy some milk' should be recorded in his list

  Scenario: Adding an item to a list with other items in Cucumber
    Given that Jane has a todo list containing Buy some milk, Walk the dog
    When she adds 'Buy some cereal' to her list
    Then her todo list should contain Buy some milk, Walk the dog, Buy some cereal

  Scenario: Adding items to several peoples lists in Cucumber
    Given that James has a todo list containing Buy some milk, Walk the dog
    And that Jill has a todo list containing Buy some milk, Buy some cheese
    When she adds 'Buy some cereal' to her list
    Then Jill's todo list should contain Buy some milk, Buy some cheese, Buy some cereal
    And James's todo list should contain Buy some milk, Walk the dog

```


6. Run mvn verify, you should see the following:

```
INFO] --- serenity-maven-plugin:1.8.3:aggregate (serenity-reports) @ todo-automated-acceptance-testing-bdd ---
[INFO] current_project.base.dir: /Users/cleophas/my/apps/cucumber-apps/todo-automated-acceptance-testing-bdd-master
[INFO] Generating test results for 0 tests
[INFO] 2 requirements loaded after 280 ms
[INFO] 2 related requirements found after 280 ms
[INFO] Generating test outcome reports: false
[INFO] Starting generating reports: 297 ms
[INFO] Configured report threads: 40
[INFO] Test results for 0 tests generated in 783 ms

```

7. Generate the steps as:
   - Add TodoUserSteps.java to src/test/steps.
   - Generate the steps by navigating to add_new_items_to_the_todo_list.feature


8. Make sure your pom build-profile config's `<include>**/cucumber/*.java</include>` matches your package that contains your cucumber test runner:

```
package com.demo.todos.cucumber;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by cleophas on 2018/07/24.
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/record_todos")
public class RecordTodos {
}
```

 ```
 <profile>
 				<id>cucumber</id>
 				<activation>
 					<activeByDefault>true</activeByDefault>
 				</activation>
 				<build>
 					<plugins>
 						<plugin>
 							<artifactId>maven-failsafe-plugin</artifactId>
 							<version>2.18.1</version>
 							<configuration>
 								<includes>
 									<include>**/cucumber/*.java</include>
 								</includes>
 							</configuration>
 							<executions>
 								<execution>
 									<goals>
 										<goal>integration-test</goal>
 										<goal>verify</goal>
 									</goals>
 								</execution>
 							</executions>
 						</plugin>
 					</plugins>
 				</build>
 			</profile>
 ```



