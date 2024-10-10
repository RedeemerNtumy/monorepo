package company.assessment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(OrderAnnotation.class)
public class AssessmentCreationTests {

    private static final Long quizId = 1L;
    private static final String COMPANY_NAME = "TestCompany";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8890;
    }

    @Test
    @Order(1)
    public void testCreateQuiz() {
        Map<String, Object> quiz = new HashMap<>();
        quiz.put("title", "Sample Quiz");
        quiz.put("skill", "Java");
        quiz.put("companyName", COMPANY_NAME);
        quiz.put("global", false);
        quiz.put("timeLimit", 60L);

        Map<String, Object> question = new HashMap<>();
        question.put("text", "What is Java?");
        question.put("options", Arrays.asList(
                "Programming Language",
                "Coffee",
                "Island",
                "All of the above"
        ));
        question.put("correctOptionIndex", 0);
        question.put("pointValue", 10L);  // Explicitly set as Long

        quiz.put("questions", Arrays.asList(question));

        Response response = given()
                .contentType("application/json")
                .body(quiz)
                .when()
                .post("/api/quizzes/create")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON).log().all()
                .body("id", notNullValue())
                .extract().response();
    }


    @Test
    @Order(2)
    public void testAddToGlobalRepository() {
        Map<String, Object> quiz = new HashMap<>();
        quiz.put("title", "Global Quiz");
        quiz.put("skill", "Python");
        quiz.put("companyName", "GlobalCompany");
        quiz.put("global", true);
        quiz.put("timeLimit", 45);

        Map<String, Object> question = new HashMap<>();
        question.put("text", "What is Python?");
        question.put("options", Arrays.asList(
                "Snake",
                "Programming Language",
                "Comedy Group",
                "All of the above"
        ));
        question.put("correctOptionIndex", 3);
        question.put("pointValue", 10);

        quiz.put("questions", Arrays.asList(question));

        given()
                .contentType("application/json")
                .body(quiz)
                .when()
                .post("/api/quizzes/global")
                .then()
                .statusCode(200)
                .body("global", equalTo(true))
                .body("title", equalTo("Global Quiz")).log().all();
    }

    @Test
    @Order(3)
    public void testGetGlobalQuizzes() {
        given()
                .when()
                .get("/api/quizzes/global")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].global", equalTo(true)).log().all();
    }

    @Test
    @Order(4)
    public void testGetCompanyQuizzes() {
        given()
                .when()
                .get("/api/quizzes/company/" + COMPANY_NAME)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].companyName", equalTo(COMPANY_NAME)).log().all();
    }

    @Test
    @Order(5)
    public void testUpdateQuiz() {
        Map<String, Object> updatedQuiz = new HashMap<>();
        updatedQuiz.put("title", "Updated Quiz Title");
        updatedQuiz.put("skill", "Java");
        updatedQuiz.put("companyName", COMPANY_NAME);
        updatedQuiz.put("global", false);
        updatedQuiz.put("timeLimit", 75);

        Map<String, Object> question = new HashMap<>();
        question.put("text", "What is JVM?");
        question.put("options", Arrays.asList(
                "Java Virtual Machine",
                "JavaScript Version Manager",
                "Just Very Massive",
                "None of the above"
        ));
        question.put("correctOptionIndex", 0);
        question.put("pointValue", 15);

        updatedQuiz.put("questions", Arrays.asList(question));

        given()
                .contentType("application/json")
                .header("Company-Name", COMPANY_NAME)
                .body(updatedQuiz)
                .when()
                .put("/api/quizzes/"+quizId )
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Quiz Title"))
                .body("timeLimit", equalTo(75)).log().all();
    }

    @Test
    @Order(6)
    public void testDeleteQuiz() {
        given()
                .header("Company-Name", COMPANY_NAME)
                .when()
                .delete("/api/quizzes/"+quizId)
                .then()
                .statusCode(204);
    }
}
