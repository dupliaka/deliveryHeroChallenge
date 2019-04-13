import entity.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import restapi.Spec;
import restapi.TaskApi;

import static org.hamcrest.core.IsEqual.equalTo;

class TasksTest {
  static Long createdTestDataToModify;
  @BeforeEach
          public void before(){
    Task testTask = Task.builder().content("AutotestContent").build();
    createdTestDataToModify =  new TaskApi(new Spec("beta.v8"))
            .createNewTask(testTask)
            .then()
            .statusCode(200)
            .extract().path("id");
  }

  @ParameterizedTest
  @ValueSource(strings = {"Test task content 1", "Test task content 2"})
  void createATaskForProject(String taskContent) {
    Task testTask = Task.builder().content(taskContent).build();
    new TaskApi(new Spec("beta.v8"))
            .createNewTask(testTask)
            .then()
            .statusCode(200)
            .body("content", equalTo(taskContent))
            ;
  }

  @Test
  void updateATaskForProject() {
    String contentWithProjectName = DataGeneratorHelper.generateNewName();
    Task testTask = Task.builder().content(contentWithProjectName).build();

    new TaskApi(new Spec("beta.v8"))
            .updateATask(createdTestDataToModify, testTask)
            .then()
            .statusCode(204)
            .body("content", equalTo(contentWithProjectName))
    ;
  }

  @Test
  void closeATask() {

    new TaskApi(new Spec("beta.v8"))
            .closeATask(createdTestDataToModify)
            .then()
            .statusCode(204);
  }

  @Test
  void deleteATask() {

    new TaskApi(new Spec("beta.v8"))
            .deleteATask(createdTestDataToModify)
            .then();
  }

}
