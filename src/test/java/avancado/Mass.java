package avancado;


import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class) // Class is executed with parameters
public class Mass {
    public String id;
    public String num1;
    public String operator;
    public String num2;
    public String expectedResult;
    public String deviceName;

    public Mass(String id, String num1, String operator, String num2, String expectedResult, String deviceName) {
        this.id = id;
        this.num1 = num1;
        this.operator = operator;
        this.num2 = num2;
        this.expectedResult = expectedResult;
        this.deviceName = deviceName;
    }

    @Parameterized.Parameters
    public static Collection<String[]>readCSV(String massName) throws IOException {
        List<String[]> data = new ArrayList<String[]>();
        String line;
        BufferedReader file = new BufferedReader(new FileReader(massName));
        while ((line = file.readLine()) != null){
            String fields[] = line.split(";");
            data.add(fields);
        }
        file.close();

    return data;
}

}
