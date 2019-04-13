import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import restapi.ProjectApi;
import restapi.Spec;

import static org.hamcrest.core.IsEqual.equalTo;

class ProjectApiTest {

  private static Long inboxId = 2207635049l;

  @ParameterizedTest
  @ValueSource(strings = {"Test project 1", "Test project 2"})
  void createAProject(String projectName) {

    new ProjectApi(new Spec("beta.v8"))
            .createNewProject(projectName)
            .then()
            .statusCode(200)
            .body("name", equalTo(projectName))
    ;
  }

  @Test
  void getAProject() {
    new ProjectApi(new Spec("beta.v8")).getAProject(inboxId).then().statusCode(200);
  }

  @Test
  void getAllProject() {

    new ProjectApi(new Spec("beta.v8")).getAllProjects().then().statusCode(200);
  }

  @Test
  void renameAProject() {
    new ProjectApi(new Spec("beta.v8"))
            .updateAProject(inboxId, DataGeneratorHelper.generateNewName())
            .then()
            .statusCode(204);
  }

  @Test
  void deleteAProject() {
    Long id = new ProjectApi(new Spec("beta.v8"))
            .createNewProject("AutoTestProject")
            .then()
            .statusCode(200)
            .extract().path("id");

    new ProjectApi(new Spec("beta.v8"))
            .deleteAProject(id)
            .then()
            .statusCode(204);
  }

}
