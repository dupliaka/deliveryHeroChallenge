package restapi;

import groovy.util.logging.Log4j;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static java.lang.ClassLoader.getSystemResourceAsStream;
@Getter
@Setter
public class Spec {

  private RequestSpecification rs;

  public Spec(String endpoint) {
    rs = given()
            .config(RestAssuredConfig.config()
                    .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())
                    .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
            )
            .urlEncodingEnabled(false)
            .header("Authorization", String.format("Bearer %s", ITConstants.getApiToken()))
            .baseUri(getBaseUri(endpoint));
  }

  private String getBaseUri(String endpoint) {

    Properties props = new Properties();
    try (InputStream is = getSystemResourceAsStream(ITConstants.getUriPath())) {
      props.load(is);
    } catch (IOException e) {
      throw new RuntimeException();
    }
    String uri = "";

    if (endpoint != null) {
      uri = props.getProperty(endpoint);
    }
    return uri;
  }
}
