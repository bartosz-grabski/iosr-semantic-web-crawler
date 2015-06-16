package pl.edu.agh.iosr;

import org.junit.Test;

import java.util.ArrayList;

public class ScrapyRunnerTest {

    @Test
    public void test(){
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("diving");
        strings.add("sailing");
        ScrapyRunner.deployProject("superQuery", "http://wpdressing.com,http://anemo.pl", strings);
    }
}
