package hellocucumber;

import java.nio.file.Path;
import java.nio.file.Paths;

public class main {
    public static void main(String[] args){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println(s+"\\sqe-hw3\\Cucumber\\edgedriver_win64\\msedgedriver.exe");
    }
    }
