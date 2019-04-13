package restapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
@AllArgsConstructor
public class ProjectApi {

  private static final String PATH_DELETE_A_PROJECT = "/projects/{projectId}";
  private static final String PATH_UPDATE_A_PROJECT = "/projects/{projectId}";
  private static final String PATH_GET_A_PROJECT = "/projects/{projectId}";
  private static final String PATH_GET_ALL_PROJECTS = "/projects";
  private static final String PATH_CREATE_NEW_PROJECTS = "/projects";

  private Spec todoistRequesrSpecification;

  public Response getAProject(Long projectId) {
    return given().spec(todoistRequesrSpecification.getRs())
            .pathParam("projectId", projectId)
            .get(PATH_GET_A_PROJECT);
  }

  public Response getAllProjects() {
    return given().spec(todoistRequesrSpecification.getRs())
            .get(PATH_GET_ALL_PROJECTS);
  }

  public Response createNewProject(String nameOfProject) {

    Map<String, Object> params = new HashMap<>();
    params.put("name", nameOfProject);

    return given().spec(todoistRequesrSpecification.getRs())
            .contentType(ContentType.JSON)
            .body(params)
            .header("X-Request-Id", new Date().getTime())
            .post(PATH_CREATE_NEW_PROJECTS);
  }

  public Response updateAProject(Long projectId, String reName) {

    Map<String, Object> params = new HashMap<>();
    params.put("name", reName);

    return given().spec(todoistRequesrSpecification.getRs())
            .contentType(ContentType.JSON)
            .body(params)
            .header("X-Request-Id", new Date().getTime())
            .pathParam("projectId", projectId)
            .post(PATH_UPDATE_A_PROJECT);
  }

  public Response deleteAProject(Long projectId) {
    return given().spec(todoistRequesrSpecification.getRs())
            .pathParam("projectId", projectId)
            .delete(PATH_DELETE_A_PROJECT);
  }
}

