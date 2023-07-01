package dmf_demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class ReturnFieldTest {

    @Test
    void testCsvToList() throws IOException {
        Seq<Seq<String>> table = ReturnField.csvToList("input-tests/example/input2.csv");
        String expectedString = "[[netid, grade], [def456, junior], [ghi789, first-year], [abc123, senior]]";
        assertEquals(expectedString, table.toString());

        table = ReturnField.csvToList("tests/testCsvToList/non-rectangular.csv");
        expectedString = "[[1], [1, 2], [1, 2, 3], [1, , , 4], [1, , 3], [1, , ], [1]]";
        assertEquals(expectedString, table.toString());

        table = ReturnField.csvToList("tests/testCsvToList/empty.csv");
        expectedString = "[]";
        assertEquals(expectedString, table.toString());

        table = ReturnField.csvToList("tests/testCsvToList/no-cols.csv");
        expectedString = "[[], [], []]";
        assertEquals(expectedString, table.toString());
    }


    @Test
    void testSSNInfo() throws IOException {
        Seq<String> info = ReturnField.ssnInfo("input-tests/dmf/dmf1.csv","123456789");
        String expectedString = "[\"C\", \"123456789\", \"Daniels\", \"\", \"David\", \"Davis\", "
                + "\"V\", \"02/17/2020\", \"05/17/1954\"]";
        assertEquals(expectedString, info.toString());

        info = ReturnField.ssnInfo("input-tests/dmf/dmf1.csv","773688807");
        expectedString = "[\"A\", \"773688807\", \"Castillo\", \"Amira\", \"\", \"N\", "
                + "\"04/18/2023\", \"10/19/1961\"]";
        assertEquals(expectedString, info.toString());

        info = ReturnField.ssnInfo("input-tests/dmf/dmf1.csv","192891281");
        expectedString = "[\"D\", \"192891281\", \"Gill\", \"\", \"Knox\", \"Woods\", \"P\", "
                + "\"07/23/2019\", \"05/15/1981\"]";
        assertEquals(expectedString, info.toString());
    }


    @Test
    void testToJson() throws IOException {
        Seq<String> info = ReturnField.ssnInfo("input-tests/dmf/dmf1.csv","123456789");
        String json = ReturnField.toJson(info);
        String expectedString =
                "{\n"
                + "\"description\": \"C\",\n"
                + "\"ssn\": \"123456789\",\n"
                + "\"lastName\": \"Daniels\",\n"
                + "\"suffix\": \"\",\n"
                + "\"firstName\": \"David\",\n"
                + "\"middleName\": \"Davis\",\n"
                + "\"vCode\": \"V\",\n"
                + "\"dateOfDeath\": \"02/17/2020\",\n"
                + "\"dateOfBirth\": \"05/17/1954\"\n"
                + "}";
        assertEquals(expectedString, json);
    }


}