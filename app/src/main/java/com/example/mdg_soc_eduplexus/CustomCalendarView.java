package com.example.mdg_soc_eduplexus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CustomCalendarView extends LinearLayout {
    ImageButton NextButton,PreviousButton;
    TextView CurrentDate;
    GridView gridView;
    private static final int MAX_CALENDAR_DAYS = 42;
    Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
    Context context;
    MyGridAdaptor myGridAdaptor;
    SimpleDateFormat dateFormat = new SimpleDateFormat("mm yyyy",Locale.ENGLISH);
    SimpleDateFormat monthFormat = new SimpleDateFormat("mm",Locale.ENGLISH);
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.ENGLISH);
    SimpleDateFormat eventDateFormat = new SimpleDateFormat("yyyy-mm-dd",Locale.ENGLISH);
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();
    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        InitializeLayout();
        SetUpCalendar();
        PreviousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                SetUpCalendar();
            }
        });

        NextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                SetUpCalendar();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                @SuppressLint("InflateParams") final View addView;
                addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout, null);
                final EditText EventName = addView.findViewById(R.id.eventName);
                final TextView EventTime = addView.findViewById(R.id.eventtime);
                ImageButton setTime = addView.findViewById(R.id.seteventtime);
                Button AddEvent = addView.findViewById(R.id.addevent);
                setTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hformate = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
                                String event_Time = hformate.format(c.getTime());
                                EventTime.setText(event_Time);
                            }
                        }, hours, minutes, false);
                        timePickerDialog.show();
                    }
                });
                final String date = eventDateFormat.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));

                AddEvent.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveEvent(EventName.getText().toString(), EventTime.getText().toString(), date, month, year);
                        SetUpCalendar();
                        alertDialog.dismiss();
                    }
                });
                builder.setView(addView);
                builder.setView(addView);
                alertDialog.show();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String date = eventDateFormat.format(dates.get(position));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout, null);
                RecyclerView recyclerView = showView.findViewById(R.id.EventsRV);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventRecyclerAdapter eventRecyclerAdapter = new EventRecyclerAdapter(showView.getContext(),CollectEventByDate(date));
                recyclerView.setAdapter(eventRecyclerAdapter);
                eventRecyclerAdapter.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog =  builder.create();
                alertDialog.show();
                return true;
            }
        });




    }

    private ArrayList<Events> CollectEventByDate(String date){
        ArrayList<Events> arrayList = new ArrayList<>();
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.ReadEVents(date,database);
        while(cursor.moveToNext()){
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.EVENT));
            String time = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.TIME));
            String Date = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.DATE));
            String month = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.MONTH));
            String Year = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.YEAR));
            Events events = new Events(event,time,Date,month,Year);
            arrayList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();
        return arrayList;
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void saveEvent(String event,String time,String Date,String Month,String year){

       DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.SaveEvent(event,time,Date,Month,year,database);
        dbOpenHelper.close();
      Toast.makeText(context,"Event Saved",Toast.LENGTH_SHORT).show();
    }
    private void InitializeLayout(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.calendar_layout,this);
        NextButton = view.findViewById(R.id.nextBtn);
        PreviousButton = view.findViewById(R.id.previousBtn);
        CurrentDate = view.findViewById(R.id.current_date);
        gridView = view.findViewById(R.id.gridview23);
    }

    private void SetUpCalendar(){
        String currentDate = dateFormat.format(calendar.getTime());
        CurrentDate.setText(currentDate);
        dates.clear();
        Calendar monthCalendar =(Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-FirstDayOfMonth);
        CollectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));
        while (dates.size() <MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        myGridAdaptor =  new MyGridAdaptor(context,dates,calendar,eventsList);
        gridView.setAdapter(myGridAdaptor);
    }
    private void CollectEventsPerMonth(String Month,String year){
        eventsList.clear();
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getReadableDatabase();
        Cursor cursor = dbOpenHelper.ReadEventsperMonth(Month,year,database);
        while (cursor.moveToNext()){
            String event = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.EVENT));
            String time = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.TIME));
            String date = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.DATE));
            String month = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.MONTH));
            String Year = cursor.getString(cursor.getColumnIndex(DBStructure.Eventstable.YEAR));
            Events events = new Events(event,time,date,month,Year);
            eventsList.add(events);
        }
        cursor.close();
        dbOpenHelper.close();
    }
}
