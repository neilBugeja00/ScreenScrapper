package com.screenscrapper;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
)
public class TestRunner {
}
