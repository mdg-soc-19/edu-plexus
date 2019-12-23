package com.example.mdg_soc_eduplexus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyGridAdaptor extends ArrayAdapter{
    List<Date> dates;
    Calendar currentdate;
    List<Events> events;
    LayoutInflater inflater;

    public MyGridAdaptor(@NonNull Context context, List<Date> dates,Calendar currentdate,List<Events> events) {
        super(context, R.layout.single_cel_layout);

        this.dates=dates;
        this.currentdate=currentdate;
        this.events=events;
        inflater = LayoutInflater.from(context);
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int DayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentMonth = currentdate.get(Calendar.MONTH)+1;
        int currentYear = currentdate.get(Calendar.YEAR);



        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.single_cel_layout, parent , false);
        }

        if(displayMonth == currentMonth && displayYear == currentYear){
            view.setBackgroundColor(getContext().getResources().getColor(R.color.green));
        }
        else {
            view.setBackgroundColor(Color.parseColor("#cccccc"));
        }
        TextView Day_Number = view.findViewById(R.id.calendar_day);
        Day_Number.setText(String.valueOf(DayNo));
        TextView EventNumber = view.findViewById(R.id.events_id);
        Calendar eventCalendar = Calendar.getInstance();
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0;i<events.size();i++){
            eventCalendar.setTime(ConvertStringToDate(events.get(i).getDATE()));
            if(DayNo == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH)+1
            && displayYear == eventCalendar.get(Calendar.YEAR)) {
                arrayList.add(events.get(i).getEVENTS());
                EventNumber.setText(arrayList.size()+"Events");
            }
        }
        return view;
    }

    private Date ConvertStringToDate(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddd",Locale.ENGLISH);
        Date date = null;
        try{
            date = format.parse(eventDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }
    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return dates.indexOf(item);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return dates.get(position) ;
    }
}


