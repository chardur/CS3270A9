# Android app with Room database and REST api CS3270A9
CS3270 - Android Development  Assignment #9


<p float="left">
  <img src="/CS3270A9Cap1.png" width="250" />
  <img src="/CS3270A9Cap2.png" width="250" /> 
  <img src="/CS3270A9Cap3.png" width="250" />
  <img src="/CS3270A8Cap2.png" width="250" />
  <img src="/CS3270A8Cap4.png" width="250" /> 
</p>


This assignment using technologies and techniques used in prior assignments as well as the specific REST API related helps that are included in this week's module.

Your submission for this assignment will be Screen Captures, and a zip file of your entire project folder.

The app that you're developing will extend the course database from Assignment #8 to allow for the import of courses from your Canvas account.  The user should be able to initiate the import via an Option Menu Selection.  Learning how to refactor a project into a new one is an essential skill
Additionally, the user should also be able  to see a list of the assignments for a course by long-pressing on a course in the course list.

Your assignment is as follows.

1. Create a copy of your CS3270A8 folder rename it to be CS3270A9.
 - refactor the project, modifying all necessary parts to reflect the new project and app name.
 - the app name should be CS3270A9 <your last name>
2. Create an option menu item that will be available when the used is on the Course List to provide for the import of your own Canvas Course information via the Canvas REST API.  The INTERNET permission must be set in the manifest file in order to connect to the Canvas REST API.
3. Use the imported Course information to load the SQLite (i.e. ROOM Database) Course table after first deleting any existing rows.  The table can be loaded using the same insert method that was used for manual data entry.
4. Once the Course information has been imported into the SQLite database, it should be able to be displayed and edited in the same manner as it was in Assignment #8.
5. Create a layout and code to allow the user to long press on a course which then displays a list of assignments for that course.  The assignments are received via the Canvas REST API in a manner similar to that of retrieving the Courses.  Note that the assignments should be displayed in a ListView, but should not be saved in the database.
6. Save and test the app on the emulator or device.
7. Check the "Problems" tab to insure that you have resolved all errors and warnings.
8. IMPORTANT - 
    Prior to zipping your project folder for submissions, change your Canvas API Auth Key to an empty string to preserve the security of your key.  I will be using my own key to test your app.
9. Capture screenshots of the important functions of the app.
  - Save the screenshots as CS3270A9Cap1.png, CS3270A9Cap2.png, etc.
 - Zip your project file into a file named CS3270A9.zip

  Submit (upload) both your final screen shots and zipped project folder on or before the Due Date. 
- Make sure that the files are named correctly. Files that are incorrectly named will not be graded.
