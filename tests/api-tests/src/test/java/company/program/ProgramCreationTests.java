package company.program;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProgramCreationTests {

    @BeforeAll
    public static void setup() {
        // Set the base URI for the API. Adjust the port and host as per your configuration.
        RestAssured.baseURI = "http://localhost:8888";
        RestAssured.basePath = "api/programs";
    }

    @Test
    @Order(1)
    public void createProgramTest() {
        // Define a JSON representation of the Program object
        String programJson = "{\n" +
                "  \"name\": \"Java Bootcamp\",\n" +
                "  \"description\": \"A comprehensive bootcamp for learning Java\",\n" +
                "  \"requirement\": \"Basic programming knowledge\",\n" +
                "  \"requiredEarnedBadges\": \"Java Basics, OOP\",\n" +
                "  \"additionalEarnedBadges\": \"Spring Framework\",\n" +
                "  \"dateOfCommencement\": \"2024-11-01\",\n" +
                "  \"dateOfCompletion\": \"2025-03-01\",\n" +
                "  \"status\": \"Open\",\n" +
                "  \"coverImageForProgram\": \"cover.jpg\"\n" +
                "}";

        // Execute the POST request and validate the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(programJson)
                .when()
                .post("/create")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Java Bootcamp"))
                .body("description", equalTo("A comprehensive bootcamp for learning Java"))
                .body("requirement", equalTo("Basic programming knowledge"))
                .body("requiredEarnedBadges", equalTo("Java Basics, OOP"))
                .body("additionalEarnedBadges", equalTo("Spring Framework"))
                .body("dateOfCommencement", equalTo("2024-11-01"))
                .body("dateOfCompletion", equalTo("2025-03-01"))
                .body("status", equalTo("Open"))
                .body("coverImageForProgram", equalTo("cover.jpg")).log().all()
                .extract().response();
    }

    @Test
    @Order(2)
    public void getProgramByIdSuccessTest() {
        int programId = 1;
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/" + programId) // Use the created program ID
                .then()
                .log().body() // Log the response for debugging
                .statusCode(200) // Assert that the status code is 200 OK
                .contentType(ContentType.JSON) // Assert that the response content type is JSON
                .body("id", equalTo(programId)) // Check that the returned program ID matches the requested ID
                .body("name", equalTo("Java Bootcamp")) // Check that the name field is correct
                .body("description", equalTo("A comprehensive bootcamp for learning Java")) // Check the description field
                ;
    }

    }


