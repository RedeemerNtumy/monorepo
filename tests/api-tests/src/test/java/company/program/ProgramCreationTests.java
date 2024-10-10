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
                .body("coverImageForProgram", equalTo("cover.jpg"))
                .body("draft",equalTo("true")).log().all()
                .extract().response();
    }

    @Test
    @Order(2)
    public void getProgramByIdSuccessTest() {
        int programId = 1;
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/" + programId)
                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(programId))
                .body("name", equalTo("Java Bootcamp"))
                .body("description", equalTo("A comprehensive bootcamp for learning Java"))
                ;
    }

    @Test
    @Order(3)
    public void updateProgramTest() {
        int programId = 1; // Use the ID of the program created in the first test
        String updatedProgramJson = "{\n" +
                "  \"name\": \"A Java Bootcamp Updated\",\n" +
                "  \"description\": \"An updated description\",\n" +
                "  \"requirement\": \"Intermediate programming knowledge\",\n" +
                "  \"requiredEarnedBadges\": \"Advanced Java\",\n" +
                "  \"additionalEarnedBadges\": \"Spring Boot\",\n" +
                "  \"dateOfCommencement\": \"2024-12-01\",\n" +
                "  \"dateOfCompletion\": \"2025-04-01\",\n" +
                "  \"status\": \"Closed\",\n" +
                "  \"coverImageForProgram\": \"updated_cover.jpg\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .queryParam("updatedBy", "TestUser")
                .body(updatedProgramJson)
                .when()
                .put("/" + programId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(programId))
                .body("name", equalTo("A Java Bootcamp Updated"))
                .body("description", equalTo("An updated description"))
                .body("requirement", equalTo("Intermediate programming knowledge"))
                .body("requiredEarnedBadges", equalTo("Advanced Java"))
                .body("additionalEarnedBadges", equalTo("Spring Boot"))
                .body("dateOfCommencement", equalTo("2024-12-01"))
                .body("dateOfCompletion", equalTo("2025-04-01"))
                .body("status", equalTo("Closed"))
                .body("coverImageForProgram", equalTo("updated_cover.jpg")).log().all();
    }

    @Test
    @Order(4)
    public void getProgramChangeLogsTest() {
        int programId = 1;
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/" + programId + "/changelogs")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()))
                .body("size()", greaterThanOrEqualTo(1))
                .body("[0].changedBy", equalTo("TestUser"))
                .body("[0].changeDescription", notNullValue()).log().all();
    }

    @Test
    @Order(5)
    public void getAllChangeLogsTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/changelogs")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()))
                .body("size()", greaterThanOrEqualTo(1)).log().all();
    }

    @Test
    @Order(6)
    public void getAllProgramsTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()))
                .body("size()", greaterThanOrEqualTo(1)).log().all();
    }

    @Test
    @Order(7)
    public void getAllDraftsTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/drafts")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("draft", everyItem(equalTo(true)))
                .log().all();
    }


    @Test
    @Order(8)
    public void publishProgramTest() {
        int programId = 1;

        // Publish the program
        given()
                .contentType(ContentType.JSON)
                .queryParam("publishedBy", "PublisherUser")
                .when()
                .post("/" + programId + "/publish")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(programId))
                .body("draft", equalTo(false));

        // Verify that the program is no longer in drafts
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/drafts")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("findAll { it.id == " + programId + " }", empty());
    }



    @Test
    @Order(9)
    public void deleteProgramTest() {
        int programId = 1;
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/" + programId)
                .then()
                .statusCode(200);

        // Verify that the program cannot be retrieved
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/" + programId)
                .then().log().all()
                .statusCode(404);
    }


}


