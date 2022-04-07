import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainTest {

    private static long suiteStartTime;
    private long testStartTime;

    private String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
    private String csvFileName = "data.csv";
    private String csvJsonActualFileName = "data.json";
    private String csvJsonAdjustedFileName = "dataAdjusted.json";

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running \"MainTest\" for methods of class \"Main\"");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("The \"MainTest\" complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete: " + (System.nanoTime() - testStartTime));
    }

    @Test
    @DisplayName("Test \"parceCSV()\" static method")
    public void parseCSVTest(TestInfo parseCSVInfo) {

        List<Employee> expected = Arrays.asList(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Inav", "Petrov", "RU", 23)
        );

        List<Employee> actual = Main.parseCSV(columnMapping, csvFileName);

        Assertions.assertEquals(expected, actual, parseCSVInfo.getDisplayName() + " NO complete!");
        System.out.println(parseCSVInfo.getDisplayName() + " complete!");
    }

    @Test
    @DisplayName("Test \"listToJson()\" static method")
    void listToJsonTest(TestInfo listToJsonInfo) {

        String expected = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Inav\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";
        List<Employee> listFromCSV = Main.parseCSV(columnMapping, csvFileName);
        String actual = (String) Main.listToJson(listFromCSV);

        Assertions.assertEquals(expected, actual, listToJsonInfo.getDisplayName() + " NO complete!");
        System.out.println(listToJsonInfo.getDisplayName() + " complete!");
    }

    @Test
    @DisplayName("Test \"writeString()\" static method")
    public void writeStringTest(TestInfo writeStringInfo) {

        System.out.println("Starting csv -> json convertation...");

        List<Employee> listFromCSV = Main.parseCSV(columnMapping, csvFileName);
        String jsonFromCSV = (String) Main.listToJson(listFromCSV);
        Main.writeString(jsonFromCSV, csvJsonActualFileName);

        System.out.println("End csv -> json convertation");

        System.out.println("Reading \"" + csvJsonAdjustedFileName + "\" file...");

        String s;
        StringBuilder sbExpectedString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(csvJsonAdjustedFileName))) {
            while ((s = br.readLine()) != null) {
                sbExpectedString.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expected = sbExpectedString.toString();

        System.out.println("Reading \"" + csvJsonActualFileName + "\" file...");

        StringBuilder sbActualString = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(csvJsonActualFileName))) {
            while ((s = br.readLine()) != null) {
                sbActualString.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String actual = sbActualString.toString();

        Assertions.assertEquals(expected, actual, writeStringInfo.getDisplayName() + " NO complete!");
        System.out.println(writeStringInfo.getDisplayName() + " complete!");

    }


}