import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmployeeTest {

    private static long suiteStartTime;
    private long testStartTime;

    Employee employee = new Employee(4, "Alexander", "Rakhmanov", "RU", 44);

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running \"EmployeeTest\" for methods of class \"Employee\"");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("\"EmployeeTest\" complete: " + (System.nanoTime() - suiteStartTime));
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
    @DisplayName("Test \"Employee\" class constructor")
    public void validEmployeeTest(TestInfo validEmployeeInfo) {
        Assertions.assertNotNull(employee, validEmployeeInfo.getDisplayName() + " is NO complete!");
        System.out.println(validEmployeeInfo.getDisplayName() + " complete!");
    }

    @ParameterizedTest
    @DisplayName("Test \"getId()\" method")
    @ValueSource(longs = {-265, -2, 0, 26, 378})
    public void getIdParametrizedTest(long id) {
        employee.setId(id);
        Assertions.assertEquals(id, employee.getId(), " is NO complete!");
        System.out.println("Test \"getId(" + id + ")\" method complete!");
    }

    @Test
    @DisplayName("Test \"setId()\" method")
    public void setIdTest(TestInfo setIdInfo) {
        Assertions.assertEquals(4, employee.getId(), setIdInfo.getDisplayName() + " is NO complete!");
        System.out.println(setIdInfo.getDisplayName() + " complete!");
    }

    @Test
    @DisplayName("Test \"getFirstName()\" method")
    public void getFirstNameTest(TestInfo getFirstNameTestInfo) {
        Assertions.assertEquals("Alexander", employee.getFirstName(), getFirstNameTestInfo.getDisplayName() + " is NO complete!");
        System.out.println(getFirstNameTestInfo.getDisplayName() + " complete!");
    }

    @ParameterizedTest
    @DisplayName("Test \"setFirstName()\" method")
    @ValueSource(strings = {"Olga", "Sergey", "Elena", "Oleg"})
    public void setFirstNameParametrizedTest(String firstName) {
        employee.setFirstName(firstName);
        Assertions.assertEquals(firstName, employee.getFirstName());
        System.out.println("Test \"setFirstName(" + firstName + ")\" method complete!");
    }

    @ParameterizedTest
    @DisplayName("Test \"getLastName()\" method")
    @ValueSource(strings = {"Kiseleva", "Goryachev", "Smirnova", "Stupin"})
    public void getLastNameTest(String lastName) {
        employee.setLastName(lastName);
        Assertions.assertEquals(lastName, employee.getLastName());
        System.out.println("Test \"getLastName(" + lastName + ")\" method complete!");
    }

    @Test
    @DisplayName("Test \"setLastName()\" method")
    public void setLastNameParametrizedTest(TestInfo testInfo) {
        Assertions.assertEquals("Rakhmanov", employee.getLastName(), testInfo.getDisplayName() + " is NO complete!");
        System.out.println(testInfo.getDisplayName() + " complete!");
    }

    @Test
    @DisplayName("Test \"getCountry()\" method")
    public void getCountryTest(TestInfo testInfo) {
        Assertions.assertEquals("RU", employee.getCountry(), testInfo.getDisplayName() + " is NO complete!");
        System.out.println(testInfo.getDisplayName() + " complete!");
    }

    @ParameterizedTest
    @DisplayName("Test \"setCountry()\" method")
    @ValueSource(strings = {"FIN", "GB", "EST", "TR"})
    public void setCountryParameterizedTest(String country) {
        employee.setCountry(country);
        Assertions.assertEquals(country, employee.getCountry());
        System.out.println("Test \"setCountry(" + country + ")\" method complete!");
    }

    @Test
    @DisplayName("Test \"getAge()\" method")
    public void getAgeTest(TestInfo getAgeTestInfo) {
        Assertions.assertEquals(44, employee.getAge(), getAgeTestInfo.getDisplayName() + " is NO complete!");
        System.out.println(getAgeTestInfo.getDisplayName() + " complete!");
    }

    @ParameterizedTest
    @DisplayName("Test \"setAge()\" method for correct values")
    @ValueSource(ints = {45, 167, 16, 57})
    public void setAgeCorrectValuesParameterizedTest(int age) {
        employee.setAge(age);
        Assertions.assertEquals(age, employee.getAge());
        System.out.println("Test \"setAge(" + age + ")\" method complete!");
    }

    @ParameterizedTest
    @DisplayName("Test \"setAge()\" method for incorrect values")
    @ValueSource(ints = {-156, -5, 0, -89})
    void setAgeIncorrectValuesParameterizedTest(int age) {
        employee.setAge(age);
        Assertions.assertEquals("Incorrect value '" + age + "' of 'age' !", "Incorrect value '" + age + "' of 'age' !");
        System.out.println("Test \"setAge(" + age + ")\" method complete!");
    }

    @Test
    @DisplayName("Test \"toString()\" method")
    public void toStringTest(TestInfo toStringTestInfo) {
        String employeeToString = employee.toString();
        Assertions.assertEquals(employeeToString, employee.toString(), toStringTestInfo.getDisplayName() + " is NO complete!");
        System.out.println(toStringTestInfo.getDisplayName() + " complete!");
    }
}