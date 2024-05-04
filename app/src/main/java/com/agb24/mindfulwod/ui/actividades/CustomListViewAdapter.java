package com.agb24.mindfulwod.ui.actividades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.agb24.mindfulwod.R;

import java.util.List;

public class CustomListViewAdapter extends BaseExpandableListAdapter {

    private List<ActivityWODItem> activityWodStackList;

    public CustomListViewAdapter(List<ActivityWODItem> activityWodStackList) {
        this.activityWodStackList = activityWodStackList;
    }

    @Override
    public int getGroupCount() {
        return activityWodStackList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<String> lngList = activityWodStackList.get(groupPosition).getProgrammingLanguages();
        return lngList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return activityWodStackList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<String> programmingLanguageList = activityWodStackList.get(groupPosition).getProgrammingLanguages();
        return programmingLanguageList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ActivityWODItem activityWODItem = (ActivityWODItem) getGroup(groupPosition);
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_stack_list_item, null);
        TextView activityStackTV = view.findViewById(R.id.activity_stack_w);
        activityStackTV.setText(activityWODItem.getTechStack());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String language = (String) getChild(groupPosition, childPosition);
        String exerciseDescription = activityWodStackList.get(groupPosition).getExerciseDescriptions().get(childPosition); // Obtenemos la descripción del ejercicio
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_wod_list_item, null);
        TextView programmingLngTV = view.findViewById(R.id.id_activitywod);
        TextView descriptionTV = view.findViewById(R.id.id_activitywod_desc); // Agregamos la referencia al TextView de descripción
        programmingLngTV.setText(language);
        descriptionTV.setText(exerciseDescription); // Establecemos la descripción del ejercicio
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
