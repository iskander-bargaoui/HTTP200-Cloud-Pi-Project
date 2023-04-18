package tn.esprit.pibakcend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.python.util.PythonInterpreter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@SpringBootApplication
@RestController
public class PiBakcendApplication {
    @GetMapping("/execute")
    public ResponseEntity<String> executePythonScript()   {
      // PythonInterpreter interpreter = new PythonInterpreter();
     //  interpreter.execfile("C:/Users/ibrahim/Desktop/IA-Camera/maincamera.py");
        /*
        String output = "";
        try {
            PythonInterpreter interpreter = new PythonInterpreter();
            interpreter.execfile("C:/Users/ibrahim/Desktop/IA-Camera/maincamera.py");
            output = "Script executed successfully";
        } catch (Exception e) {
            output = "Error executing script: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
        }
        return ResponseEntity.ok(output);
        C:/Users/ibrahim/Downloads/IA-VOICE-PI/IA-VOICE-PI/.venv/Scripts/activate.bat
         */

        String output = "";
        try {
            // Activate the virtual environment                         C:\Users\ibrahim\Desktop\IA-Camera\.venv  C:/Users/ibrahim/IdeaProjects/Pi-bakcend/.venv
            ProcessBuilder venvProcessBuilder = new ProcessBuilder("C:/Users/ibrahim/IdeaProjects/Pi-bakcend/.venv/Scripts/activate.bat");
            Process venvProcess = venvProcessBuilder.start();
            venvProcess.waitFor();
            System.out.println(venvProcess);
            System.out.println("Virtual environment activated");
            // c:/users/ibrahim/appdata/local/programs/python/python38/python.exe
            // Execute the Python script                   C:/Users/ibrahim/Desktop/IA-Camera/maincamera.py       C:\Users\ibrahim\Downloads\IA-VOICE-PI\
            ProcessBuilder pyProcessBuilder = new ProcessBuilder("c:/users/ibrahim/appdata/local/programs/python/python38/python.exe", "C:/Users/ibrahim/Downloads/IA-VOICE-PI/IA-VOICE-PI/main.py" ,"jython main.py");
            System.out.println(pyProcessBuilder);
            Process pyProcess = pyProcessBuilder.start();
            int exitCode = pyProcess.waitFor();
            System.out.println("Process exited with code " + exitCode);

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(pyProcess.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                errorOutput.append(errorLine + "\n");
            }
            pyProcess.waitFor();

            System.out.println("Python script executed");

            output = errorOutput.toString();
        } catch (Exception e) {
            output = "Error executing script: " + e.getMessage();
            System.out.println(output);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(output);
        }
        return ResponseEntity.ok(output);

    }
    public static void main(String[] args) {
        SpringApplication.run(PiBakcendApplication.class, args);
      //  PythonInterpreter interpreter = new PythonInterpreter();
      //  interpreter.execfile("C:/Users/ibrahim/Desktop/IA-Camera/maincamera.py");
    }
}
