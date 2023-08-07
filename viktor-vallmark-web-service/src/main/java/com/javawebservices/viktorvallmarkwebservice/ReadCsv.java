package com.javawebservices.viktorvallmarkwebservice;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.json.CDL;
import org.springframework.core.io.ClassPathResource;

public class ReadCsv {

    public ReadCsv() {
        super();
    }

    public String readFile(String fileName ) throws FileNotFoundException, IOException
    {
        ClassPathResource file = new ClassPathResource(fileName);
        StringBuilder result = new StringBuilder();

        try (Scanner myScanner = new Scanner(file.getFile()))
        {
            while(myScanner.hasNextLine())
            {
                result.append(processLine(myScanner.nextLine()));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result.toString();
    }
    private String processLine(String textLine)
    {
        StringBuilder result = new StringBuilder();

        Scanner rowScanner = new Scanner(textLine);
        rowScanner.useDelimiter(",");

        while (rowScanner.hasNext())
        {
            result.append(rowScanner.next()).append(" , ");
        }
        return result.toString();
    }
    public void csvToJson(String file) throws IOException {
        ClassPathResource csvFile = new ClassPathResource(file);
        InputStream inputStreamCsv = csvFile.getInputStream();
        String csvAsString = new BufferedReader(new InputStreamReader(inputStreamCsv)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();

        try {
            Files.writeString(Path.of("output.json"), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
