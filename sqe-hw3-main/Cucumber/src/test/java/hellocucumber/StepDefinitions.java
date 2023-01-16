package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StepDefinitions {
    private moodle studentMoodle;
    private moodle teacherMoodle;
    private String STUDENT_USERNAME="dina";
    private String STUDENT_PASS="123456";
    private String TEACHER_USERNAME="ben";
    private String TEACHER_PASS="12345";
    private String COURSE_NAME = "hebrew";
    private boolean found = false;
    Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString();
    private String webDriver = "webdriver.edge.driver";
    private String path = s +"\\edgedriver_win64\\msedgedriver.exe";
    public void studentMoodleInit() {
        System.out.println("--------------- INITIALIZING Student Moodle - OPENING WEBPAGE ---------------");
        if(studentMoodle != null){
            studentMoodle.terminateDriver();
        }
        studentMoodle = new moodle();
        studentMoodle.initSession(webDriver,path);
    }
    public void teacherMoodleInit() {
        System.out.println("--------------- INITIALIZING Teacher Moodle - OPENING WEBPAGE ---------------");
        if(teacherMoodle != null){
            teacherMoodle.terminateDriver();
        }
        teacherMoodle = new moodle();
        teacherMoodle.initSession(webDriver,path);
    }
    public void beforeAll(){
        teacherMoodleInit();
        teacherMoodle.loginSequence(TEACHER_USERNAME,TEACHER_PASS);
        teacherMoodle.myCoursesTab();
        teacherMoodle.goToCourse(COURSE_NAME);
        teacherMoodle.goToRecords();
    }

    @Given("a student is in homepage")
    public void aStudentIsInHomepage() {
        beforeAll();
        studentMoodleInit();
    }
    @When("student navigates to login page")
    public void studentNavigatesToLoginPage() {
        studentMoodle.goToLogin();
    }

    @And("student enters username and password")
    public void studentEntersUsernameAndPassword() {
        studentMoodle.enterLoginInfo(STUDENT_USERNAME,STUDENT_PASS);
    }

    @And("Welcome back message is displayed")
    public void welcomeBackMessageIsDisplayed() {
        studentMoodle.WelcomeMessage();
    }

    @And("Teacher logs in as-well")
    public void teacherLogsInAsWell() {
        teacherMoodleInit();
        teacherMoodle.loginSequence(TEACHER_USERNAME,TEACHER_PASS);
    }

    @And("student navigates to MyCourses")
    public void studentNavigatesToMyCourses() {
        studentMoodle.myCoursesTab();
    }

    @And("teacher navigates to courses")
    public void teacherNavigatesToCourses() {
        teacherMoodle.myCoursesTab();
    }

    @And("teacher deletes record")
    public void teacherDeletesForum() {
        teacherMoodle.editModeOn();
        teacherMoodle.removeRecord();
    }
    @And("student enters course")
    public void studentEntersCourse() {
        studentMoodle.goToCourse(COURSE_NAME);
    }

    @And("Student trys to watch record")
    public void studentTrysToWatchRecord() {
        found = studentMoodle.watchRecord();
    }
    @Then("record doesnt exist")
    public void recordDoesntExist() {
        Assertions.assertFalse(found);
    }
    @Given("a teacher is in homepage")
    public void aTeacherIsInHomepage() {
        beforeAll();
        teacherMoodleInit();
    }
    @When("teacher navigates to login page")
    public void teacherNavigatesToLoginPage() {
        teacherMoodle.goToLogin();
    }

    @And("teacher enters username and password")
    public void teacherEntersUsernameAndPassword() {
        teacherMoodle.enterLoginInfo(TEACHER_USERNAME,TEACHER_PASS);
    }

    @And("Student logs in as-well")
    public void studentLogsInAsWell() {
        studentMoodleInit();
        studentMoodle.loginSequence(STUDENT_USERNAME,STUDENT_PASS);
    }

    @And("teacher enters course")
    public void teacherEntersCourse() {
        teacherMoodle.goToCourse(COURSE_NAME);
    }

    @And("student watches record")
    public void studentWatchesRecord() {
        studentMoodle.watchRecord();
    }

    @And("comment exists")
    public void commentExists() {
        studentMoodle.goToRecords();
        studentMoodle.checkIfRecordExists();
    }
    @Then("record no longer appears")
    public void recordNoLongerAppears() {
        found = teacherMoodle.goToRecords();
        Assertions.assertFalse(found);
    }
    @And("Welcome back message is displayed for teacher")
    public void welcomeBackMessageIsDisplayedForTeacher() {
        teacherMoodle.WelcomeMessage();
    }

}
