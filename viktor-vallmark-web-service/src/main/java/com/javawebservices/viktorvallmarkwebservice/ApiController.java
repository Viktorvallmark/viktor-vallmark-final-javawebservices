package com.javawebservices.viktorvallmarkwebservice;

import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class ApiController implements ErrorController {
    private GuessNumber guessNumber;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String root() {
        String index = "";

        index += "<html><head><title>This is my api</title></head>";
        index += "<body>";
        index += "<h1>This is my API!</h1>";
        index += "<p>This API contains three services</p>";
        index += "<p> <a href=\"http://localhost:8080/pdf\" >Pdf presenter shows a pdf document.</a> This shows a random PDF every time you refresh the page. [GET]</p>";
        index += "<p> <a href=\"http://localhost:8080/csvToJson\" >A csv -> JSON converter.</a> This converts a csv-file into JSON.[GET] </p>";
        index += "<p> <a href=\"http://localhost:8080/guessnumber\" >A basic number guessing game played against an computer opponent.</a> This game keeps records of how many guesses you made in order to guess the" +
                " correct number. [POST]</p>";
        index += "</body></html>";
        return index;
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public void presentPDF(HttpServletResponse resp) throws IOException
    {
        PdfPresenter presenter = new PdfPresenter();
        ClassPathResource pdfPath = new ClassPathResource(presenter.getPathStringPdf());
        resp.setContentType(MediaType.APPLICATION_PDF_VALUE);
        StreamUtils.copy(pdfPath.getInputStream(), resp.getOutputStream());
    }

    @GetMapping(value = "/guessnumber", produces = "text/html")
    public String numberGameLanding()
    {
        guessNumber = new GuessNumber();



        return "<html><head><title>Guess the number!</title></head>" +
                "<body><h3>Rules: Chose a number between 1-100 and the computer will tell you if it's correct " +
                "or lower/over. When you guess the correct number, the computer will tell you how many tries " +
                "it took to get the correct number!</h3>" +
                "<h4>Please enter your name and your first guess down below</h4>"+
                "<form action=\"http://localhost:8080/playgame\" method=\"POST\">" +
                "Name: <input type=\"text\" id=\"1\" name=\"name\">" +
                "<div>"+"<div>"+"Guess: <input type=\"number\" id=\"2\" name=\"guess\" min=\"1\" max=\"100\">" +
                "<div><input type=\"submit\" id=\"3\" name=\"ok\"></form></body></html>";
    }

    @PostMapping(value = "/playgame")
    public String playGuessGame(@RequestParam(required = false) String name, @RequestParam int guess){

        String kek = guessNumber.playGameGuess(guess);


        return "<html><body><h1>Hello, " + guessNumber.getName()+
                "</h1>"+
                "<p>"+kek+"</p>" +
                "<p><form action =\"http://localhost:8080/playgame\" method =\"POST\">"+
                "<div>Guess: <input type=\"number\" name=\"guess\" min=\"1\" max=\"100\"><div><div>" +
                "<input type=\"submit\" name=\"ok\"></p><p><a href=\"http://localhost:8080/\" Main page>Return to main page.</body></html>";
    }

    @GetMapping(value = "/csvToJson", produces = MediaType.TEXT_HTML_VALUE)
    public String csvToJsonLanding()
    {
        return "<html><head><title>Csv to JSON converter.</title></head>" +
                "<body><h3>Hello there!</h3><p><a href=\"http://localhost:8080/showCsvFile\" >Click here to show the original csv-file!</a></p>" +
                "<p><a href=\"http://localhost:8080/showJsonFile\" > Click here to convert the original csv-file into JSON format!</a></p>" +
                "</body></html>";
    }

    @RequestMapping(
            value = "/showCsvFile",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public void showCsvFile(HttpServletResponse response) throws IOException {
        ClassPathResource csvFile = new ClassPathResource("sample.csv");

        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        StreamUtils.copy(csvFile.getInputStream(), response.getOutputStream());
    }

    @RequestMapping(
            value = "/showJsonFile",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void showJsonFile(HttpServletResponse response) throws IOException
    {
        ReadCsv myReader = new ReadCsv();
        ClassPathResource jsonFile = new ClassPathResource("output.json");
        myReader.csvToJson("sample.csv");


        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        StreamUtils.copy(jsonFile.getInputStream(), response.getOutputStream());
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String errorPage(@NotNull HttpServletResponse response)
    {
        return "<p>Something went wrong! Please go to <a href=\"http://localhost:8080/\"> main page</a> and try again.</p>"
                +"<p>HTTP error was: "+response.getStatus()+"</p>";
    }
}
