package com.agb24.mindfulwod.ui.actividades;

import java.util.ArrayList;

public class ActivityWODItem {
    private String wodStack;
    private ArrayList<String> programmingLanguages;
    private ArrayList<String> exerciseDescriptions; // Nueva lista para almacenar las descripciones de los ejercicios

    public ActivityWODItem(String wodStack, ArrayList<String> programmingLanguages, ArrayList<String> exerciseDescriptions) {
        this.wodStack = wodStack;
        this.programmingLanguages = programmingLanguages;
        this.exerciseDescriptions = exerciseDescriptions; // Inicializar la lista de descripciones de ejercicios
    }

    public String getTechStack() {
        return wodStack;
    }

    public void setTechStack(String wodStack) {
        this.wodStack = wodStack;
    }

    public ArrayList<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(ArrayList<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public ArrayList<String> getExerciseDescriptions() {
        return exerciseDescriptions;
    }

    public void setExerciseDescriptions(ArrayList<String> exerciseDescriptions) {
        this.exerciseDescriptions = exerciseDescriptions;
    }
}
