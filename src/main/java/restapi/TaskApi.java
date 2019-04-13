package restapi;

import entity.Task;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.hamcrest.Condition;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
@AllArgsConstructor
public class TaskApi {

  private static final String PATH_CREATE_NEW_TASK = "/tasks";
  private static final String PATH_UPDATE_TASK = "/tasks/{taskId}";
  private static final String PATH_CLOSE_TASK = "/tasks/{taskId}/close";
  private static final String PATH_DElETE_TASK = "/tasks";

  private Spec todoistRequesrSpecification;

  public Response createNewTask(Task task) {
    return given().spec(todoistRequesrSpecification.getRs())
            .contentType(ContentType.JSON)
            .body(task)
            .header("X-Request-Id", new Date().getTime())
            .post(PATH_CREATE_NEW_TASK);
  }

  public Response updateATask(Long taskId, Task task) {
    return given().spec(todoistRequesrSpecification.getRs())
            .pathParam("taskId", taskId)
            .contentType(ContentType.JSON)
            .body(task)
            .header("X-Request-Id", new Date().getTime())
            .post(PATH_UPDATE_TASK);
  }

  public Response closeATask(Long taskId) {
    return given().spec(todoistRequesrSpecification.getRs())
            .pathParam("taskId", taskId)
            .post(PATH_CLOSE_TASK);
  }

  public Response deleteATask(Long taskId) {
    return given().spec(todoistRequesrSpecification.getRs())
            .delete(PATH_DElETE_TASK);
  }
}
