package pl.edu.agh.iosr;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ScrapyRunner {

    public static void executeCommand(String command) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println(output.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deployProject(String queryId, String urls, String keywords) {
        String deployCommand = "curl http://" + RestApp.SCRAPYD_ADDRESS + "/addversion.json -F project=" + queryId + " -F version=r2 -F egg=@my.egg";
        String scheduleCommand = "curl http://" + RestApp.SCRAPYD_ADDRESS + "/schedule.json -d project=" + queryId + " -d spider=metaspider -d urls="+urls
                +" -d query_id="+queryId + " -d keywords=" + keywords;

        executeCommand(deployCommand);
        executeCommand(scheduleCommand);

    }
}
