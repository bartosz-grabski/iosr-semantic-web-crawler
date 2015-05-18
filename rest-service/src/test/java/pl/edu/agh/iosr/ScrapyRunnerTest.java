package pl.edu.agh.iosr;

import org.junit.Test;

public class ScrapyRunnerTest {

    @Test
    public void test(){
        ScrapyRunner.deployProject("superQuery", "http://wpdressing.com,http://anemo.pl");
    }
}
