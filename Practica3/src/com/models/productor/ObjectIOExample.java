package com.models.productor;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ObjectIOExample {
	 
    private static final String filepath="D:\\Code\\roger\\Practica3\\src\\com\\consoleapp\\main\\obj";
 
    public static void main(String args[]) {
 
        ObjectIOExample objectIO = new ObjectIOExample();
 
        Student student = new Student();
        student.setAge(50);
        student.setFirstName("May");
        student.setLastName("Gorda");
        objectIO.WriteObjectToFile(student);
    }
 
    public void WriteObjectToFile(Object serObj) {
 
        try {
 
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}