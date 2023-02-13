package com.javawebservices.viktorvallmarkwebservice;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Random;

public class PdfPresenter {

    private Random random;

    public PdfPresenter() {
        super();
    }

    public int randomNumber()
    {
        random = new Random();
        int num = random.nextInt(2);
        return num;
    }

    public String getPathStringPdf()
    {
        int which = randomNumber();
        String pdf;
        if(which == 0)
        {
            pdf = "dummyWebservices.pdf";
        } else
        {
            pdf ="sampleWebservices.pdf";
        }
        return pdf;
    }

}
