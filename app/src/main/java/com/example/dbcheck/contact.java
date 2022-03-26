package com.example.dbcheck;

public class contact {
    private int studentsID;
    private int projectsID;
    private String title;
    private String description;
    private int year;
    private String photo;


    public contact(int studentsID, int projectsID, String title, String description, int year, String photo){
        this.studentsID = studentsID;
        this.projectsID = projectsID;
        this.year = year;
        this.photo = photo;
        this.title = title;
        this.description = description;
    }

    public int getStudentsID() {
        return studentsID;
    }

    public void setStudentsID(int studentsID) {
        this.studentsID = studentsID;
    }

    public int getProjectsID() {
        return projectsID;
    }

    public void setProjectsID(int projectsID) {
        this.projectsID = projectsID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String toString() {
        return "Contact{" +
                "title ='" + title + '\'' +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                ", projectsid='" + projectsID + '\'' +
                "studentsID ='" + studentsID + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

}
