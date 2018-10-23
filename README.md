# CS3270A8
CS3270 - Android Development  Assignment #8

This assignment is designed to give you additional experience in previously learned Android development techniques along with new experience utilizing Recycler Views, Recycler Adapters, MVVM / LiveData, and intro to theming.
Your submission for this assignment will be Screen Captures, and a zip file of your entire project folder.

NOTE: The app that you're developing in this assignment will be the continuation of the last assignment, and a building point for next week's assignment as well.


This assignment will use the database you designed last week as part of A7. As a reminder it should have the following Course columns in your Entity:

_id: integer primary key auto-increment
id: text
name: text
course_code: text
start_at: text
end_at: text

(Note that the _id field will NOT be entered by the user on the add or edit screens.  It is the auto-numbered identity field of the database table and is managed by Room.)

Your assignment is as follows.

1. Create a new Android project named CS3270A8.
2. Modify the app to change the application name to "CS3270A8 <Your Name>"
3. Create an Activity, Fragments and layouts to function similar to the screen shots below.
You should have
- MainActivity
- CourseListFragment
- CourseViewFragment
- CourseEditFragment
- you will also need a Recycler View Adapter, database classes,and a Delete Confirmation Dialog.
4. The functionality of the app is that the user can do the following:
   -   Add a new course to the database
   -   View a list of courses
   -   View the details of a selected course
   -   Edit a selected course
   -   Add a new course
   -   Delete a selected course
5. The Edit and Delete functions should be selected via a Toolbar icon
6. The View and Edit fragment UI elements should be contained in a ScrollView so that they can move out of the way of the keyboard.
7. Save and test the app on the emulator or device.
8. Check the "Problems" tab to insure that you have resolved all errors and warnings.
9. Using Android Monitor, capture sufficient screenshots of the app.
  - Save the screenshots as CS3270A8Cap1.png, CS3270A8Cap2.png, etc.
 - Zip your project file into a file named CS3270A8.zip
